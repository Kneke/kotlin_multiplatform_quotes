package de.kneke.common.repo

interface Repo<I, T> {

    suspend fun get(parameters: I): T

}