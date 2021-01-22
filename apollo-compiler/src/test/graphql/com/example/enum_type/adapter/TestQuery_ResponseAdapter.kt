// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.enum_type.adapter

import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.ResponseReader
import com.apollographql.apollo.api.internal.ResponseWriter
import com.example.enum_type.TestQuery
import com.example.enum_type.type.Episode
import kotlin.Array
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
object TestQuery_ResponseAdapter : ResponseAdapter<TestQuery.Data> {
  val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
    ResponseField(
      type = ResponseField.Type.Named.Object("Character"),
      responseName = "hero",
      fieldName = "hero",
      arguments = emptyMap(),
      conditions = emptyList(),
      fields = Hero.RESPONSE_FIELDS,
    )
  )

  override fun fromResponse(reader: ResponseReader, __typename: String?): TestQuery.Data {
    return reader.run {
      var hero: TestQuery.Data.Hero? = null
      while(true) {
        when (selectField(RESPONSE_FIELDS)) {
          0 -> hero = readObject<TestQuery.Data.Hero>(RESPONSE_FIELDS[0]) { reader ->
            Hero.fromResponse(reader)
          }
          else -> break
        }
      }
      TestQuery.Data(
        hero = hero
      )
    }
  }

  override fun toResponse(writer: ResponseWriter, value: TestQuery.Data) {
    if(value.hero == null) {
      writer.writeObject(RESPONSE_FIELDS[0], null)
    } else {
      writer.writeObject(RESPONSE_FIELDS[0]) { writer ->
        Hero.toResponse(writer, value.hero)
      }
    }
  }

  object Hero : ResponseAdapter<TestQuery.Data.Hero> {
    val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField(
        type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
        responseName = "name",
        fieldName = "name",
        arguments = emptyMap(),
        conditions = emptyList(),
        fields = emptyArray(),
      ),
      ResponseField(
        type =
            ResponseField.Type.NotNull(ResponseField.Type.List(ResponseField.Type.Named.Other("Episode"))),
        responseName = "appearsIn",
        fieldName = "appearsIn",
        arguments = emptyMap(),
        conditions = emptyList(),
        fields = emptyArray(),
      ),
      ResponseField(
        type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("Episode")),
        responseName = "firstAppearsIn",
        fieldName = "firstAppearsIn",
        arguments = emptyMap(),
        conditions = emptyList(),
        fields = emptyArray(),
      )
    )

    override fun fromResponse(reader: ResponseReader, __typename: String?): TestQuery.Data.Hero {
      return reader.run {
        var name: String? = null
        var appearsIn: List<Episode?>? = null
        var firstAppearsIn: Episode? = null
        while(true) {
          when (selectField(RESPONSE_FIELDS)) {
            0 -> name = readString(RESPONSE_FIELDS[0])
            1 -> appearsIn = readList<Episode>(RESPONSE_FIELDS[1]) { reader ->
              Episode.safeValueOf(reader.readString())
            }
            2 -> firstAppearsIn = readString(RESPONSE_FIELDS[2])?.let { Episode.safeValueOf(it) }
            else -> break
          }
        }
        TestQuery.Data.Hero(
          name = name!!,
          appearsIn = appearsIn!!,
          firstAppearsIn = firstAppearsIn!!
        )
      }
    }

    override fun toResponse(writer: ResponseWriter, value: TestQuery.Data.Hero) {
      writer.writeString(RESPONSE_FIELDS[0], value.name)
      writer.writeList(RESPONSE_FIELDS[1], value.appearsIn) { value, listItemWriter ->
        listItemWriter.writeString(value?.rawValue)}
      writer.writeString(RESPONSE_FIELDS[2], value.firstAppearsIn.rawValue)
    }
  }
}
