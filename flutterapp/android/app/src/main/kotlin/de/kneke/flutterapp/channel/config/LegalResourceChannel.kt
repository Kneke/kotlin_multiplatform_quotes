package de.kneke.flutterapp.channel.config

import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import de.kneke.flutterapp.channel.ChannelConfig
import de.kneke.flutterapp.channel.BaseResourceChannel
import de.kneke.common.injectClient

class LegalResourceChannelConfig : ChannelConfig {
    override fun getMethodChannelName(): String = "de.kneke.common/legal"
    override fun getEventChannelName(): String = ""
    override fun getChannelMethods(): List<String> = listOf("getLegalText")
}

class LegalResourceChannel(flutterEngine: FlutterEngine) :
    BaseResourceChannel(LegalResourceChannelConfig(), flutterEngine) {

    private val licenceProvider = injectClient.licenceProvider()

    override fun get(arguments: Any?, result: MethodChannel.Result) {
        val licenceHTML = licenceProvider.getPrivacyPolicyHTMLText() + "\n" +
                licenceProvider.getAssetLicenceHTMLText() + "\n" +
                licenceProvider.getFossHTMLText()
        result.success(licenceHTML)
    }
}