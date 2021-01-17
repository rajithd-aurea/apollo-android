package com.apollographql.apollo.cache.normalized.internal

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.cache.CacheHeaders
import com.apollographql.apollo.cache.normalized.CacheKeyResolver
import com.apollographql.apollo.cache.normalized.CacheReference
import com.apollographql.apollo.cache.normalized.Record

class CacheMapBuilder(
    private val readableStore: ReadableStore,
    private val rootKey: String,
    private val variables: Operation.Variables,
    private val cacheKeyResolver: CacheKeyResolver,
    private val cacheHeaders: CacheHeaders,
) {
  private val holder: MutableList<Any?> = listOf(PlaceHolder).toMutableList()

  sealed class PendingReference(val key: String) {
    class InMap(
        key: String,
        val name: String,
        val parent: MutableMap<String, Any?>
    ) : PendingReference(key)

    class InList(
        key: String,
        val index: Int,
        val parent: MutableList<Any?>
    ) : PendingReference(key)
  }

  lateinit var pendingReferences: List<PendingReference>

  fun toMap(): Map<String, Any?> {
    pendingReferences = listOf(
        PendingReference.InList(
            rootKey,
            0,
            holder,
        )
    )

    while (!pendingReferences.isEmpty()) {
      val records = readableStore.read(pendingReferences.map { it.key }, cacheHeaders).associateBy { it.key }

      pendingReferences = pendingReferences.flatMap { pendingReference ->
        val record = records[pendingReference.key] ?: throw error("Cache miss on ${pendingReference.key}")

        val (map, newPendingReferences) = record.mutate()
        when (pendingReference) {
          is PendingReference.InList -> pendingReference.parent[pendingReference.index] = map
          is PendingReference.InMap -> pendingReference.parent[pendingReference.name] = map
        }

        newPendingReferences
      }
    }

    return holder.first() as Map<String, Any?>
  }

  object PlaceHolder

  private fun List<*>.mutate(references: MutableList<PendingReference>): MutableList<*> {
    val list = toMutableList()

    for (i in 0 until list.size) {
      when (val value = list[i]) {
        is CacheReference -> {
          list[i] = PlaceHolder
          references.add(PendingReference.InList(key = value.key, parent = list, index = i))
        }
        is List<*> -> value.mutate(references)
      }
    }
    return list
  }

  private fun Record.mutate(): Pair<MutableMap<String, Any?>, List<PendingReference>> {
    val map = toMutableMap()

    val references = mutableListOf<PendingReference>()
    for (name in map.keys) {
      when (val value = map[name]) {
        is CacheReference -> {
          map[name] = PlaceHolder
          references.add(PendingReference.InMap(key = value.key, parent = map, name = name))
        }
        is List<*> -> {
          map[name] = value.mutate(references)
        }
      }
    }

    return map to references
  }
}