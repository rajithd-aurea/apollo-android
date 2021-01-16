package com.apollographql.apollo.cache.normalized.internal

import com.apollographql.apollo.api.CustomScalar
import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.JsonElement
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ResponseReader
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.internal.json.Utils.readRecursively
import com.apollographql.apollo.cache.normalized.CacheKey
import com.apollographql.apollo.cache.normalized.CacheKeyResolver

class CacheStreamResponseReader(
    private val jsonReader: CacheJsonReader,
    private val customScalarAdapters: CustomScalarAdapters,
    val cacheKeyResolver: CacheKeyResolver,
    val variables: Operation.Variables,
) : ResponseReader, ResponseReader.ListItemReader {
  private val cacheKeyBuilder = RealCacheKeyBuilder()

  private var visitedIndexes: ArrayList<Boolean>? = null
  private var scan = 0
  private var selectedField: ResponseField? = null
  private var visitedIndexesStack = ArrayList<ArrayList<Boolean>?>()
  private var scanStack = ArrayList<Int>()
  private var selectedFieldStack = ArrayList<ResponseField?>()

  private fun push() {
    visitedIndexesStack.add(visitedIndexes)
    visitedIndexes = null
    scanStack.add(scan)
    scan = 0
    selectedFieldStack.add(selectedField)
    selectedField = null
  }

  private fun pop() {
    visitedIndexes = visitedIndexesStack.removeAt(visitedIndexesStack.size - 1)
    scan = scanStack.removeAt(scanStack.size - 1)
    selectedField = selectedFieldStack.removeAt(selectedFieldStack.size - 1)
  }

  override fun selectField(fields: Array<ResponseField>): Int {
    if (visitedIndexes == null) {
      visitedIndexes = ArrayList(0.until(fields.size).map { false })
    }
    while (jsonReader.hasNext()) {
      val nextFieldName = jsonReader.nextName()

      val index = fields.indexOfFirst { field -> cacheKeyBuilder.build(field, variables) == nextFieldName }
      if (index == -1) {
        jsonReader.skipValue()
      } else {
        visitedIndexes!![index] = true
        return index
      }
    }

    while (scan < visitedIndexes!!.size) {
      if (!visitedIndexes!![scan]) {
        selectedField = fields[scan]
        return scan.also {
          scan++
        }
      } else {
        scan++
      }
    }

    return -1
  }


  override fun readString(field: ResponseField): String? {
    return readValue(field) {
      nextString()
    }
  }

  override fun readInt(field: ResponseField): Int? {
    return readValue(field) {
      nextInt()
    }
  }

  override fun readDouble(field: ResponseField): Double? {
    return readValue(field) {
      nextDouble()
    }
  }

  override fun readBoolean(field: ResponseField): Boolean? {
    return readValue(field) {
      nextBoolean()
    }
  }

  override fun <T : Any> readObject(field: ResponseField, block: (ResponseReader) -> T): T? {
    return readValue(field) {
      beginObject()
      push()
      val result = block(this@CacheStreamResponseReader)
      pop()
      endObject()
      result
    }
  }

  override fun <T : Any> readList(field: ResponseField, block: (ResponseReader.ListItemReader) -> T): List<T?>? {
    return readValue(field) {
      beginArray()
      val result = ArrayList<T?>()
      while (hasNext()) {
        when (peek()) {
          JsonReader.Token.NULL -> result.add(jsonReader.nextNull())
          else -> result.add(block(this@CacheStreamResponseReader))
        }
      }
      endArray()
      result
    }
  }

  override fun <T : Any> readCustomScalar(field: ResponseField.CustomScalarField): T? {
    val typeAdapter = customScalarAdapters.adapterFor<T>(field.customScalar)
    val value = readValue(field) {
      readRecursively()
    }
    return value?.let { typeAdapter.decode(JsonElement.fromRawValue(it)) }
  }

  private inline fun <T> readValue(field: ResponseField, block: JsonReader.() -> T?): T? {
    if (selectedField != null) {
      selectedField = null
      if (field.type == ResponseField.Type.OBJECT) {
        val cacheKey = cacheKeyResolver.fromFieldArguments(field, variables)
        if (cacheKey != CacheKey.NO_KEY) {
          jsonReader.beginFakeObject(cacheKey.key)
          return jsonReader.block().also {
            jsonReader.endFakeObject()
          }
        }
      }
      error("Cache miss for ${field.fieldName}")
    }
    return when (jsonReader.peek()) {
      JsonReader.Token.NULL -> if (field.optional) jsonReader.nextNull<T>() else throw NullPointerException(
          "Couldn't read `${field.responseName}` field value, expected non null value"
      )
      else -> block(jsonReader)
    }
  }

  override fun readString(): String {
    return jsonReader.nextString()!!
  }

  override fun readInt(): Int {
    return jsonReader.nextInt()
  }

  override fun readDouble(): Double {
    return jsonReader.nextDouble()
  }

  override fun readBoolean(): Boolean {
    return jsonReader.nextBoolean()
  }

  override fun <T : Any> readCustomScalar(customScalar: CustomScalar): T {
    val typeAdapter = customScalarAdapters.adapterFor<T>(customScalar)
    val value = jsonReader.readRecursively()!!
    return typeAdapter.decode(JsonElement.fromRawValue(value))
  }

  override fun <T : Any> readObject(block: (ResponseReader) -> T): T {
    jsonReader.beginObject()
    push()
    val result = block(this@CacheStreamResponseReader)
    pop()
    jsonReader.endObject()
    return result
  }

  override fun <T : Any> readList(block: (ResponseReader.ListItemReader) -> T): List<T?> {
    jsonReader.beginArray()
    val result = ArrayList<T?>()
    while (jsonReader.hasNext()) {
      when (jsonReader.peek()) {
        JsonReader.Token.NULL -> result.add(jsonReader.nextNull())
        else -> result.add(block(this))
      }
    }
    jsonReader.endArray()
    return result
  }
}