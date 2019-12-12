package de.kneke.common.util.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

expect object Dispatcher {

    internal val main: CoroutineDispatcher

    internal val io: CoroutineDispatcher

    internal val worker: CoroutineDispatcher
}

