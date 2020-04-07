package de.kneke.common.repo.cache

class InMemoryCache<T>: Cache<T> {

    private var cacheList = mutableListOf<T>()

    override fun save(data: T) {
        cacheList.add(data)
    }

    override fun saveAll(list: List<T>) {
        cacheList = list.toMutableList()
    }

    override fun load(filter: ((List<T>) -> T?)?): T? {
        if (cacheList.isEmpty()) return null
        if (filter != null) return filter(cacheList)
        return cacheList[cacheList.lastIndex]
    }

    override fun loadAll(filter: ((List<T>) -> List<T>?)?): List<T>? {
        if (filter != null) return filter(cacheList)
        return cacheList
    }

    override fun size(): Int {
        return cacheList.size
    }

    override fun clear() {
        cacheList.clear()
    }
}