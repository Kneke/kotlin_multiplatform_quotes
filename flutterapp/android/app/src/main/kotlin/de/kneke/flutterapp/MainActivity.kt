package de.kneke.flutterapp

import android.util.Log
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugins.GeneratedPluginRegistrant
import de.kneke.flutterapp.channel.config.QuoteViewModelChannel
import de.kneke.flutterapp.channel.config.LegalResourceChannel

class MainActivity : FlutterActivity() {

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine);

        // Register channels
        QuoteViewModelChannel(flutterEngine);
        LegalResourceChannel(flutterEngine);
    }
}
