package network.api

import data.Quote
import di.defaultClient
import io.ktor.client.features.logging.*
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.Url
import util.logger.d

class QuoteApi {

    private val address = Url("http://quotes.stormconsultancy.co.uk/random.json")

    private val client = defaultClient

    suspend fun load(): Quote {
        Logger.DEFAULT.d("Call endpoint -> $address", this::class.simpleName.toString())
        val result = client.get<Quote> {
            url(address.toString())
        }
        return result
    }
}