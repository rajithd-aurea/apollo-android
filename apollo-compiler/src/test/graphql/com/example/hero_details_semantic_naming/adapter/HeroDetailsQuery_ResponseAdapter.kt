// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.hero_details_semantic_naming.adapter

import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.ResponseReader
import com.apollographql.apollo.api.internal.ResponseWriter
import com.example.hero_details_semantic_naming.HeroDetailsQuery
import kotlin.Array
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
object HeroDetailsQuery_ResponseAdapter : ResponseAdapter<HeroDetailsQuery.Data> {
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

  override fun fromResponse(reader: ResponseReader, __typename: String?): HeroDetailsQuery.Data {
    return reader.run {
      var hero: HeroDetailsQuery.Data.Hero? = null
      while(true) {
        when (selectField(RESPONSE_FIELDS)) {
          0 -> hero = readObject<HeroDetailsQuery.Data.Hero>(RESPONSE_FIELDS[0]) { reader ->
            Hero.fromResponse(reader)
          }
          else -> break
        }
      }
      HeroDetailsQuery.Data(
        hero = hero
      )
    }
  }

  override fun toResponse(writer: ResponseWriter, value: HeroDetailsQuery.Data) {
    if(value.hero == null) {
      writer.writeObject(RESPONSE_FIELDS[0], null)
    } else {
      writer.writeObject(RESPONSE_FIELDS[0]) { writer ->
        Hero.toResponse(writer, value.hero)
      }
    }
  }

  object Hero : ResponseAdapter<HeroDetailsQuery.Data.Hero> {
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
        type = ResponseField.Type.NotNull(ResponseField.Type.Named.Object("FriendsConnection")),
        responseName = "friendsConnection",
        fieldName = "friendsConnection",
        arguments = emptyMap(),
        conditions = emptyList(),
        fields = FriendsConnection.RESPONSE_FIELDS,
      )
    )

    override fun fromResponse(reader: ResponseReader, __typename: String?):
        HeroDetailsQuery.Data.Hero {
      return reader.run {
        var name: String? = null
        var friendsConnection: HeroDetailsQuery.Data.Hero.FriendsConnection? = null
        while(true) {
          when (selectField(RESPONSE_FIELDS)) {
            0 -> name = readString(RESPONSE_FIELDS[0])
            1 -> friendsConnection = readObject<HeroDetailsQuery.Data.Hero.FriendsConnection>(RESPONSE_FIELDS[1]) { reader ->
              FriendsConnection.fromResponse(reader)
            }
            else -> break
          }
        }
        HeroDetailsQuery.Data.Hero(
          name = name!!,
          friendsConnection = friendsConnection!!
        )
      }
    }

    override fun toResponse(writer: ResponseWriter, value: HeroDetailsQuery.Data.Hero) {
      writer.writeString(RESPONSE_FIELDS[0], value.name)
      writer.writeObject(RESPONSE_FIELDS[1]) { writer ->
        FriendsConnection.toResponse(writer, value.friendsConnection)
      }
    }

    object FriendsConnection : ResponseAdapter<HeroDetailsQuery.Data.Hero.FriendsConnection> {
      val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField(
          type = ResponseField.Type.Named.Other("Int"),
          responseName = "totalCount",
          fieldName = "totalCount",
          arguments = emptyMap(),
          conditions = emptyList(),
          fields = emptyArray(),
        ),
        ResponseField(
          type = ResponseField.Type.List(ResponseField.Type.Named.Object("FriendsEdge")),
          responseName = "edges",
          fieldName = "edges",
          arguments = emptyMap(),
          conditions = emptyList(),
          fields = Edge.RESPONSE_FIELDS,
        )
      )

      override fun fromResponse(reader: ResponseReader, __typename: String?):
          HeroDetailsQuery.Data.Hero.FriendsConnection {
        return reader.run {
          var totalCount: Int? = null
          var edges: List<HeroDetailsQuery.Data.Hero.FriendsConnection.Edge?>? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> totalCount = readInt(RESPONSE_FIELDS[0])
              1 -> edges = readList<HeroDetailsQuery.Data.Hero.FriendsConnection.Edge>(RESPONSE_FIELDS[1]) { reader ->
                reader.readObject<HeroDetailsQuery.Data.Hero.FriendsConnection.Edge> { reader ->
                  Edge.fromResponse(reader)
                }
              }
              else -> break
            }
          }
          HeroDetailsQuery.Data.Hero.FriendsConnection(
            totalCount = totalCount,
            edges = edges
          )
        }
      }

      override fun toResponse(writer: ResponseWriter,
          value: HeroDetailsQuery.Data.Hero.FriendsConnection) {
        writer.writeInt(RESPONSE_FIELDS[0], value.totalCount)
        writer.writeList(RESPONSE_FIELDS[1], value.edges) { value, listItemWriter ->
          listItemWriter.writeObject { writer ->
            Edge.toResponse(writer, value)
          }
        }
      }

      object Edge : ResponseAdapter<HeroDetailsQuery.Data.Hero.FriendsConnection.Edge> {
        val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField(
            type = ResponseField.Type.Named.Object("Character"),
            responseName = "node",
            fieldName = "node",
            arguments = emptyMap(),
            conditions = emptyList(),
            fields = Node.RESPONSE_FIELDS,
          )
        )

        override fun fromResponse(reader: ResponseReader, __typename: String?):
            HeroDetailsQuery.Data.Hero.FriendsConnection.Edge {
          return reader.run {
            var node: HeroDetailsQuery.Data.Hero.FriendsConnection.Edge.Node? = null
            while(true) {
              when (selectField(RESPONSE_FIELDS)) {
                0 -> node = readObject<HeroDetailsQuery.Data.Hero.FriendsConnection.Edge.Node>(RESPONSE_FIELDS[0]) { reader ->
                  Node.fromResponse(reader)
                }
                else -> break
              }
            }
            HeroDetailsQuery.Data.Hero.FriendsConnection.Edge(
              node = node
            )
          }
        }

        override fun toResponse(writer: ResponseWriter,
            value: HeroDetailsQuery.Data.Hero.FriendsConnection.Edge) {
          if(value.node == null) {
            writer.writeObject(RESPONSE_FIELDS[0], null)
          } else {
            writer.writeObject(RESPONSE_FIELDS[0]) { writer ->
              Node.toResponse(writer, value.node)
            }
          }
        }

        object Node : ResponseAdapter<HeroDetailsQuery.Data.Hero.FriendsConnection.Edge.Node> {
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

          override fun fromResponse(reader: ResponseReader, __typename: String?):
              HeroDetailsQuery.Data.Hero.FriendsConnection.Edge.Node {
            return reader.run {
              var name: String? = null
              while(true) {
                when (selectField(RESPONSE_FIELDS)) {
                  0 -> name = readString(RESPONSE_FIELDS[0])
                  else -> break
                }
              }
              HeroDetailsQuery.Data.Hero.FriendsConnection.Edge.Node(
                name = name!!
              )
            }
          }

          override fun toResponse(writer: ResponseWriter,
              value: HeroDetailsQuery.Data.Hero.FriendsConnection.Edge.Node) {
            writer.writeString(RESPONSE_FIELDS[0], value.name)
          }
        }
      }
    }
  }
}
