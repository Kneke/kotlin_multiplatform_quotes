package de.kneke.common.db

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.ios.NativeSqliteDriver

actual fun getDriver(dbName: String): SqlDriver = NativeSqliteDriver(MyDatabase.Schema, dbName)