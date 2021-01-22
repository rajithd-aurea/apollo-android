// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.two_heroes_unique.adapter

import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.ResponseReader
import com.apollographql.apollo.api.internal.ResponseWriter
import com.example.two_heroes_unique.TestQuery
import kotlin.Array
import kotlin.String
import kotlin.Suppress

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
object TestQuery_ResponseAdapter : ResponseAdapter<TestQuery.Data> {
  val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
    ResponseField(
      type = ResponseField.Type.Named.Object("Character"),
      responseName = "r2",
      fieldName = "hero",
      arguments = emptyMap(),
      conditions = emptyList(),
      fields = R2.RESPONSE_FIELDS,
    ),
    ResponseField(
      type = ResponseField.Type.Named.Object("Character"),
      responseName = "luke",
      fieldName = "hero",
      arguments = mapOf<String, Any?>(
        "episode" to "EMPIRE"),
      conditions = emptyList(),
      fields = Luke.RESPONSE_FIELDS,
    )
  )

  override fun fromResponse(reader: ResponseReader, __typename: String?): TestQuery.Data {
    return reader.run {
      var r2: TestQuery.Data.R2? = null
      var luke: TestQuery.Data.Luke? = null
      while(true) {
        when (selectField(RESPONSE_FIELDS)) {
          0 -> r2 = readObject<TestQuery.Data.R2>(RESPONSE_FIELDS[0]) { reader ->
            R2.fromResponse(reader)
          }
          1 -> luke = readObject<TestQuery.Data.Luke>(RESPONSE_FIELDS[1]) { reader ->
            Luke.fromResponse(reader)
          }
          else -> break
        }
      }
      TestQuery.Data(
        r2 = r2,
        luke = luke
      )
    }
  }

  override fun toResponse(writer: ResponseWriter, value: TestQuery.Data) {
    if(value.r2 == null) {
      writer.writeObject(RESPONSE_FIELDS[0], null)
    } else {
      writer.writeObject(RESPONSE_FIELDS[0]) { writer ->
        R2.toResponse(writer, value.r2)
      }
    }
    if(value.luke == null) {
      writer.writeObject(RESPONSE_FIELDS[1], null)
    } else {
      writer.writeObject(RESPONSE_FIELDS[1]) { writer ->
        Luke.toResponse(writer, value.luke)
      }
    }
  }

  object R2 : ResponseAdapter<TestQuery.Data.R2> {
    val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField(
        type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
        responseName = "name",
        fieldName = "name",
        arguments = emptyMap(),
        conditions = emptyList(),
        fields = emptyArray(),
      )
    )

    override fun fromResponse(reader: ResponseReader, __typename: String?): TestQuery.Data.R2 {
      return reader.run {
        var name: String? = null
        while(true) {
          when (selectField(RESPONSE_FIELDS)) {
            0 -> name = readString(RESPONSE_FIELDS[0])
            else -> break
          }
        }
        TestQuery.Data.R2(
          name = name!!
        )
      }
    }

    override fun toResponse(writer: ResponseWriter, value: TestQuery.Data.R2) {
      writer.writeString(RESPONSE_FIELDS[0], value.name)
    }
  }

  object Luke : ResponseAdapter<TestQuery.Data.Luke> {
    val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField(
        type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
        responseName = "id",
        fieldName = "id",
        arguments = emptyMap(),
        conditions = emptyList(),
        fields = emptyArray(),
      ),
      ResponseField(
        type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
        responseName = "name",
        fieldName = "name",
        arguments = emptyMap(),
        conditions = emptyList(),
        fields = emptyArray(),
      )
    )

    override fun fromResponse(reader: ResponseReader, __typename: String?): TestQuery.Data.Luke {
      return reader.run {
        var id: String? = null
        var name: String? = null
        while(true) {
          when (selectField(RESPONSE_FIELDS)) {
            0 -> id = readString(RESPONSE_FIELDS[0])
            1 -> name = readString(RESPONSE_FIELDS[1])
            else -> break
          }
        }
        TestQuery.Data.Luke(
          id = id!!,
          name = name!!
        )
      }
    }

    override fun toResponse(writer: ResponseWriter, value: TestQuery.Data.Luke) {
      writer.writeString(RESPONSE_FIELDS[0], value.id)
      writer.writeString(RESPONSE_FIELDS[1], value.name)
    }
  }
}
