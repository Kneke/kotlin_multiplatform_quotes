package util.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import platform.darwin.dispatch_main
import platform.darwin.dispatch_queue_t
import kotlin.coroutines.CoroutineContext

internal actual val mainDispatcher: CoroutineDispatcher = NsQueueDispatcher(dispatch_get_main_queue())

internal actual val backgroundDispatcher: CoroutineDispatcher = NsQueueDispatcher(dispatch_get_main_queue())


internal class NsQueueDispatcher(private val dispatchQueue: dispatch_queue_t): CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatchQueue) {
            block.run()
        }
    }
}


