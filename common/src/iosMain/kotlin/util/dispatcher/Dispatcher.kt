package util.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import platform.darwin.dispatch_queue_t
import kotlin.coroutines.CoroutineContext

actual object Dispatcher {

    internal actual val main: CoroutineDispatcher = NsQueueDispatcher(dispatch_get_main_queue())

    internal actual val io: CoroutineDispatcher = NsQueueDispatcher(dispatch_get_main_queue())

    internal actual val worker: CoroutineDispatcher = NsQueueDispatcher(dispatch_get_main_queue())

}

internal class NsQueueDispatcher(private val dispatchQueue: dispatch_queue_t) : CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatchQueue) {
            block.run()
        }
    }
}


