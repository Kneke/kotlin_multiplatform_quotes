package de.kneke.common.repo.cache

class InMemoryCache<T>: Cache<T> {

    private var cacheList = mutableListOf<T>()

    override fun save(data: T) {
        cacheList.add(data)
    }

    override fun saveAll(list: List<T>) {
        cacheList = list.toMutableList()
    }

    override fun load(index: Int): T? {
        if (cacheList.isEmpty()) return null
        if (index >= 0) return cacheList[index]
        return cacheList[cacheList.lastIndex]
    }

    override fun loadAll(): List<T>? {
        return cacheList
    }

    override fun clear() {
        cacheList.clear()
    }

}