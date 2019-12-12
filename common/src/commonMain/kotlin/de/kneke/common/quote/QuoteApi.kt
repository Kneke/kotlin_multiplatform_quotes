package de.kneke.common.quote

import de.kneke.common.data.Quote
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.Url
import de.kneke.common.data.Resource

class QuoteApi(private val client: HttpClient) {

    private val address = Url("http://quotes.stormconsultancy.co.uk/random.json")

    suspend fun load(): Resource<Quote> {
        return try {
            Resource.Success(client.get { url(address.toString()) })
        } catch (e: Exception) {
            Resource.NetworkError(e)
        }
    }
}