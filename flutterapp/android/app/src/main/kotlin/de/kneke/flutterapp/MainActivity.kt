package de.kneke.flutterapp

import android.util.Log
import androidx.annotation.NonNull
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant
import kotlin.random.Random
import de.kneke.common.viewmodel.quote.QuoteViewModel
import de.kneke.common.viewmodel.quote.QuoteModel
import de.kneke.common.injectClient

class MainActivity : FlutterActivity() {

    private val CHANNEL = "de.kneke.common/quote"
    private val STREAM = "de.kneke.common/quotestream"

    private val viewModel: QuoteViewModel = injectClient.quoteViewModel()

    private var quoteEventSink: EventChannel.EventSink? = null

    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine);

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            when (call.method) {
                "getQuoteViewModel" -> {
                    val freshData = call.arguments as Boolean
                    viewModel.get(freshData)
                }
                else -> result.notImplemented()
            }
        }

        EventChannel(flutterEngine.getDartExecutor(), STREAM).setStreamHandler(
                object : EventChannel.StreamHandler {

                    override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
                        quoteEventSink = events
                        viewModel.quoteModel.watch {
                            events?.success(it!!.toJsonString())
                        }
                    }

                    override fun onCancel(arguments: Any?) {
                        quoteEventSink = null
                    }
                }
        )
    }
}
