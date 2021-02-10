// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.nested_conditional_inline

import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.internal.InputFieldMarshaller
import com.apollographql.apollo.api.internal.QueryDocumentMinifier
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.example.nested_conditional_inline.adapter.TestQuery_ResponseAdapter
import com.example.nested_conditional_inline.type.Episode
import kotlin.Any
import kotlin.Double
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.jvm.Transient

@Suppress("NAME_SHADOWING", "UNUSED_ANONYMOUS_PARAMETER", "LocalVariableName",
    "RemoveExplicitTypeArguments", "NestedLambdaShadowedImplicitParameter", "PropertyName",
    "RemoveRedundantQualifierName")
data class TestQuery(
  val episode: Input<Episode> = Input.absent()
) : Query<TestQuery.Data> {
  @Transient
  private val variables: Operation.Variables = object : Operation.Variables() {
    override fun valueMap(): Map<String, Any?> = mutableMapOf<String, Any?>().apply {
      if (this@TestQuery.episode.defined) {
        this["episode"] = this@TestQuery.episode.value
      }
    }

    override fun marshaller(): InputFieldMarshaller {
      return InputFieldMarshaller.invoke { writer ->
        if (this@TestQuery.episode.defined) {
          writer.writeString("episode", this@TestQuery.episode.value?.rawValue)
        }
      }
    }
  }

  override fun operationId(): String = OPERATION_ID

  override fun queryDocument(): String = QUERY_DOCUMENT

  override fun variables(): Operation.Variables = variables

  override fun name(): String = OPERATION_NAME

  override fun adapter(): ResponseAdapter<Data> = TestQuery_ResponseAdapter
  /**
   * The query type, represents all of the entry points into our object graph
   */
  data class Data(
    val hero: Hero?
  ) : Operation.Data {
    /**
     * A character from the Star Wars universe
     */
    interface Hero {
      val __typename: String

      /**
       * The name of the character
       */
      val name: String

      interface Human : Hero {
        /**
         * This human's friends, or an empty list if they have none
         */
        val friends: List<Friends?>?

        /**
         * A character from the Star Wars universe
         */
        interface Friends {
          val __typename: String

          /**
           * The name of the character
           */
          val name: String

          interface Human : Friends {
            /**
             * Height in the preferred unit, default is meters
             */
            val height: Double?
          }

          companion object {
            fun Friends.asHuman(): Human? = this as? Human
          }
        }
      }

      interface Droid : Hero {
        /**
         * This droid's friends, or an empty list if they have none
         */
        val friends: List<Friends?>?

        /**
         * A character from the Star Wars universe
         */
        interface Friends {
          val __typename: String

          /**
           * The name of the character
           */
          val name: String

          interface Human : Friends {
            /**
             * Height in the preferred unit, default is meters
             */
            val height: Double?
          }

          companion object {
            fun Friends.asHuman(): Human? = this as? Human
          }
        }
      }

      data class HumanHero(
        override val __typename: String,
        /**
         * The name of the character
         */
        override val name: String,
        /**
         * This human's friends, or an empty list if they have none
         */
        override val friends: List<Friends?>?
      ) : Hero, Human {
        /**
         * A character from the Star Wars universe
         */
        interface Friends : Human.Friends {
          data class HumanFriends(
            override val __typename: String,
            /**
             * The name of the character
             */
            override val name: String,
            /**
             * Height in the preferred unit, default is meters
             */
            override val height: Double?
          ) : Human.Friends, Human.Friends.Human, Friends

          data class OtherFriends(
            override val __typename: String,
            /**
             * The name of the character
             */
            override val name: String
          ) : Human.Friends, Friends
        }
      }

      data class DroidHero(
        override val __typename: String,
        /**
         * The name of the character
         */
        override val name: String,
        /**
         * This droid's friends, or an empty list if they have none
         */
        override val friends: List<Friends?>?
      ) : Hero, Droid {
        /**
         * A character from the Star Wars universe
         */
        interface Friends : Droid.Friends {
          data class HumanFriends(
            override val __typename: String,
            /**
             * The name of the character
             */
            override val name: String,
            /**
             * Height in the preferred unit, default is meters
             */
            override val height: Double?
          ) : Droid.Friends, Droid.Friends.Human, Friends

          data class OtherFriends(
            override val __typename: String,
            /**
             * The name of the character
             */
            override val name: String
          ) : Droid.Friends, Friends
        }
      }

      data class OtherHero(
        override val __typename: String,
        /**
         * The name of the character
         */
        override val name: String
      ) : Hero

      companion object {
        fun Hero.asHuman(): Human? = this as? Human

        fun Hero.asDroid(): Droid? = this as? Droid
      }
    }
  }

  companion object {
    const val OPERATION_ID: String =
        "a9f066a7d1092096ab154f16f32114a4bd71e959b789f37879249cdf6309ea86"

    val QUERY_DOCUMENT: String = QueryDocumentMinifier.minify(
          """
          |query TestQuery(${'$'}episode: Episode) {
          |  hero(episode: ${'$'}episode) {
          |    __typename
          |    name
          |    ... on Human {
          |      friends {
          |        __typename
          |        name
          |        ... on Human {
          |          height(unit: FOOT)
          |        }
          |      }
          |    }
          |    ... on Droid {
          |      friends {
          |        __typename
          |        name
          |        ... on Human {
          |          height(unit: METER)
          |        }
          |      }
          |    }
          |  }
          |}
          """.trimMargin()
        )

    val OPERATION_NAME: String = "TestQuery"
  }
}
