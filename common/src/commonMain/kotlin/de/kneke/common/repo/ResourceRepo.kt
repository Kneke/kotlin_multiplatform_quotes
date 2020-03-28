package de.kneke.common.repo

import de.kneke.common.api.Api
import de.kneke.common.data.Resource
import de.kneke.common.repo.cache.Cache
import de.kneke.common.util.dispatcher.Dispatcher
import de.kneke.common.util.logger.log
import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

abstract class ResourceRepo<T>(
    private val memoryCache: Cache<T>?,
    private val database: Cache<T>?,
    private val api: Api<T>?
) : Repo<Resource<T>> {

    val mutex = Mutex()

    override suspend fun get(fromNetwork: Boolean, index: Int): Resource<T> {
        // Directly request new data from server
        if (fromNetwork) return loadFromApi()

        // Get data from cache
        memoryCache?.load(index)?.let {
            log("Get Data from Cache")
            return Resource.Success(it)
        }

        // Get data from DB
        database?.load(index)?.let {
            log("Get Data from DB")
            saveInMemoryCache(it)
            return Resource.Success(it)
        }

        // Get data from Api
        return loadFromApi()
    }

    open suspend fun loadFromApi(): Resource<T> {
        api?.load()?.let {
            log("Get Data from API")
            saveReceivedDataInDB(it)
            return Resource.Success(it)
        }
        return Resource.Failure()
    }

    open fun saveInMemoryCache(data: T) {
        memoryCache?.save(data)
    }

    open fun saveReceivedDataInDB(data: T) {
        CoroutineScope(Dispatcher.io).launch {
            mutex.withLock {
                database?.save(data)
                saveInMemoryCache(data)
            }
        }
    }
}