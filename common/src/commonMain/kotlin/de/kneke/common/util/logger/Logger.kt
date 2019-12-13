package de.kneke.common.util.logger

var loggerName = "LOGGER"
var loggingActive: Boolean = false

fun setupLogger(showLogs: Boolean = loggingActive, loggerIdentifier: String = loggerName) {
    loggingActive = showLogs
    loggerName = loggerIdentifier
}

fun log(message: String, tag: String = loggerName) {
    if (loggingActive) println("$tag: $message")
}

fun log(throwable: Throwable, message: String = "", tag: String = loggerName) {
    if (loggingActive) println("$tag: $message \n$tag: $throwable")
}