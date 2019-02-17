package data.db

import de.cknetsc.multiapp.MyDatabase

expect object DatabaseDriver {

    val dbInstance: MyDatabase

    fun setupDB()

}