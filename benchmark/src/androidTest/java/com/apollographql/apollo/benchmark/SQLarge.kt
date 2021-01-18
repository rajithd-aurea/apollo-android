package com.apollographql.apollo.benchmark

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.platform.app.InstrumentationRegistry
import com.apollographql.apollo.cache.normalized.Record
import com.apollographql.apollo.cache.normalized.sql.ApolloDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random

class SQLarge {
  @get:Rule
  val benchmarkRule = BenchmarkRule()

  val driver = AndroidSqliteDriver(
      ApolloDatabase.Schema,
      InstrumentationRegistry.getInstrumentation().context,
      "testLarge",
      FrameworkSQLiteOpenHelperFactory(),
      useNoBackupDirectory = true
  )
  val database = ApolloDatabase(driver)
  val queries = database.cacheQueries

  val entries = 1000*1000
  val count = 999

  @Before
  fun before() {
    queries.deleteAll()
    queries.transaction {
      repeat(entries) {
        queries.insert(it.toString(), "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum")
      }
    }
  }

  private fun readChunked(chunkSize: Int) {
    0.until(count).chunked(chunkSize).forEach {
      queries.recordsForKeys(it.map { it.toString() }).executeAsList()
    }
  }

  @Test
  fun explain() = benchmarkRule.measureRepeated  {
    println("explain")
    println(queries.explain().executeAsOne())

  }

//  @Test
//  fun readChunked1() = benchmarkRule.measureRepeated {
//    readChunked(1)
//  }
//  @Test
//  fun readChunked2() = benchmarkRule.measureRepeated {
//    readChunked(2)
//  }
//  @Test
//  fun readChunked4() = benchmarkRule.measureRepeated {
//    readChunked(4)
//  }
//  @Test
//  fun readChunked8() = benchmarkRule.measureRepeated {
//    readChunked(8)
//  }
//  @Test
//  fun readChunked16() = benchmarkRule.measureRepeated {
//    readChunked(16)
//  }
//  @Test
//  fun readChunked32() = benchmarkRule.measureRepeated {
//    readChunked(32)
//  }
//  @Test
//  fun readChunked64() = benchmarkRule.measureRepeated {
//    readChunked(64)
//  }
//  @Test
//  fun readChunked128() = benchmarkRule.measureRepeated {
//    readChunked(128)
//  }
//  @Test
//  fun readChunked256() = benchmarkRule.measureRepeated {
//    readChunked(256)
//  }
//  @Test
//  fun readChunked512() = benchmarkRule.measureRepeated {
//    readChunked(512)
//  }
//  @Test
//  fun readChunked1024() = benchmarkRule.measureRepeated {
//    readChunked(1024)
//  }
}