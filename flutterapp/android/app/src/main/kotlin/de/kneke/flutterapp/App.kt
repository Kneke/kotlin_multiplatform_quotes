package de.kneke.flutterapp

import io.flutter.app.FlutterApplication
import android.content.Context
import androidx.multidex.MultiDex
import de.kneke.common.util.libContext

class App : FlutterApplication() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)

        libContext = base
    }
}