package de.kneke.flutterapp.channel

interface ChannelConfig {
    fun getMethodChannelName(): String

    fun getEventChannelName(): String

    fun getChannelMethods(): List<String>
}