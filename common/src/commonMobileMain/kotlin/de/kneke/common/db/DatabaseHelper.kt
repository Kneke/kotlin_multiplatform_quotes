package de.kneke.common.db

import com.squareup.sqldelight.db.SqlDriver

expect fun getDriver(dbName: String): SqlDriver

class DatabaseHelper(dbName: String) {
    private val driver: SqlDriver = getDriver(dbName)
    val database: MyDatabase = MyDatabase(driver)
}