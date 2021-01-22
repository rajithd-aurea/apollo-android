// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragments_same_type_condition.fragment.adapter

import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.ResponseReader
import com.apollographql.apollo.api.internal.ResponseWriter
import com.example.fragments_same_type_condition.fragment.DroidDetails2Impl
import kotlin.Array
import kotlin.String
import kotlin.Suppress

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
object DroidDetails2Impl_ResponseAdapter : ResponseAdapter<DroidDetails2Impl.Data> {
  val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
    ResponseField(
      type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
      responseName = "__typename",
      fieldName = "__typename",
      arguments = emptyMap(),
      conditions = emptyList(),
      fields = emptyArray(),
    ),
    ResponseField(
      type = ResponseField.Type.Named.Other("String"),
      responseName = "primaryFunction",
      fieldName = "primaryFunction",
      arguments = emptyMap(),
      conditions = emptyList(),
      fields = emptyArray(),
    )
  )

  override fun fromResponse(reader: ResponseReader, __typename: String?): DroidDetails2Impl.Data {
    return reader.run {
      var __typename: String? = __typename
      var primaryFunction: String? = null
      while(true) {
        when (selectField(RESPONSE_FIELDS)) {
          0 -> __typename = readString(RESPONSE_FIELDS[0])
          1 -> primaryFunction = readString(RESPONSE_FIELDS[1])
          else -> break
        }
      }
      DroidDetails2Impl.Data(
        __typename = __typename!!,
        primaryFunction = primaryFunction
      )
    }
  }

  override fun toResponse(writer: ResponseWriter, value: DroidDetails2Impl.Data) {
    writer.writeString(RESPONSE_FIELDS[0], value.__typename)
    writer.writeString(RESPONSE_FIELDS[1], value.primaryFunction)
  }
}
