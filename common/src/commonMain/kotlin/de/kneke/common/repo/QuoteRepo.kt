package de.kneke.common.repo

import de.kneke.common.api.Api
import de.kneke.common.data.Quote
import de.kneke.common.data.Resource
import de.kneke.common.repo.cache.Cache
import de.kneke.common.util.dispatcher.Dispatcher
import de.kneke.common.util.logger.log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class QuoteRepo(
    private val api: Api<Quote>,
    private val database: Cache<Quote>?,
    private val memoryCache: Cache<Quote>?
) : Repo<Resource<Quote>> {

    override suspend fun get(fromNetwork: Boolean): Resource<Quote> {
        // Directly request new data from server
        if (fromNetwork) return loadFromApi()

        // Get data from cache
        memoryCache?.load()?.let {
            log("Get Data from Cache")
            return Resource.Success(it)
        }

        // Get data from DB
        database?.load()?.let {
            log("Get Data from DB")
            memoryCache?.save(it)
            return Resource.Success(it)
        }

        // Get data from Api
        return loadFromApi()
    }

    private suspend fun loadFromApi(): Resource<Quote> {
        api.load()?.let {
            log("Get Data from API")
            saveReceivedDataInDB(it)
            return Resource.Success(it)
        }
        return Resource.Failure()
    }

    private fun saveReceivedDataInDB(quote: Quote) {
        CoroutineScope(Dispatcher.io).launch {
            database?.save(quote)
            memoryCache?.save(quote)
        }
    }
}


