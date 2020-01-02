package de.kneke.multiapp

import androidx.multidex.MultiDexApplication
import com.google.android.gms.ads.MobileAds
import de.kneke.common.util.logger.setupLogger
import de.kneke.common.util.libContext

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        // Enable multiplatform logging
        setupLogger(true, "ANDROID-LOGGER")

        // Share context with multiplatform common module
        libContext = this.applicationContext

        MobileAds.initialize(this, "ca-app-pub-8543132817382656~2127824293")

    }
}