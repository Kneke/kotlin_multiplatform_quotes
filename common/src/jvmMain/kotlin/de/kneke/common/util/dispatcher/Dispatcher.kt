package de.kneke.common.util.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual object Dispatcher {

    internal actual val main: CoroutineDispatcher = Dispatchers.Unconfined

    internal actual val io: CoroutineDispatcher = Dispatchers.IO

    internal actual val worker: CoroutineDispatcher = Dispatchers.Default

}