package de.kneke.common.util.db

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import de.kneke.common.db.MyDatabase
import de.kneke.common.util.logger.log

actual fun getDriver(dbName: String): SqlDriver  {
    val driver = JdbcSqliteDriver("jdbc:sqlite:$dbName")
    try {
        MyDatabase.Schema.create(driver)
    } catch (e: Exception) {
        log(e, "SQLDelight Database Error:")
    }
    return driver
}
