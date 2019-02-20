package util.database

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import de.cknetsc.multiapp.MyDatabase
import util.libContext

actual object DatabaseProvider {

    private var driverInstance: SqlDriver? = null

    actual val dbInstance: MyDatabase
        get() {
            if (driverInstance == null) setupDB()
            return MyDatabase(driverInstance!!)
        }

    internal actual fun setupDB() {
        driverInstance = AndroidSqliteDriver(MyDatabase.Schema, libContext!!, "test.db")
    }
}
