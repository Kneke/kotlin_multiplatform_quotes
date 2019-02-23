package viewpresenter

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import util.dispatcher.Dispatcher

interface BasePresenter {

    fun cleanUp()
}

abstract class BaseCoroutinePresenter<T : BaseView> : BasePresenter, CoroutineScope {

    abstract var currentView: T

    private var uiDispatcher = Dispatcher.main
    private var job = Job()
    private var coroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable -> currentView.showError(throwable) }
    override var coroutineContext = uiDispatcher + job + coroutineExceptionHandler

    override fun cleanUp() {
        job.cancel()
        job = Job()
        coroutineContext = uiDispatcher + job + coroutineExceptionHandler
    }
}
