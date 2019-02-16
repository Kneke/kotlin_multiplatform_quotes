package util.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal actual val mainDispatcher: CoroutineDispatcher = Dispatchers.Main

internal actual val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO