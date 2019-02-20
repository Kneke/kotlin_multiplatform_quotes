package de.cknetsc.multiapp

import android.app.Application
import util.libContext
import util.logger.loggingActive

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        // Enable multiplatform logging
        loggingActive = true

        // Share context with multiplatform common module
        libContext = this.applicationContext

    }
}