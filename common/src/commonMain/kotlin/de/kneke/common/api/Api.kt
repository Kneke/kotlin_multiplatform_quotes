package de.kneke.common.api

interface Api<T> {

    suspend fun load(): T?
}