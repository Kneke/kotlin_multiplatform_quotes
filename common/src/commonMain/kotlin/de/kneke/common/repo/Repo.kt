package de.kneke.common.repo

interface Repo<T> {

    suspend fun get(fromNetwork: Boolean): T

}