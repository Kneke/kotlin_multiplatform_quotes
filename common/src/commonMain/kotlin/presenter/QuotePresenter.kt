package presenter

import data.Quote
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.Logger
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import repo.QuoteRepo
import util.dispatcher.backgroundDispatcher
import util.logger.d

interface QuoteContract {
    interface QuoteView: BaseView {
        fun setQuote(quote: Quote)
    }
    interface QuotePresenter: BasePresenter {
        fun showQuote(getFreshQuote: Boolean = false)
    }
}

class QuotePresenterImpl(private val repo: QuoteRepo): BaseCoroutinePresenter<QuoteContract.QuoteView>(), QuoteContract.QuotePresenter {

    override lateinit var currentView: QuoteContract.QuoteView


    override fun showQuote(getFreshQuote: Boolean) {
        launch {
            val quote = withContext(backgroundDispatcher) {
                repo.getRandomQuote(getFreshQuote)
            }
            currentView.setQuote(quote)
        }
    }

}
