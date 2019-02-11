package util.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

internal expect val mainDispatcher: CoroutineDispatcher

internal expect val backgroundDispatcher: CoroutineDispatcher

