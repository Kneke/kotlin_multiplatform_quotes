package de.kneke.flutterapp

import android.util.Log
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant
import kotlin.random.Random

class MainActivity : FlutterActivity() {

    private val CHANNEL = "de.kneke.common/quote"
    private val STREAM = "de.kneke.common/quotestream"

    //private val viewModel: QuoteViewModel = injectClient.quoteViewModel()

    private var quoteEventSink: EventChannel.EventSink? = null

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine);

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            when (call.method) {
                "getQuoteViewModel" -> {
                    Log.e("METHOD CHANNEL", "call getQuoteViewModel")
                    val freshData = call.arguments as Boolean
                    Log.e("METHOD CHANNEL", "fresh data $freshData")
                    //viewModel.get(freshData)
                    sendNewQuote()
                }
                else -> result.notImplemented()
            }
        }

        EventChannel(flutterEngine.getDartExecutor(), STREAM).setStreamHandler(
                object : EventChannel.StreamHandler {

                    override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
                        quoteEventSink = events
                        /*
                        viewModel.quoteModel.watch {
                            Log.e("METHOD CHANNEL", it!!.toJsonString())
                            events?.success(it!!.toJsonString())
                        }
                         */
                    }

                    override fun onCancel(arguments: Any?) {
                        quoteEventSink = null
                    }
                }
        )
    }

    private fun sendNewQuote() {
        val randomNum = Random.nextInt(0, 1000)
        quoteEventSink?.success("""{"loading": false, "quote": {"id": 1, "quote": "Hello this is test message: $randomNum", "author": "Chris", "permalink": ""}, "error": "No ERROR"}""")
    }

}
