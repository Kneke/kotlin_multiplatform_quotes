package de.kneke.common.util.db

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import de.kneke.common.db.QuoteDatabase

actual fun getDriver(dbName: String): SqlDriver  {
    val driver = JdbcSqliteDriver(dbName)
    QuoteDatabase.Schema.create(driver) // FIXME Maybe not necessary
    return driver
}
