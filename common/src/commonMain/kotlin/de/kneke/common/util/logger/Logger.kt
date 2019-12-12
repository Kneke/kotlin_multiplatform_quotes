package de.kneke.common.util.logger

import io.ktor.client.features.logging.Logger
import kotlin.native.concurrent.SharedImmutable

@SharedImmutable
var loggingActive: Boolean = false

fun Logger.d(message: String, tag: String = "Logger") {
    if (loggingActive) println("$tag: $message")
}

fun Logger.e(message: String, tag: String = "Logger") {
    if (loggingActive) println("$tag: $message")
}

fun Logger.e(throwable: Throwable, message: String = "", tag: String = "Logger") {
    if (loggingActive) {
        println("$tag: $message")
        println(throwable)
    }
}