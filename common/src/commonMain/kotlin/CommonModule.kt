import api.MyHttpClient
import quote.QuoteApi
import util.database.DatabaseProvider
import de.cknetsc.multiapp.MyDatabase
import io.ktor.client.HttpClient
import viewpresenter.BaseCoroutinePresenter
import quote.QuoteContract
import quote.QuotePresenterImpl
import quote.QuoteRepo
import quote.QuoteRepoImpl

val httpClient: HttpClient by lazy {
    MyHttpClient.httpClient
}

val quoteApi: QuoteApi by lazy {
    QuoteApi(httpClient)
}

val myDatabase: MyDatabase by lazy {
    util.database.DatabaseProvider.dbInstance
}

val quoteRepo: QuoteRepo by lazy {
    QuoteRepoImpl(quoteApi, myDatabase.quoteDbQueries)
}

// TODO Make it more kolin style like
private var quotePresenter: BaseCoroutinePresenter<QuoteContract.QuoteView>? = null
fun injectQuotePresenter(quoteView: QuoteContract.QuoteView): QuoteContract.QuotePresenter {
    if (quotePresenter == null) quotePresenter = QuotePresenterImpl(quoteRepo)
    return quotePresenter?.apply {
        currentView = quoteView
    } as QuoteContract.QuotePresenter
}

