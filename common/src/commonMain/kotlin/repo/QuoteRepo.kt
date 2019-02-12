package repo

import api.QuoteApi
import data.Quote
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

interface QuoteRepo {
    suspend fun getRandomQuote(freshData: Boolean): Quote
}

class QuoteRepoImpl(private val api: QuoteApi) : QuoteRepo {

    private var latestQuoteCache: Quote? = null

    override suspend fun getRandomQuote(freshData: Boolean): Quote {
        // Directly request new data from server
        if (freshData) return loadFromApi()

        // Get data from cache
        latestQuoteCache?.let { return latestQuoteCache as Quote }

        // TODO implement DB

        return loadFromApi()
    }

    private suspend fun loadFromApi(): Quote {
        val quote = api.load()
        saveReceivedDataInDB(quote)
        return quote
    }

    private fun saveReceivedDataInCache(quote: Quote) {
        GlobalScope.async {// TODO Remove GlobalScope and add some new scope that can be canceled
            latestQuoteCache = quote
        }
    }


    private suspend fun saveReceivedDataInDB(quote: Quote) {
        GlobalScope.async {// TODO Remove GlobalScope and add some new scope that can be canceled
            // TODO implement DB
            saveReceivedDataInCache(quote)
        }
    }

}

