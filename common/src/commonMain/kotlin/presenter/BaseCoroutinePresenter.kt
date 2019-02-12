package presenter

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import util.dispatcher.mainDispatcher

interface BasePresenter {
    fun cancelJobs()
}

abstract class BaseCoroutinePresenter<T: BaseView> : BasePresenter, CoroutineScope {

    abstract var currentView: T

    private var uiDispatcher = mainDispatcher
    private var job = Job()
    private var coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable -> currentView.showError(throwable) }
    override var coroutineContext = uiDispatcher + job + coroutineExceptionHandler

    override fun cancelJobs() {
        job.cancel()
        job = Job()
        coroutineContext = uiDispatcher + job + coroutineExceptionHandler
    }
}
