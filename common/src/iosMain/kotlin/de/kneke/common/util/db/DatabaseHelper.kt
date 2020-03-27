package de.kneke.common.util.db

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import de.kneke.common.db.MyDatabase

actual fun getDriver(dbName: String): SqlDriver = NativeSqliteDriver(MyDatabase.Schema, dbName)