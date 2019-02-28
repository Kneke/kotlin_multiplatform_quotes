package quote

import util.Resource

interface QuoteRepo {
    suspend fun getRandomQuote(freshData: Boolean): Resource<Quote>
}