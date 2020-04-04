package de.kneke.flutterapp.channel.viewmodel

import de.kneke.flutterapp.channel.ChannelConfig
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.EventChannel

abstract class BaseViewModelChannel(channelConfig: ChannelConfig, flutterEngine: FlutterEngine) {

    private var eventSink: EventChannel.EventSink? = null

    init {
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, channelConfig.getMethodChannelName())
            .setMethodCallHandler { call, result ->
                when (call.method) {
                    channelConfig.getChannelMethods()[0] -> update(call.arguments)
                    else -> result.notImplemented()
                }
            }

        EventChannel(flutterEngine.getDartExecutor(), channelConfig.getEventChannelName()).setStreamHandler(
            object : EventChannel.StreamHandler {
                override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
                    eventSink = events
                    watch(eventSink)
                }

                override fun onCancel(arguments: Any?) {
                    eventSink = null
                }
            }
        )
    }


    abstract fun update(arguments: Any?)

    abstract fun watch(quoteEventSink: EventChannel.EventSink?)
}