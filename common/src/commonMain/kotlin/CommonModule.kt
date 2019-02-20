import api.MyHttpClient
import api.QuoteApi
import data.db.DatabaseDriver
import de.cknetsc.multiapp.MyDatabase
import io.ktor.client.HttpClient
import presenter.BaseCoroutinePresenter
import presenter.QuoteContract
import presenter.QuotePresenterImpl
import repo.QuoteRepo
import repo.QuoteRepoImpl


val httpClient: HttpClient by lazy {
    MyHttpClient.httpClient
}

val quoteApi: QuoteApi by lazy {
    QuoteApi(httpClient)
}

val myDatabase: MyDatabase by lazy {
    DatabaseDriver.setupDB()
    DatabaseDriver.dbInstance
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

