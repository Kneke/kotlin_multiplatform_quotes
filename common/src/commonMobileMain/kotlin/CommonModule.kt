import de.kneke.common.api.MyHttpClient
import de.kneke.common.db.DatabaseHelper
import de.kneke.common.db.QuoteQueries
import de.kneke.common.quote.*
import io.ktor.client.HttpClient
import de.kneke.common.viewpresenter.BaseCoroutinePresenter
import org.kodein.di.Kodein
import org.kodein.di.erased.*


// TODO Make it more kolin style like
private var quotePresenter: BaseCoroutinePresenter<QuoteContract.QuoteView>? = null

fun injectQuotePresenter(quoteView: QuoteContract.QuoteView): QuoteContract.QuotePresenter {
    val repo: QuoteRepo by kodein.instance()
    if (quotePresenter == null) quotePresenter = QuotePresenterImpl(repo)
    return quotePresenter!!.apply { currentView = quoteView } as QuoteContract.QuotePresenter
}


val kodein = Kodein {
    bind<HttpClient>() with singleton { MyHttpClient.httpClient }
    bind<QuoteApi>() with singleton { QuoteApi(instance()) }
    //bind<MyDatabase>() with singleton { DatabaseHelper("test.db").database }
    bind<QuoteQueries>() with singleton { DatabaseHelper("test.db").database.quoteQueries }
    bind<QuoteRepo>() with singleton { QuoteRepoImpl(instance(), instance()) }
    bind<QuoteContract.QuotePresenter>() with factory { quoteView: QuoteContract.QuoteView ->
        QuotePresenterImpl(instance()).apply {
            currentView = quoteView
        }
    }
}

fun getPresenter(quoteView: QuoteContract.QuoteView): QuoteContract.QuotePresenter {
    val test: QuoteContract.QuotePresenter by kodein.instance(arg = quoteView)
    return test
}

