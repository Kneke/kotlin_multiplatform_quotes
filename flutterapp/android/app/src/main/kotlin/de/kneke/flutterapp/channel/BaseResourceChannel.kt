package de.kneke.flutterapp.channel

import de.kneke.flutterapp.channel.ChannelConfig
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

abstract class BaseResourceChannel(channelConfig: ChannelConfig, flutterEngine: FlutterEngine) {

    init {
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, channelConfig.getMethodChannelName())
            .setMethodCallHandler { call, result ->
                when (call.method) {
                    channelConfig.getChannelMethods()[0] -> get(call.arguments, result)
                    else -> result.notImplemented()
                }
            }
    }

    abstract fun get(arguments: Any?, result: MethodChannel.Result)
}