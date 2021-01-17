package com.apollographql.apollo.benchmark

import Utils.bufferedSource
import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.platform.app.InstrumentationRegistry
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.CustomScalarAdapters
import com.apollographql.apollo.api.internal.json.JsonReader
import com.apollographql.apollo.api.parse
import com.apollographql.apollo.benchmark.moshi.Query
import com.apollographql.apollo.cache.CacheHeaders
import com.apollographql.apollo.cache.normalized.CacheKeyResolver
import com.apollographql.apollo.cache.normalized.Record
import com.apollographql.apollo.cache.normalized.internal.ReadableStore
import com.apollographql.apollo.cache.normalized.internal.normalize
import com.apollographql.apollo.cache.normalized.internal.readDataFromCache
import com.apollographql.apollo.cache.normalized.internal.streamDataFromCache
import com.apollographql.apollo.cache.normalized.sql.SqlNormalizedCache
import com.apollographql.apollo.cache.normalized.sql.SqlNormalizedCacheFactory
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.fetcher.ApolloResponseFetchers
import com.squareup.moshi.Moshi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class Benchmark {
  @get:Rule
  val benchmarkRule = BenchmarkRule()

  private val operation = GetResponseQuery()

  private val moshiAdapter = Moshi.Builder().build().adapter(Query::class.java)

  @Test
  fun moshi() = benchmarkRule.measureRepeated {
    val bufferedSource = runWithTimingDisabled {
      bufferedSource()
    }

    moshiAdapter.fromJson(bufferedSource)
  }

  @Test
  fun apollo() = benchmarkRule.measureRepeated {
    val bufferedSource = runWithTimingDisabled {
      bufferedSource()
    }

    operation.parse(bufferedSource)
  }

  @Test
  fun apolloParseAndNormalize() = benchmarkRule.measureRepeated {
    val bufferedSource = runWithTimingDisabled {
      bufferedSource()
    }

    val data = operation.parse(bufferedSource).data!!
    val records = operation.normalize(data, CustomScalarAdapters.DEFAULT, CacheKeyResolver.DEFAULT)
  }

  lateinit var apolloClient: ApolloClient

  @Before
  fun setup() {
    val cache = SqlNormalizedCacheFactory(context = InstrumentationRegistry.getInstrumentation().context)

    apolloClient = ApolloClient.builder()
        .serverUrl("https://example.com")
        .normalizedCache(cache)
        .build()

    val data = operation.parse(bufferedSource()).data!!

    apolloClient.apolloStore.writeOperation(operation, data)
  }

  @Test
  fun apolloReadCache() = benchmarkRule.measureRepeated {
    runBlocking {
      val data = apolloClient.query(operation).responseFetcher(ApolloResponseFetchers.CACHE_ONLY).await()
      //println(data)
    }
  }
}