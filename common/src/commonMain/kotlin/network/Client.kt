package network

import data.Quote
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.*
import network.intercept.ResponseInterceptor
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal // FIXME Currently necessary to get thread save ktor clients in iOS
object DefaultClient {

    val defaultClient: HttpClient = HttpClient {
        install(ResponseInterceptor)
        install(JsonFeature) {
            serializer = KotlinxSerializer().apply {
                // FIXME Mappers are required because in ios this can not be parsed by it own
                setMapper(Quote::class, Quote.serializer())
            }
        }
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
    }
}

