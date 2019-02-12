import data.Quote
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import api.QuoteApi
import io.ktor.client.features.logging.*
import api.intercept.ResponseInterceptor
import presenter.BaseCoroutinePresenter
import presenter.QuoteContract
import presenter.QuotePresenterImpl
import repo.QuoteRepo
import repo.QuoteRepoImpl
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal // FIXME In nativ ktor throws exception without this
val httpClient: HttpClient by lazy {
    HttpClient {
        install(ResponseInterceptor)
        install(JsonFeature) {
            serializer = KotlinxSerializer().apply {
                // FIXME Required for native this can not be serialized without it
                setMapper(Quote::class, Quote.serializer())
            }
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
    }
}

val quoteApi: QuoteApi by lazy {
    QuoteApi(httpClient)
}

val quoteRepo: QuoteRepo by lazy {
    QuoteRepoImpl(quoteApi)
}

// TODO Make it more kolin style like
private var quotePresenter: BaseCoroutinePresenter<QuoteContract.QuoteView>? = null
fun quotePresenter(quoteView: QuoteContract.QuoteView): QuoteContract.QuotePresenter {
    if (quotePresenter == null) quotePresenter = QuotePresenterImpl(quoteRepo)
    return quotePresenter?.apply {
        currentView = quoteView
    } as QuoteContract.QuotePresenter
}
