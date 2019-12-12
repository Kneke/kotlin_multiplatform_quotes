import de.kneke.common.api.MyHttpClient
import de.kneke.common.db.DatabaseHelper
import de.kneke.common.db.MyDatabase
import de.kneke.common.quote.*
import io.ktor.client.HttpClient
import de.kneke.common.viewpresenter.BaseCoroutinePresenter

val httpClient: HttpClient by lazy {
    MyHttpClient.httpClient
}

val quoteApi: QuoteApi by lazy {
    QuoteApi(httpClient)
}

val myDatabase: MyDatabase by lazy {
    DatabaseHelper("test.db").database
}

val quoteRepo: QuoteRepo by lazy {
    QuoteRepoImpl(quoteApi, myDatabase.quoteQueries)
}

// TODO Make it more kolin style like
private var quotePresenter: BaseCoroutinePresenter<QuoteContract.QuoteView>? = null

fun injectQuotePresenter(quoteView: QuoteContract.QuoteView): QuoteContract.QuotePresenter {
    if (quotePresenter == null) quotePresenter = QuotePresenterImpl(quoteRepo)
    return quotePresenter!!.apply { currentView = quoteView } as QuoteContract.QuotePresenter
}

