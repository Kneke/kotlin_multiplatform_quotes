package util.database

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import de.cknetsc.multiapp.MyDatabase

actual object DatabaseProvider {

    private var driverInstance: SqlDriver? = null

    actual val dbInstance: MyDatabase
        get() {
            if (driverInstance == null) setupDB()
            return MyDatabase(driverInstance!!)
        }

    internal actual fun setupDB() {
        driverInstance = JdbcSqliteDriver()
    }

}