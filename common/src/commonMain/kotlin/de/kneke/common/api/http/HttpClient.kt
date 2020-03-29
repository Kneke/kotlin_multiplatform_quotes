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
import io.ktor.client.request.post
import io.ktor.client.request.url
import kotlinx.coroutines.isActive

class HttpClient {

    var ktorClient = initClient()

    fun initClient(): HttpClient {
        return HttpClient {
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

    suspend inline fun <reified T> get(url: String): T {
        if (!ktorClient.isActive) ktorClient = initClient()
        return ktorClient.get { url(url) }
    }

    suspend inline fun <reified T> post(url: String, payload: Any): T {
        if (!ktorClient.isActive) ktorClient = initClient()
        return ktorClient.post {
            url(url)
            body = payload
        }
    }

    fun close() = ktorClient.close()
}

