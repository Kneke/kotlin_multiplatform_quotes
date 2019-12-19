package de.kneke.common.util.db

import com.squareup.sqldelight.db.SqlDriver
import de.kneke.common.db.QuoteDatabase

expect fun getDriver(dbName: String): SqlDriver

class DatabaseHelper(dbName: String) {
    private val driver: SqlDriver = getDriver(dbName)
    val database: QuoteDatabase = QuoteDatabase(driver)
}