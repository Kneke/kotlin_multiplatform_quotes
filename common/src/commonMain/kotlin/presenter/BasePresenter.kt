package presenter

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BasePresenter(private val baseView: BaseView, private val mainDispatcher: CoroutineContext): CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = mainDispatcher + job + exceptionHandler

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        baseView.showError(throwable)
    }

    open fun onDestroy() {
        job.cancel()
    }

}