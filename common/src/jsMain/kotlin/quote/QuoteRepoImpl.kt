package quote

import util.Resource

class QuoteRepoImpl(private val quoteApi: QuoteApi): QuoteRepo {

    var quoteCache: Quote? = null

    override suspend fun getRandomQuote(freshData: Boolean): Resource<Quote> {

        if (!freshData && quoteCache != null) return Resource.Success(quoteCache!!)

        val quote = quoteApi.load()

        if (quote is Resource.Success) {
            quoteCache = quote.data
        }

        return quote
    }
}