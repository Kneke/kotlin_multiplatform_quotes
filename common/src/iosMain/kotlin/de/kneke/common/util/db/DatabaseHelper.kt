package de.kneke.common.util.db

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.ios.NativeSqliteDriver
import de.kneke.common.db.QuoteDatabase

actual fun getDriver(dbName: String): SqlDriver = NativeSqliteDriver(QuoteDatabase.Schema, dbName)