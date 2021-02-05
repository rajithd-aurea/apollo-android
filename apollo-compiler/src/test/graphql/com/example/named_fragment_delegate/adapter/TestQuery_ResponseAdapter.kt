// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.named_fragment_delegate.adapter

import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.apollographql.apollo.api.internal.ResponseReader
import com.apollographql.apollo.api.internal.ResponseWriter
import com.example.named_fragment_delegate.TestQuery
import kotlin.Any
import kotlin.Array
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
object TestQuery_ResponseAdapter : ResponseAdapter<TestQuery.Data> {
  private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
    ResponseField(
      type = ResponseField.Type.Named.Object("Character"),
      responseName = "hero",
      fieldName = "hero",
      arguments = emptyMap(),
      conditions = emptyList(),
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
    private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
      ResponseField(
        type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
        responseName = "__typename",
        fieldName = "__typename",
        arguments = emptyMap(),
        conditions = emptyList(),
      )
    )

    override fun fromResponse(reader: ResponseReader, __typename: String?): TestQuery.Data.Hero {
      val typename = __typename ?: reader.readString(RESPONSE_FIELDS[0])
      return when(typename) {
        "Droid" -> DroidHero.fromResponse(reader, typename)
        "Human" -> HumanHero.fromResponse(reader, typename)
        else -> OtherHero.fromResponse(reader, typename)
      }
    }

    override fun toResponse(writer: ResponseWriter, value: TestQuery.Data.Hero) {
      when(value) {
        is TestQuery.Data.Hero.DroidHero -> DroidHero.toResponse(writer, value)
        is TestQuery.Data.Hero.HumanHero -> HumanHero.toResponse(writer, value)
        is TestQuery.Data.Hero.OtherHero -> OtherHero.toResponse(writer, value)
      }
    }

