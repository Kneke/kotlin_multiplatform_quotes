package de.kneke.common.quote

import de.kneke.common.data.Quote
import de.kneke.common.data.Resource

interface QuoteRepo {
    suspend fun getRandomQuote(freshData: Boolean): Resource<Quote>
}