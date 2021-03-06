package de.kneke.common.util.db

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import de.kneke.common.db.MyDatabase
import de.kneke.common.util.libContext

actual fun getDriver(dbName: String): SqlDriver = AndroidSqliteDriver(MyDatabase.Schema, libContext!!, dbName)
