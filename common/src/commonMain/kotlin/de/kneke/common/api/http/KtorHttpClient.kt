package de.kneke.common.api.http

import de.kneke.common.api.http.intercept.ResponseInterceptor
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.url

class KtorHttpClient {

    val client by lazy {
        HttpClient {
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

    suspend inline fun <reified T> get(url: String): T = client.get { url(url) }

    fun close() = client.close()
}

