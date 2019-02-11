package di

import io.ktor.client.HttpClient
import network.DefaultClient
import network.api.QuoteApi
import presenter.QuoteContract
import presenter.QuotePresenterImpl
import repo.QuoteRepo
import repo.QuoteRepoImpl

val defaultClient: HttpClient by lazy {
    DefaultClient.defaultClient
}

val quoteApi: QuoteApi by lazy {
    QuoteApi()
}

val quoteRepo: QuoteRepo by lazy {
    QuoteRepoImpl()
}

private var quotePresenter: QuoteContract.QuotePresenter? = null
fun quotePresenter(quoteView: QuoteContract.QuoteView): QuoteContract.QuotePresenter {
    if (quotePresenter == null) quotePresenter = QuotePresenterImpl(quoteView)
    return quotePresenter!!
}



/*
fun inject(): MainComponent = MainModule()

interface MainComponent {

    val defaultClient: HttpClient

    val quoteApi: QuoteApi

    val repo: QuoteRepo

    fun quotePresenter(quoteView: QuoteContract.QuoteView): QuoteContract.QuotePresenter
}

class MainModule : MainComponent {

    override val defaultClient = DefaultClient().defaultClient

    override val quoteApi = QuoteApi()

    override val repo = QuoteRepoImpl()

    var quotePresenter: QuoteContract.QuotePresenter? = null

    override fun quotePresenter(quoteView: QuoteContract.QuoteView): QuoteContract.QuotePresenter {
        if (quotePresenter == null) quotePresenter = QuotePresenterImpl(quoteView)
        return quotePresenter!!
    }
}
*/

/*
object CommonModule {

    fun quoteApi() = object: DependencyProvider<api.QuoteApi>() {
        override fun new() = QuoteApi()
    }

    fun repo() = object: DependencyProvider<repo.QuoteRepo>() {
        override fun new() = QuoteRepoImpl()
    }

    fun quotePresenter(view: QuoteContract.QuoteView) = object: DependencyProvider<QuoteContract.QuotePresenter>() {
        override fun new() = QuotePresenterImpl(view)
    }

}

abstract class DependencyProvider<T> {
    private var instance: T? = null

    abstract fun new(): T
    fun newLazy(): Lazy<T> = lazy { new() }
    fun single(): T {
        instance = if (instance != null) instance else new()
        return instance!!
    }
}
        */