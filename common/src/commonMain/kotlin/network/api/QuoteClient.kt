package network.api

import data.Quote
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.*
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.Url
import network.intercept.QuoteApiInterceptor

var address = Url("http://quotes.stormconsultancy.co.uk/random.json")

class QuoteClient {

    private val client = HttpClient {
        install(QuoteApiInterceptor)
        install(JsonFeature) {
            serializer = KotlinxSerializer().apply {
                // Mappers are required because in Nativ this can not be parsed by it own
                setMapper(Quote::class, Quote.serializer())
            }
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }

    suspend fun load(): Quote {
        val result = client.get<Quote> {
            url(address.toString())
        }
        return result
    }
}