    object DroidHero : ResponseAdapter<TestQuery.Data.Hero.DroidHero> {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
          responseName = "__typename",
          fieldName = "__typename",
          arguments = emptyMap(),
          conditions = emptyList(),
        ),
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
          responseName = "name",
          fieldName = "name",
          arguments = emptyMap(),
          conditions = emptyList(),
        ),
        ResponseField(
          type = ResponseField.Type.Named.Other("String"),
          responseName = "primaryFunction",
          fieldName = "primaryFunction",
          arguments = emptyMap(),
          conditions = emptyList(),
        ),
        ResponseField(
          type = ResponseField.Type.List(ResponseField.Type.Named.Object("Character")),
          responseName = "friends",
          fieldName = "friends",
          arguments = emptyMap(),
          conditions = emptyList(),
        )
      )

      override fun fromResponse(reader: ResponseReader, __typename: String?):
          TestQuery.Data.Hero.DroidHero {
        return reader.run {
          var __typename: String? = __typename
          var name: String? = null
          var primaryFunction: String? = null
          var friends: List<TestQuery.Data.Hero.DroidHero.Friends?>? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              1 -> name = readString(RESPONSE_FIELDS[1])
              2 -> primaryFunction = readString(RESPONSE_FIELDS[2])
              3 -> friends = readList<TestQuery.Data.Hero.DroidHero.Friends>(RESPONSE_FIELDS[3]) { reader ->
                reader.readObject<TestQuery.Data.Hero.DroidHero.Friends> { reader ->
                  Friends.fromResponse(reader)
                }
              }
              else -> break
            }
          }
          TestQuery.Data.Hero.DroidHero(
            __typename = __typename!!,
            name = name!!,
            primaryFunction = primaryFunction,
            friends = friends
          )
        }
      }

      override fun toResponse(writer: ResponseWriter, value: TestQuery.Data.Hero.DroidHero) {
        writer.writeString(RESPONSE_FIELDS[0], value.__typename)
        writer.writeString(RESPONSE_FIELDS[1], value.name)
        writer.writeString(RESPONSE_FIELDS[2], value.primaryFunction)
        writer.writeList(RESPONSE_FIELDS[3], value.friends) { value, listItemWriter ->
          listItemWriter.writeObject { writer ->
            Friends.toResponse(writer, value)
          }
        }
      }

      object Friends : ResponseAdapter<TestQuery.Data.Hero.DroidHero.Friends> {
        private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField(
            type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
            responseName = "name",
            fieldName = "name",
            arguments = emptyMap(),
            conditions = emptyList(),
          )
        )

        override fun fromResponse(reader: ResponseReader, __typename: String?):
            TestQuery.Data.Hero.DroidHero.Friends {
          return reader.run {
            var name: String? = null
            while(true) {
              when (selectField(RESPONSE_FIELDS)) {
                0 -> name = readString(RESPONSE_FIELDS[0])
                else -> break
              }
            }
            TestQuery.Data.Hero.DroidHero.Friends(
              name = name!!
            )
          }
        }

        override fun toResponse(writer: ResponseWriter,
            value: TestQuery.Data.Hero.DroidHero.Friends) {
          writer.writeString(RESPONSE_FIELDS[0], value.name)
        }
      }
    }

    object HumanHero : ResponseAdapter<TestQuery.Data.Hero.HumanHero> {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
          responseName = "__typename",
          fieldName = "__typename",
          arguments = emptyMap(),
          conditions = emptyList(),
        ),
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
          responseName = "name",
          fieldName = "name",
          arguments = emptyMap(),
          conditions = emptyList(),
        ),
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("URL")),
          responseName = "profileLink",
          fieldName = "profileLink",
          arguments = emptyMap(),
          conditions = emptyList(),
        ),
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Object("FriendsConnection")),
          responseName = "friendsConnection",
          fieldName = "friendsConnection",
          arguments = emptyMap(),
          conditions = emptyList(),
        )
      )

      override fun fromResponse(reader: ResponseReader, __typename: String?):
          TestQuery.Data.Hero.HumanHero {
        return reader.run {
          var __typename: String? = __typename
          var name: String? = null
          var profileLink: Any? = null
          var friendsConnection: TestQuery.Data.Hero.HumanHero.FriendsConnection? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              1 -> name = readString(RESPONSE_FIELDS[1])
              2 -> profileLink = readCustomScalar<Any>(RESPONSE_FIELDS[2])
              3 -> friendsConnection = readObject<TestQuery.Data.Hero.HumanHero.FriendsConnection>(RESPONSE_FIELDS[3]) { reader ->
                FriendsConnection.fromResponse(reader)
              }
              else -> break
            }
          }
          TestQuery.Data.Hero.HumanHero(
            __typename = __typename!!,
            name = name!!,
            profileLink = profileLink!!,
            friendsConnection = friendsConnection!!
          )
        }
      }

      override fun toResponse(writer: ResponseWriter, value: TestQuery.Data.Hero.HumanHero) {
        writer.writeString(RESPONSE_FIELDS[0], value.__typename)
        writer.writeString(RESPONSE_FIELDS[1], value.name)
        writer.writeCustom(RESPONSE_FIELDS[2], value.profileLink)
        writer.writeObject(RESPONSE_FIELDS[3]) { writer ->
          FriendsConnection.toResponse(writer, value.friendsConnection)
        }
      }

      object FriendsConnection : ResponseAdapter<TestQuery.Data.Hero.HumanHero.FriendsConnection> {
        private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
          ResponseField(
            type = ResponseField.Type.List(ResponseField.Type.Named.Object("FriendsEdge")),
            responseName = "edges",
            fieldName = "edges",
            arguments = emptyMap(),
            conditions = emptyList(),
          )
        )

        override fun fromResponse(reader: ResponseReader, __typename: String?):
            TestQuery.Data.Hero.HumanHero.FriendsConnection {
          return reader.run {
            var edges: List<TestQuery.Data.Hero.HumanHero.FriendsConnection.Edges?>? = null
            while(true) {
              when (selectField(RESPONSE_FIELDS)) {
                0 -> edges = readList<TestQuery.Data.Hero.HumanHero.FriendsConnection.Edges>(RESPONSE_FIELDS[0]) { reader ->
                  reader.readObject<TestQuery.Data.Hero.HumanHero.FriendsConnection.Edges> { reader ->
                    Edges.fromResponse(reader)
                  }
                }
                else -> break
              }
            }
            TestQuery.Data.Hero.HumanHero.FriendsConnection(
              edges = edges
            )
          }
        }

        override fun toResponse(writer: ResponseWriter,
            value: TestQuery.Data.Hero.HumanHero.FriendsConnection) {
          writer.writeList(RESPONSE_FIELDS[0], value.edges) { value, listItemWriter ->
            listItemWriter.writeObject { writer ->
              Edges.toResponse(writer, value)
            }
          }
        }

        object Edges : ResponseAdapter<TestQuery.Data.Hero.HumanHero.FriendsConnection.Edges> {
          private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
            ResponseField(
              type = ResponseField.Type.Named.Object("Character"),
              responseName = "node",
              fieldName = "node",
              arguments = emptyMap(),
              conditions = emptyList(),
            )
          )

          override fun fromResponse(reader: ResponseReader, __typename: String?):
              TestQuery.Data.Hero.HumanHero.FriendsConnection.Edges {
            return reader.run {
              var node: TestQuery.Data.Hero.HumanHero.FriendsConnection.Edges.Node? = null
              while(true) {
                when (selectField(RESPONSE_FIELDS)) {
                  0 -> node = readObject<TestQuery.Data.Hero.HumanHero.FriendsConnection.Edges.Node>(RESPONSE_FIELDS[0]) { reader ->
                    Node.fromResponse(reader)
                  }
                  else -> break
                }
              }
              TestQuery.Data.Hero.HumanHero.FriendsConnection.Edges(
                node = node
              )
            }
          }

          override fun toResponse(writer: ResponseWriter,
              value: TestQuery.Data.Hero.HumanHero.FriendsConnection.Edges) {
            if(value.node == null) {
              writer.writeObject(RESPONSE_FIELDS[0], null)
            } else {
              writer.writeObject(RESPONSE_FIELDS[0]) { writer ->
                Node.toResponse(writer, value.node)
              }
            }
          }

          object Node : ResponseAdapter<TestQuery.Data.Hero.HumanHero.FriendsConnection.Edges.Node>
              {
            private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
              ResponseField(
                type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
                responseName = "name",
                fieldName = "name",
                arguments = emptyMap(),
                conditions = emptyList(),
              )
            )

            override fun fromResponse(reader: ResponseReader, __typename: String?):
                TestQuery.Data.Hero.HumanHero.FriendsConnection.Edges.Node {
              return reader.run {
                var name: String? = null
                while(true) {
                  when (selectField(RESPONSE_FIELDS)) {
                    0 -> name = readString(RESPONSE_FIELDS[0])
                    else -> break
                  }
                }
                TestQuery.Data.Hero.HumanHero.FriendsConnection.Edges.Node(
                  name = name!!
                )
              }
            }

            override fun toResponse(writer: ResponseWriter,
                value: TestQuery.Data.Hero.HumanHero.FriendsConnection.Edges.Node) {
              writer.writeString(RESPONSE_FIELDS[0], value.name)
            }
          }
        }
      }
    }

    object OtherHero : ResponseAdapter<TestQuery.Data.Hero.OtherHero> {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField(
          type = ResponseField.Type.NotNull(ResponseField.Type.Named.Other("String")),
          responseName = "__typename",
          fieldName = "__typename",
          arguments = emptyMap(),
          conditions = emptyList(),
        )
      )

      override fun fromResponse(reader: ResponseReader, __typename: String?):
          TestQuery.Data.Hero.OtherHero {
        return reader.run {
          var __typename: String? = __typename
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              else -> break
            }
          }
          TestQuery.Data.Hero.OtherHero(
            __typename = __typename!!
          )
        }
      }

      override fun toResponse(writer: ResponseWriter, value: TestQuery.Data.Hero.OtherHero) {
        writer.writeString(RESPONSE_FIELDS[0], value.__typename)
      }
    }
  }
}
