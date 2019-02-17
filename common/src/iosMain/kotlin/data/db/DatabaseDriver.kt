package data.db

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.ios.NativeSqliteDriver
import de.cknetsc.multiapp.MyDatabase
import kotlin.native.concurrent.AtomicReference
import kotlin.native.concurrent.freeze

actual object DatabaseDriver {
    private val driverRef = AtomicReference<SqlDriver?>(null)

    actual val dbInstance: MyDatabase
        get() = MyDatabase(driverRef.value!!)

    actual fun setupDB() {
        driverRef.value = NativeSqliteDriver(MyDatabase.Schema, "test.db").freeze()
    }

}