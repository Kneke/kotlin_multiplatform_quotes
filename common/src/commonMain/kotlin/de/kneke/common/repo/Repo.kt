package de.kneke.common.repo

interface Repo<T> {

    suspend fun get(fromNetwork: Boolean = false, index: Int = -1): T

}