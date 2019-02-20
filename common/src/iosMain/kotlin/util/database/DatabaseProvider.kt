package util.database

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.ios.NativeSqliteDriver
import de.cknetsc.multiapp.MyDatabase
import kotlin.native.concurrent.AtomicReference
import kotlin.native.concurrent.freeze

actual object DatabaseProvider {

    actual val dbInstance: MyDatabase
        get() {
            if (driverInstance.value == null) setupDB()
            return MyDatabase(driverInstance.value!!)
        }

    private val driverInstance = AtomicReference<SqlDriver?>(null)

    internal actual fun setupDB() {
        driverInstance.value = NativeSqliteDriver(MyDatabase.Schema, "test.db").freeze()
    }

}