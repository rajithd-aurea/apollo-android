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

class SQL {
  @get:Rule
  val benchmarkRule = BenchmarkRule()

  val driver = AndroidSqliteDriver(
      ApolloDatabase.Schema,
      InstrumentationRegistry.getInstrumentation().context,
      "test",
      FrameworkSQLiteOpenHelperFactory(),
      useNoBackupDirectory = true
  )
  val database = ApolloDatabase(driver)
  val queries = database.cacheQueries

  val count = 999

  @Before
  fun before() {
    queries.deleteAll()
    queries.transaction {
      repeat(count) {
        queries.insert(it.toString(), "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum")
      }
    }
  }

  @Test
  fun read() = benchmarkRule.measureRepeated {
    val records = 0.until(count).map {
      queries.recordForKey(it.toString()).executeAsOne()
    }
    runWithTimingDisabled {
      check(records.size == count)
      records.forEach {
        check(it.record == "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum")
      }
    }

  }

  @Test
  fun readBatch() = benchmarkRule.measureRepeated {
    val records = queries.recordsForKeys(0.until(count).map { it.toString() }).executeAsList()
    runWithTimingDisabled {
      check(records.size == count)
      records.forEach {
        check(it.record == "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum")
      }
    }
  }

  @Test
  fun readBatchTransaction() = benchmarkRule.measureRepeated {
    queries.transaction {
      val records = queries.recordsForKeys(0.until(count).map { it.toString() }).executeAsList()
    }
  }

  @Test
  fun readTransation() = benchmarkRule.measureRepeated {
    queries.transaction {
      val records = 0.until(count).map {
        queries.recordForKey(it.toString()).executeAsOne()
      }
    }
  }
}