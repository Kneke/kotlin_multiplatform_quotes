package de.kneke.common.api

import de.kneke.common.api.intercept.ResponseInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal // FIXME In nativ ktor throws exception without this
object MyHttpClient {

    var httpClient = HttpClient {
        install(ResponseInterceptor)
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

}