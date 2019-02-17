package data.db

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import de.cknetsc.multiapp.MyDatabase
import util.libContext

actual object DatabaseDriver {
    private var driverInstance: SqlDriver? = null

    actual val dbInstance: MyDatabase
        get() = MyDatabase(driverInstance!!)

    actual fun setupDB() {
        driverInstance =  AndroidSqliteDriver(MyDatabase.Schema, libContext!!, "test.db")
    }
}
