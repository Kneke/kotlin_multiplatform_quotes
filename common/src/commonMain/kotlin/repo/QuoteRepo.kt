package repo

import api.QuoteApi
import data.Quote
import de.cknetsc.multiapp.data.QuoteDbQueries
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.Logger
import util.logger.e

interface QuoteRepo {
    suspend fun getRandomQuote(freshData: Boolean): Quote
}

class QuoteRepoImpl(private val api: QuoteApi, private val quoteDao: QuoteDbQueries) : QuoteRepo {

    private var latestQuoteCache: Quote? = null

    override suspend fun getRandomQuote(freshData: Boolean): Quote {
        // Directly request new data from server
        if (freshData) return loadFromApi()

        // Get data from cache
        latestQuoteCache?.let {
            return latestQuoteCache as Quote
        }

        loadFromDb()?.let {
            saveReceivedDataInCache(it)
            return it
        }

        return loadFromApi()
    }

    private fun loadFromDb(): Quote? {
        try {
            val quote = quoteDao.selectRandom().executeAsOne()
            return Quote(quote.id.toInt(), quote.quote, quote.author, quote.permalink)
        } catch (e: Exception) {
            Logger.DEFAULT.e(e, "Db Error")
        }
        return null
    }

    private suspend fun loadFromApi(): Quote {
        val quote = api.load()
        saveReceivedDataInDB(quote)
        return quote
    }

    private fun saveReceivedDataInCache(quote: Quote) {
        // TODO Add some async task here without globalscope
        latestQuoteCache = quote
    }

    private fun saveReceivedDataInDB(quote: Quote) {
        // TODO Add some async task here without globalscope
        try {
            quoteDao.insert(quote.id.toLong(), quote.quote, quote.author, quote.permalink)
        } catch (e: Exception) {
            Logger.DEFAULT.e(e, "Db Error")
        }
        saveReceivedDataInCache(quote)
    }
}

