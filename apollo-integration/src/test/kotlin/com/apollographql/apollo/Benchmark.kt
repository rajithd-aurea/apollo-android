package com.apollographql.apollo

import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.parse
import com.apollographql.apollo.cache.CacheHeaders
import com.apollographql.apollo.cache.normalized.CacheKeyResolver
import com.apollographql.apollo.cache.normalized.Record
import com.apollographql.apollo.cache.normalized.RecordFieldJsonAdapter
import com.apollographql.apollo.cache.normalized.internal.ReadableStore
import com.apollographql.apollo.cache.normalized.internal.normalize
import com.apollographql.apollo.cache.normalized.internal.readDataFromCache
import com.apollographql.apollo.cache.normalized.internal.streamDataFromCache
import com.apollographql.apollo.cache.normalized.sql.SqlNormalizedCache
import com.apollographql.apollo.cache.normalized.sql.SqlNormalizedCacheFactory
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.fetcher.ApolloResponseFetchers
import com.apollographql.apollo.integration.benchmark.GetResponseQuery
import kotlinx.coroutines.runBlocking
import okio.Buffer
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals


/**
 * This class duplicates some of the logic of the `benchmark` project. This is because it is easier to work in integration test
 * (debugger works + no need to publish to mavenLocal in between runs)
 *
 * Proper measurements should be done in the `benchmark` project ultimately
 */
class BenchmarkTest {

  lateinit var apolloClient: ApolloClient
  lateinit var cache: SqlNormalizedCache

  private val operation = GetResponseQuery()

  @Before
  fun setup() {
    val cache = SqlNormalizedCacheFactory("jdbc:sqlite:")

    val bufferedSource = Buffer().writeUtf8(Utils.readFileToString(Utils::class.java, "/largesample.json"))

    apolloClient = ApolloClient.builder()
        .serverUrl("https://example.com")
        .normalizedCache(cache)
        .build()

    val data = operation.parse(bufferedSource).data!!

    apolloClient.apolloStore.writeOperation(operation, data)
  }

  @Test
  fun apolloReadCache() = runBlocking {
    val data = apolloClient.query(operation).responseFetcher(ApolloResponseFetchers.CACHE_ONLY).await()
    println(data)
  }
}
