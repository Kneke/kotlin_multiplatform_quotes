package data.db

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import de.cknetsc.multiapp.MyDatabase

actual object DatabaseDriver {
    private var driverInstance: SqlDriver? = null

    actual val dbInstance: MyDatabase
        get() = MyDatabase(driverInstance!!)

    actual fun setupDB() {
        driverInstance = JdbcSqliteDriver()
    }

}