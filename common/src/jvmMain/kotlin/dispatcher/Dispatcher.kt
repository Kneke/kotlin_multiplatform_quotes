package dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal actual val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO