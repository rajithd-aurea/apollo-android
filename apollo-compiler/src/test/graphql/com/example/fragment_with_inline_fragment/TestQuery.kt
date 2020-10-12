// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.fragment_with_inline_fragment

import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.OperationName
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.ScalarTypeAdapters
import com.apollographql.apollo.api.ScalarTypeAdapters.Companion.DEFAULT
import com.apollographql.apollo.api.internal.OperationRequestBodyComposer
import com.apollographql.apollo.api.internal.QueryDocumentMinifier
import com.apollographql.apollo.api.internal.ResponseFieldMapper
import com.apollographql.apollo.api.internal.ResponseFieldMarshaller
import com.apollographql.apollo.api.internal.ResponseReader
import com.apollographql.apollo.api.internal.SimpleOperationResponseParser
import com.apollographql.apollo.api.internal.Throws
import com.example.fragment_with_inline_fragment.fragment.DroidDetails
import com.example.fragment_with_inline_fragment.fragment.HeroDetails
import com.example.fragment_with_inline_fragment.fragment.HumanDetails
import com.example.fragment_with_inline_fragment.type.Episode
import kotlin.Array
import kotlin.Boolean
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import okio.Buffer
import okio.BufferedSource
import okio.ByteString
import okio.IOException

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
class TestQuery : Query<TestQuery.Data, TestQuery.Data, Operation.Variables> {
  override fun operationId(): String = OPERATION_ID
  override fun queryDocument(): String = QUERY_DOCUMENT
  override fun wrapData(data: Data?): Data? = data
  override fun variables(): Operation.Variables = Operation.EMPTY_VARIABLES
  override fun name(): OperationName = OPERATION_NAME
  override fun responseFieldMapper(): ResponseFieldMapper<Data> = ResponseFieldMapper.invoke {
    Data(it)
  }

  @Throws(IOException::class)
  override fun parse(source: BufferedSource, scalarTypeAdapters: ScalarTypeAdapters): Response<Data>
      = SimpleOperationResponseParser.parse(source, this, scalarTypeAdapters)

  @Throws(IOException::class)
  override fun parse(byteString: ByteString, scalarTypeAdapters: ScalarTypeAdapters): Response<Data>
      = parse(Buffer().write(byteString), scalarTypeAdapters)

  @Throws(IOException::class)
  override fun parse(source: BufferedSource): Response<Data> = parse(source, DEFAULT)

  @Throws(IOException::class)
  override fun parse(byteString: ByteString): Response<Data> = parse(byteString, DEFAULT)

  override fun composeRequestBody(scalarTypeAdapters: ScalarTypeAdapters): ByteString =
      OperationRequestBodyComposer.compose(
    operation = this,
    autoPersistQueries = false,
    withQueryDocument = true,
    scalarTypeAdapters = scalarTypeAdapters
  )

  override fun composeRequestBody(): ByteString = OperationRequestBodyComposer.compose(
    operation = this,
    autoPersistQueries = false,
    withQueryDocument = true,
    scalarTypeAdapters = DEFAULT
  )

  override fun composeRequestBody(
    autoPersistQueries: Boolean,
    withQueryDocument: Boolean,
    scalarTypeAdapters: ScalarTypeAdapters
  ): ByteString = OperationRequestBodyComposer.compose(
    operation = this,
    autoPersistQueries = autoPersistQueries,
    withQueryDocument = withQueryDocument,
    scalarTypeAdapters = scalarTypeAdapters
  )

  /**
   * A character from the Star Wars universe
   */
  interface Node {
    val __typename: String

    /**
     * The name of the character
     */
    val name: String

    fun marshaller(): ResponseFieldMarshaller
  }

  /**
   * An edge object for a character's friends
   */
  interface Edge {
    val __typename: String

    /**
     * The character represented by this friendship edge
     */
    val node: Node?

    fun marshaller(): ResponseFieldMarshaller
  }

  /**
   * A connection object for a character's friends
   */
  interface FriendsConnection {
    val __typename: String

    /**
     * The total number of friends
     */
    val totalCount: Int?

    /**
     * The edges for each of the character's friends.
     */
    val edges: List<Edge?>?

    fun marshaller(): ResponseFieldMarshaller
  }

