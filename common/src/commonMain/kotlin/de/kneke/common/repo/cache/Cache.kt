package de.kneke.common.repo.cache

interface Cache<T> {
    fun save(data: T)
    fun saveAll(list: List<T>)
    fun load(filter: ((List<T>) -> T?)?): T?
    fun loadAll(filter: ((List<T>) -> List<T>?)?): List<T>?
    fun size(): Int
    fun clear()
}