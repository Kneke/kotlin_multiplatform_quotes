package httpclient

import data.Quote
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.Url
import kotlinx.serialization.json.JSON

class QuoteClient {

    private val client = HttpClient()

    var address = Url("http://quotes.stormconsultancy.co.uk/random.json")

    suspend fun load(): Quote {
        val result: String = client.get {
            url(this@QuoteClient.address.toString())
        }
        return JSON.parse(Quote.serializer(), result)
    }
}