  /**
   * An autonomous mechanical character in the Star Wars universe
   */
  interface Droid : Hero {
    override val __typename: String

    /**
     * What others call this droid
     */
    override val name: String

    /**
     * The friends of the droid exposed as a connection with edges
     */
    val friendsConnection: FriendsConnection

    override fun marshaller(): ResponseFieldMarshaller
  }

  /**
   * A character from the Star Wars universe
   */
  data class Node1(
    override val __typename: String = "Character",
    /**
     * The name of the character
     */
    override val name: String
  ) : HeroDetails.Node, Node {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0], this@Node1.__typename)
        writer.writeString(RESPONSE_FIELDS[1], this@Node1.name)
      }
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null),
        ResponseField.forString("name", "name", null, false, null)
      )

      operator fun invoke(reader: ResponseReader, __typename: String? = null): Node1 {
        return reader.run {
          var __typename: String? = __typename
          var name: String? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              1 -> name = readString(RESPONSE_FIELDS[1])
              else -> break
            }
          }
          Node1(
            __typename = __typename!!,
            name = name!!
          )
        }
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<Node1> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * An edge object for a character's friends
   */
  data class Edge1(
    override val __typename: String = "FriendsEdge",
    /**
     * The character represented by this friendship edge
     */
    override val node: Node1?
  ) : HeroDetails.Edge, Edge {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0], this@Edge1.__typename)
        writer.writeObject(RESPONSE_FIELDS[1], this@Edge1.node?.marshaller())
      }
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null),
        ResponseField.forObject("node", "node", null, true, null)
      )

      operator fun invoke(reader: ResponseReader, __typename: String? = null): Edge1 {
        return reader.run {
          var __typename: String? = __typename
          var node: Node1? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              1 -> node = readObject<Node1>(RESPONSE_FIELDS[1]) { reader ->
                Node1(reader)
              }
              else -> break
            }
          }
          Edge1(
            __typename = __typename!!,
            node = node
          )
        }
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<Edge1> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * A connection object for a character's friends
   */
  data class FriendsConnection1(
    override val __typename: String = "FriendsConnection",
    /**
     * The total number of friends
     */
    override val totalCount: Int?,
    /**
     * The edges for each of the character's friends.
     */
    override val edges: List<Edge1?>?
  ) : HeroDetails.FriendsConnection, FriendsConnection {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0], this@FriendsConnection1.__typename)
        writer.writeInt(RESPONSE_FIELDS[1], this@FriendsConnection1.totalCount)
        writer.writeList(RESPONSE_FIELDS[2],
            this@FriendsConnection1.edges) { value, listItemWriter ->
          value?.forEach { value ->
            listItemWriter.writeObject(value?.marshaller())}
        }
      }
    }

    fun edgesFilterNotNull(): List<Edge1>? = edges?.filterNotNull()

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null),
        ResponseField.forInt("totalCount", "totalCount", null, true, null),
        ResponseField.forList("edges", "edges", null, true, null)
      )

      operator fun invoke(reader: ResponseReader, __typename: String? = null): FriendsConnection1 {
        return reader.run {
          var __typename: String? = __typename
          var totalCount: Int? = null
          var edges: List<Edge1?>? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              1 -> totalCount = readInt(RESPONSE_FIELDS[1])
              2 -> edges = readList<Edge1>(RESPONSE_FIELDS[2]) { reader ->
                reader.readObject<Edge1> { reader ->
                  Edge1(reader)
                }
              }
              else -> break
            }
          }
          FriendsConnection1(
            __typename = __typename!!,
            totalCount = totalCount,
            edges = edges
          )
        }
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<FriendsConnection1> = ResponseFieldMapper { invoke(it) }
    }
  }

  data class HeroDetailsDroidDroidDetailsImpl(
    override val __typename: String,
    /**
     * The name of the character
     */
    override val name: String,
    /**
     * The friends of the character exposed as a connection with edges
     */
    override val friendsConnection: FriendsConnection1,
    /**
     * This droid's primary function
     */
    override val primaryFunction: String?,
    /**
     * The movies this character appears in
     */
    override val appearsIn: List<Episode?>
  ) : HeroDetails, Droid, DroidDetails, Hero {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0], this@HeroDetailsDroidDroidDetailsImpl.__typename)
        writer.writeString(RESPONSE_FIELDS[1], this@HeroDetailsDroidDroidDetailsImpl.name)
        writer.writeObject(RESPONSE_FIELDS[2],
            this@HeroDetailsDroidDroidDetailsImpl.friendsConnection.marshaller())
        writer.writeString(RESPONSE_FIELDS[3],
            this@HeroDetailsDroidDroidDetailsImpl.primaryFunction)
        writer.writeList(RESPONSE_FIELDS[4],
            this@HeroDetailsDroidDroidDetailsImpl.appearsIn) { value, listItemWriter ->
          value?.forEach { value ->
            listItemWriter.writeString(value?.rawValue)}
        }
      }
    }

    fun appearsInFilterNotNull(): List<Episode> = appearsIn.filterNotNull()

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null),
        ResponseField.forString("name", "name", null, false, null),
        ResponseField.forObject("friendsConnection", "friendsConnection", null, false, null),
        ResponseField.forString("primaryFunction", "primaryFunction", null, true, null),
        ResponseField.forList("appearsIn", "appearsIn", null, false, null)
      )

      operator fun invoke(reader: ResponseReader, __typename: String? = null):
          HeroDetailsDroidDroidDetailsImpl {
        return reader.run {
          var __typename: String? = __typename
          var name: String? = null
          var friendsConnection: FriendsConnection1? = null
          var primaryFunction: String? = null
          var appearsIn: List<Episode?>? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              1 -> name = readString(RESPONSE_FIELDS[1])
              2 -> friendsConnection = readObject<FriendsConnection1>(RESPONSE_FIELDS[2]) { reader ->
                FriendsConnection1(reader)
              }
              3 -> primaryFunction = readString(RESPONSE_FIELDS[3])
              4 -> appearsIn = readList<Episode>(RESPONSE_FIELDS[4]) { reader ->
                Episode.safeValueOf(reader.readString())
              }
              else -> break
            }
          }
          HeroDetailsDroidDroidDetailsImpl(
            __typename = __typename!!,
            name = name!!,
            friendsConnection = friendsConnection!!,
            primaryFunction = primaryFunction,
            appearsIn = appearsIn!!
          )
        }
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<HeroDetailsDroidDroidDetailsImpl> = ResponseFieldMapper {
          invoke(it) }
    }
  }

  /**
   * A character from the Star Wars universe
   */
  data class Node2(
    override val __typename: String = "Character",
    /**
     * The name of the character
     */
    override val name: String
  ) : HeroDetails.Node {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0], this@Node2.__typename)
        writer.writeString(RESPONSE_FIELDS[1], this@Node2.name)
      }
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null),
        ResponseField.forString("name", "name", null, false, null)
      )

      operator fun invoke(reader: ResponseReader, __typename: String? = null): Node2 {
        return reader.run {
          var __typename: String? = __typename
          var name: String? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              1 -> name = readString(RESPONSE_FIELDS[1])
              else -> break
            }
          }
          Node2(
            __typename = __typename!!,
            name = name!!
          )
        }
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<Node2> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * An edge object for a character's friends
   */
  data class Edge2(
    override val __typename: String = "FriendsEdge",
    /**
     * The character represented by this friendship edge
     */
    override val node: Node2?
  ) : HeroDetails.Edge {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0], this@Edge2.__typename)
        writer.writeObject(RESPONSE_FIELDS[1], this@Edge2.node?.marshaller())
      }
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null),
        ResponseField.forObject("node", "node", null, true, null)
      )

      operator fun invoke(reader: ResponseReader, __typename: String? = null): Edge2 {
        return reader.run {
          var __typename: String? = __typename
          var node: Node2? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              1 -> node = readObject<Node2>(RESPONSE_FIELDS[1]) { reader ->
                Node2(reader)
              }
              else -> break
            }
          }
          Edge2(
            __typename = __typename!!,
            node = node
          )
        }
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<Edge2> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * A connection object for a character's friends
   */
  data class FriendsConnection2(
    override val __typename: String = "FriendsConnection",
    /**
     * The total number of friends
     */
    override val totalCount: Int?,
    /**
     * The edges for each of the character's friends.
     */
    override val edges: List<Edge2?>?
  ) : HeroDetails.FriendsConnection {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0], this@FriendsConnection2.__typename)
        writer.writeInt(RESPONSE_FIELDS[1], this@FriendsConnection2.totalCount)
        writer.writeList(RESPONSE_FIELDS[2],
            this@FriendsConnection2.edges) { value, listItemWriter ->
          value?.forEach { value ->
            listItemWriter.writeObject(value?.marshaller())}
        }
      }
    }

    fun edgesFilterNotNull(): List<Edge2>? = edges?.filterNotNull()

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null),
        ResponseField.forInt("totalCount", "totalCount", null, true, null),
        ResponseField.forList("edges", "edges", null, true, null)
      )

      operator fun invoke(reader: ResponseReader, __typename: String? = null): FriendsConnection2 {
        return reader.run {
          var __typename: String? = __typename
          var totalCount: Int? = null
          var edges: List<Edge2?>? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              1 -> totalCount = readInt(RESPONSE_FIELDS[1])
              2 -> edges = readList<Edge2>(RESPONSE_FIELDS[2]) { reader ->
                reader.readObject<Edge2> { reader ->
                  Edge2(reader)
                }
              }
              else -> break
            }
          }
          FriendsConnection2(
            __typename = __typename!!,
            totalCount = totalCount,
            edges = edges
          )
        }
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<FriendsConnection2> = ResponseFieldMapper { invoke(it) }
    }
  }

  data class HeroDetailsHumanDetailsImpl(
    override val __typename: String,
    /**
     * The name of the character
     */
    override val name: String,
    /**
     * The friends of the character exposed as a connection with edges
     */
    override val friendsConnection: FriendsConnection2,
    /**
     * The movies this character appears in
     */
    override val appearsIn: List<Episode?>
  ) : HeroDetails, HumanDetails, Hero {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0], this@HeroDetailsHumanDetailsImpl.__typename)
        writer.writeString(RESPONSE_FIELDS[1], this@HeroDetailsHumanDetailsImpl.name)
        writer.writeObject(RESPONSE_FIELDS[2],
            this@HeroDetailsHumanDetailsImpl.friendsConnection.marshaller())
        writer.writeList(RESPONSE_FIELDS[3],
            this@HeroDetailsHumanDetailsImpl.appearsIn) { value, listItemWriter ->
          value?.forEach { value ->
            listItemWriter.writeString(value?.rawValue)}
        }
      }
    }

    fun appearsInFilterNotNull(): List<Episode> = appearsIn.filterNotNull()

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null),
        ResponseField.forString("name", "name", null, false, null),
        ResponseField.forObject("friendsConnection", "friendsConnection", null, false, null),
        ResponseField.forList("appearsIn", "appearsIn", null, false, null)
      )

      operator fun invoke(reader: ResponseReader, __typename: String? = null):
          HeroDetailsHumanDetailsImpl {
        return reader.run {
          var __typename: String? = __typename
          var name: String? = null
          var friendsConnection: FriendsConnection2? = null
          var appearsIn: List<Episode?>? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              1 -> name = readString(RESPONSE_FIELDS[1])
              2 -> friendsConnection = readObject<FriendsConnection2>(RESPONSE_FIELDS[2]) { reader ->
                FriendsConnection2(reader)
              }
              3 -> appearsIn = readList<Episode>(RESPONSE_FIELDS[3]) { reader ->
                Episode.safeValueOf(reader.readString())
              }
              else -> break
            }
          }
          HeroDetailsHumanDetailsImpl(
            __typename = __typename!!,
            name = name!!,
            friendsConnection = friendsConnection!!,
            appearsIn = appearsIn!!
          )
        }
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<HeroDetailsHumanDetailsImpl> = ResponseFieldMapper {
          invoke(it) }
    }
  }

  /**
   * A character from the Star Wars universe
   */
  data class OtherHero(
    override val __typename: String = "Character",
    /**
     * The name of the character
     */
    override val name: String,
    /**
     * The movies this character appears in
     */
    override val appearsIn: List<Episode?>
  ) : Hero {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeString(RESPONSE_FIELDS[0], this@OtherHero.__typename)
        writer.writeString(RESPONSE_FIELDS[1], this@OtherHero.name)
        writer.writeList(RESPONSE_FIELDS[2], this@OtherHero.appearsIn) { value, listItemWriter ->
          value?.forEach { value ->
            listItemWriter.writeString(value?.rawValue)}
        }
      }
    }

    fun appearsInFilterNotNull(): List<Episode> = appearsIn.filterNotNull()

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null),
        ResponseField.forString("name", "name", null, false, null),
        ResponseField.forList("appearsIn", "appearsIn", null, false, null)
      )

      operator fun invoke(reader: ResponseReader, __typename: String? = null): OtherHero {
        return reader.run {
          var __typename: String? = __typename
          var name: String? = null
          var appearsIn: List<Episode?>? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> __typename = readString(RESPONSE_FIELDS[0])
              1 -> name = readString(RESPONSE_FIELDS[1])
              2 -> appearsIn = readList<Episode>(RESPONSE_FIELDS[2]) { reader ->
                Episode.safeValueOf(reader.readString())
              }
              else -> break
            }
          }
          OtherHero(
            __typename = __typename!!,
            name = name!!,
            appearsIn = appearsIn!!
          )
        }
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<OtherHero> = ResponseFieldMapper { invoke(it) }
    }
  }

  /**
   * A character from the Star Wars universe
   */
  interface Hero {
    val __typename: String

    /**
     * The name of the character
     */
    val name: String

    /**
     * The movies this character appears in
     */
    val appearsIn: List<Episode?>

    fun asHeroDetails(): HeroDetails? = this as? HeroDetails

    fun asDroid(): Droid? = this as? Droid

    fun asDroidDetails(): DroidDetails? = this as? DroidDetails

    fun asHumanDetails(): HumanDetails? = this as? HumanDetails

    fun marshaller(): ResponseFieldMarshaller

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forString("__typename", "__typename", null, false, null)
      )

      operator fun invoke(reader: ResponseReader, __typename: String? = null): Hero {
        val typename = __typename ?: reader.readString(RESPONSE_FIELDS[0])
        return when(typename) {
          "Droid" -> HeroDetailsDroidDroidDetailsImpl(reader, typename)
          "Human" -> HeroDetailsHumanDetailsImpl(reader, typename)
          else -> OtherHero(reader, typename)
        }
      }
    }
  }

  /**
   * Data from the response after executing this GraphQL operation
   */
  data class Data(
    val hero: Hero?
  ) : Operation.Data {
    override fun marshaller(): ResponseFieldMarshaller {
      return ResponseFieldMarshaller.invoke { writer ->
        writer.writeObject(RESPONSE_FIELDS[0], this@Data.hero?.marshaller())
      }
    }

    companion object {
      private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
        ResponseField.forObject("hero", "hero", null, true, null)
      )

      operator fun invoke(reader: ResponseReader, __typename: String? = null): Data {
        return reader.run {
          var hero: Hero? = null
          while(true) {
            when (selectField(RESPONSE_FIELDS)) {
              0 -> hero = readObject<Hero>(RESPONSE_FIELDS[0]) { reader ->
                Hero(reader)
              }
              else -> break
            }
          }
          Data(
            hero = hero
          )
        }
      }

      @Suppress("FunctionName")
      fun Mapper(): ResponseFieldMapper<Data> = ResponseFieldMapper { invoke(it) }
    }
  }

  companion object {
    const val OPERATION_ID: String =
        "cf2801bb0424f62ecf3504cedcf40d0fc0f5b5b75bdaf1a9febb5e63bea91306"

    val QUERY_DOCUMENT: String = QueryDocumentMinifier.minify(
          """
          |query TestQuery {
          |  hero {
          |    __typename
          |    name
          |    ...HeroDetails
          |    appearsIn
          |  }
          |}
          |fragment HeroDetails on Character {
          |  __typename
          |  ... HumanDetails
          |  ... on Droid {
          |    ...DroidDetails
          |  }
          |  name
          |  friendsConnection {
          |    __typename
          |    totalCount
          |    edges {
          |      __typename
          |      node {
          |        __typename
          |        name
          |      }
          |    }
          |  }
          |}
          |fragment HumanDetails on Human {
          |  __typename
          |  name
          |}
          |fragment DroidDetails on Droid {
          |  __typename
          |  name
          |  primaryFunction
          |}
          """.trimMargin()
        )

    val OPERATION_NAME: OperationName = object : OperationName {
      override fun name(): String = "TestQuery"
    }
  }
}
