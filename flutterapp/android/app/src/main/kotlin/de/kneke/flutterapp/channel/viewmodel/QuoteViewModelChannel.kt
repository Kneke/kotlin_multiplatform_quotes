package de.kneke.flutterapp.channel.viewmodel

import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.EventChannel
import de.kneke.flutterapp.channel.ChannelConfig
import de.kneke.flutterapp.channel.viewmodel.BaseViewModelChannel
import de.kneke.common.viewmodel.quote.QuoteViewModel
import de.kneke.common.viewmodel.quote.QuoteModel
import de.kneke.common.injectClient

class QuoteViewModelChannelConfig: ChannelConfig {
    override fun getMethodChannelName(): String = "de.kneke.common/quote"

    override fun getEventChannelName(): String = "de.kneke.common/quotestream"

    override fun getChannelMethods(): List<String> = listOf("getQuoteViewModel")
}

class QuoteViewModelChannel(flutterEngine: FlutterEngine) : BaseViewModelChannel(QuoteViewModelChannelConfig(), flutterEngine) {

    private val viewModel: QuoteViewModel = injectClient.quoteViewModel()

    override fun update(arguments: Any?) {
        val freshData = arguments as Boolean
        viewModel.get(freshData)
    }

    override fun watch(eventSink: EventChannel.EventSink?) {
        viewModel.quoteModel.watch {
            eventSink?.success(it!!.toJsonString())
        }
    }

}