package de.kneke.common.repo.cache

import de.kneke.common.util.logger.log

abstract class DatabaseCache<T> : Cache<T> {

    override fun save(data: T) {
        try {
            saveInDb(data)
        } catch (e: Exception) {
            log(e, "Record can not be saved in DB")
        }
    }

    override fun saveAll(list: List<T>) {
        try {
            saveAllInDb(list)
        } catch (e: Exception) {
            log(e, "Record can not be saved in DB")
        }
    }

    override fun load(filter: ((List<T>) -> T?)?): T? {
        return try {
            loadFromDb(filter)
        } catch (e: Exception) {
            log(e, "Can not load record from DB")
            null
        }
    }

    override fun loadAll(filter: ((List<T>) -> List<T>?)?): List<T>? {
        return try {
            loadAllFromDb(filter)
        } catch (e: Exception) {
            log(e, "Can not load records from DB")
            null
        }
    }

    override fun size(): Int {
        return try {
            sizeInDb()
        } catch (e: Exception) {
            log(e, "Can not size of records in DB")
            0
        }
    }

    override fun clear() {
        try {
            clearDb()
        } catch (e: Exception) {
            log(e, "Can not clear all records in DB")
        }
    }

    abstract fun saveInDb(data: T)

    abstract fun saveAllInDb(list: List<T>)

    abstract fun loadFromDb(filter: ((List<T>) -> T?)?): T?

    abstract fun loadAllFromDb(filter: ((List<T>) -> List<T>?)?): List<T>?

    abstract fun sizeInDb(): Int

    abstract fun clearDb()
}