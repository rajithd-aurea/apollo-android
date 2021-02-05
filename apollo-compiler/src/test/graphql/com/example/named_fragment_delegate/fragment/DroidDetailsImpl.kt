// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.named_fragment_delegate.fragment

import com.apollographql.apollo.api.Fragment
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.internal.ResponseAdapter
import com.example.named_fragment_delegate.fragment.adapter.DroidDetailsImpl_ResponseAdapter
import kotlin.String
import kotlin.collections.List

class DroidDetailsImpl : Fragment<DroidDetailsImpl.Data> {
  override fun adapter(): ResponseAdapter<Data> {
    return DroidDetailsImpl_ResponseAdapter
  }

  override fun variables(): Operation.Variables = Operation.EMPTY_VARIABLES

  /**
   * An autonomous mechanical character in the Star Wars universe
   */
  data class Data(
    override val __typename: String = "Droid",
    /**
     * What others call this droid
     */
    override val name: String,
    /**
     * This droid's primary function
     */
    override val primaryFunction: String?,
    /**
     * This droid's friends, or an empty list if they have none
     */
    override val friends: List<Friends?>?
  ) : DroidDetails, Fragment.Data {
    /**
     * A character from the Star Wars universe
     */
    data class Friends(
      /**
       * The name of the character
       */
      override val name: String
    ) : DroidDetails.Friends
  }
}
