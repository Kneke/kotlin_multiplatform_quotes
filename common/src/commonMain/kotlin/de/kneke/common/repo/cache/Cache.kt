package de.kneke.common.repo.cache

interface Cache<T> {
    fun save(data: T)
    fun saveAll(list: List<T>)
    fun load(index: Int = -1): T?
    fun loadAll(): List<T>?
    fun size(): Int
    fun clear()
}