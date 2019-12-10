import api.MyHttpClient
import io.ktor.client.HttpClient
import quote.*
import viewpresenter.BaseCoroutinePresenter

val httpClient: HttpClient by lazy {
    MyHttpClient.httpClient
}

val quoteApi: QuoteApi by lazy {
    QuoteApi(httpClient)
}

/*
val myDatabase: MyDatabase by lazy {
    DatabaseProvider.dbInstance
}
 */

val quoteRepo: QuoteRepo by lazy {
    QuoteRepoImpl(quoteApi)
}

// TODO Make it more kolin style like
private var quotePresenter: BaseCoroutinePresenter<QuoteContract.QuoteView>? = null

fun injectQuotePresenter(quoteView: QuoteContract.QuoteView): QuoteContract.QuotePresenter {
    if (quotePresenter == null) quotePresenter = QuotePresenterImpl(quoteRepo)
    return quotePresenter!!.apply { currentView = quoteView } as QuoteContract.QuotePresenter
}

