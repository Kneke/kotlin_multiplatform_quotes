package de.kneke.common.api.quote

import de.kneke.common.api.Api
import de.kneke.common.api.http.KtorHttpClient
import de.kneke.common.data.quote.Quote
import de.kneke.common.util.logger.log
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class QuoteApi(private val client: KtorHttpClient, private val serverUrl: String) : Api<Quote> {

    override suspend fun load(): Quote? {
        return try {
            val quoteResponse: QuoteResponse = client.get(serverUrl)
            quoteResponse.transform()
        } catch (e: Exception) {
            log(e,"Can not load Data from API")
            return null
        }
    }

    @Serializable
    data class QuoteResponse(
        @SerialName("id") val id: Int?,
        @SerialName("quote") val quote: String?,
        @SerialName("author") val author: String?,
        @SerialName("permalink") val permalink: String?
    ) {
        fun transform(): Quote {
            return Quote(
                checkNotNull(this.id),
                checkNotNull(this.quote),
                checkNotNull(this.author),
                this.permalink
            )
        }
    }
}