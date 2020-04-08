package de.kneke.common.repo.cache

import de.kneke.common.util.logger.log

class InMemoryCache<T>: Cache<T> {

    private var cacheList = mutableListOf<T>()

    override fun save(data: T) {
        try {
            cacheList.add(data)
        } catch (e: Exception) {
            log(e, "Record can not be saved in memory cache")
        }
    }

    override fun saveAll(list: List<T>) {
        try {
            cacheList = list.toMutableList()
        } catch (e: Exception) {
            log(e, "Records can not be saved in memory cache")
        }
    }

    override fun load(filter: ((List<T>) -> T?)?): T? {
        return try {
            if (cacheList.isEmpty()) return null
            if (filter != null) return filter(cacheList)
            cacheList[cacheList.lastIndex]
        } catch (e: Exception) {
            log(e,"Can not load Record from memory cache")
            null
        }
    }

    override fun loadAll(filter: ((List<T>) -> List<T>?)?): List<T>? {
        return try {
            if (filter != null) return filter(cacheList)
            cacheList
        } catch (e: Exception) {
            log(e,"Can not load Records from memory cache")
            null
        }
    }

    override fun size(): Int {
        return try {
            cacheList.size
        } catch (e: Exception) {
            log(e,"Can not load size from memory cache")
            0
        }
    }

    override fun clear() {
        cacheList = mutableListOf()
    }
}