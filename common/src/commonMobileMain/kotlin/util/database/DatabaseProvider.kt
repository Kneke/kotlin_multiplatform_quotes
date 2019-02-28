package util.database

import de.cknetsc.multiapp.MyDatabase

expect object DatabaseProvider {

    val dbInstance: MyDatabase

    internal fun setupDB()

}