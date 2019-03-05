package quote

import data.Quote
import data.Resource

interface QuoteRepo {
    suspend fun getRandomQuote(freshData: Boolean): Resource<Quote>
}