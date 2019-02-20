package quote

import io.ktor.client.HttpClient
import io.ktor.client.features.logging.*
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.Url
import util.logger.d

class QuoteApi(private val client: HttpClient) {

    private val address = Url("http://quotes.stormconsultancy.co.uk/random.json")

    suspend fun load(): Quote {
        Logger.DEFAULT.d("Call endpoint -> $address", this::class.simpleName.toString())
        return client.get { url(address.toString()) }
    }
}