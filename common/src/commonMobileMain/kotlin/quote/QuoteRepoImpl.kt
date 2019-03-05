package quote

import data.Quote
import de.cknetsc.multiapp.data.QuoteDbQueries
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import sqldelight.de.cknetsc.multiapp.data.cast
import data.Resource
import util.dispatcher.Dispatcher

class QuoteRepoImpl(private val api: QuoteApi, private val quoteDao: QuoteDbQueries) : QuoteRepo {

    private var latestQuoteCache: Quote? = null

    override suspend fun getRandomQuote(freshData: Boolean): Resource<Quote> {
        // Directly request new data from server
        if (freshData) return loadFromApi()

        // Get data from cache
        latestQuoteCache?.let {
            return Resource.Success(latestQuoteCache as Quote)
        }

        // Get data from DB
        val quote = loadFromDb()
        if (quote is Resource.Success) {
            saveReceivedDataInCache(quote.data)
            return quote
        }

        // Get data from Api
        return loadFromApi()
    }

    private fun loadFromDb(): Resource<Quote> {
        return try { Resource.Success(quoteDao.selectRandom().executeAsOne().cast()) }
        catch (e: Exception) { Resource.DatabaseError() }
    }

    private suspend fun loadFromApi(): Resource<Quote> {
        val quote = api.load()
        if (quote is Resource.Success) saveReceivedDataInDB(quote.data)
        return quote
    }

    private fun saveReceivedDataInDB(quote: Quote) {
        CoroutineScope(Dispatcher.io).launch {
            try { quoteDao.insert(quote.id.toLong(), quote.quote, quote.author, quote.permalink) }
            finally { saveReceivedDataInCache(quote) }
        }
    }

    private fun saveReceivedDataInCache(quote: Quote) {
        latestQuoteCache = quote
    }
}

