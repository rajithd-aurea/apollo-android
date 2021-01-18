package com.apollographql.apollo.benchmark

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import androidx.sqlite.db.SupportSQLiteQueryBuilder
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class SQLRaw {
  @get:Rule
  val benchmarkRule = BenchmarkRule()


  lateinit var database: SupportSQLiteDatabase

  @ExperimentalTime
  @Before
  fun before() {
    val openHelper = FrameworkSQLiteOpenHelperFactory().create(
        SupportSQLiteOpenHelper.Configuration.builder(
            InstrumentationRegistry.getInstrumentation().context
        )
            .name("testRaw3")
            .noBackupDirectory(false)
            .callback(object : SupportSQLiteOpenHelper.Callback(2) {
              override fun onCreate(db: SupportSQLiteDatabase) {
                println("create testRaw3")
                try {
                  db.execSQL("""
                CREATE TABLE records (
                  _id INTEGER PRIMARY KEY AUTOINCREMENT,
                  key TEXT NOT NULL,
                  record TEXT NOT NULL
                );
              """.trimIndent())
                } catch (e: Exception) {
                  e.printStackTrace()
                }

                println("Insert values")
                db.beginTransaction()
                repeat(entries) {
                  db.execSQL("INSERT INTO records (key, record) VALUES ('$it', 'lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum');")
                }
                db.endTransaction()
                println("database created")

                val time = measureTime {
                  readChunked(db, 1)
                }
                println("Martin measureTime1 $time")

              }

              override fun onUpgrade(db: SupportSQLiteDatabase, oldVersion: Int, newVersion: Int) {
                println("Upgrading")
              }

            })
            .build()
    )

    database = openHelper.readableDatabase
  }

  companion object {
    val entries = 10 * 1000
    val count = 999

    private fun readChunked(database: SupportSQLiteDatabase, chunkSize: Int) {
      val variables = 0.until(chunkSize).map { "?" }.joinToString(",")
      var i = 0

      0.until(count).chunked(chunkSize).forEach {
        val query = SupportSQLiteQueryBuilder.builder("records")
            .columns(arrayOf("key", "record"))
            .selection("key IN ($variables)", it.map { it.toString() }.toTypedArray())
            .create()

        val cursor = database.query(query)

        cursor.use {
          while (cursor.moveToNext()) {
            println("Martin$i ${cursor.getString(1)}")
            i++
            //check (cursor.getString(1) == "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum")
          }
        }
      }
    }
  }

  @ExperimentalTime
  @Test
  fun readChunked1() = benchmarkRule.measureRepeated {
    readChunked(database, 1)
  }
}