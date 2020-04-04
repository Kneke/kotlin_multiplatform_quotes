package de.kneke.flutterapp

import android.util.Log
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugins.GeneratedPluginRegistrant
import de.kneke.flutterapp.channel.viewmodel.QuoteViewModelChannel

class MainActivity : FlutterActivity() {


    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine);

        // Register channels
        QuoteViewModelChannel(flutterEngine);
    }
}
