package de.kneke.multiapp

import android.app.Application
import de.kneke.common.util.libContext
import de.kneke.common.util.logger.loggingActive

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // Enable multiplatform logging
        loggingActive = true

        // Share context with multiplatform common module
        libContext = this.applicationContext

    }
}