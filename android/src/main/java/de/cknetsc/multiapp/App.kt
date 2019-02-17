package de.cknetsc.multiapp

import android.app.Application
import util.libContext
import util.logger.loggingActive

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        loggingActive = true // Enable multiplatform logging
        libContext = this.applicationContext

        //DatabaseDriver.setupDB(null)
    }
}