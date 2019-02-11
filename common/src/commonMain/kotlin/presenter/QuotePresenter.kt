package presenter

import data.Quote
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import repo.QuoteRepo
import util.dispatcher.backgroundDispatcher
import kotlin.coroutines.CoroutineContext

interface QuoteContract {
    interface QuoteView: BaseView {
        fun setQuote(quote: Quote)
    }
    interface QuotePresenter {
        fun showQuote()
    }
}

class QuotePresenterImpl(
    private val quoteView: QuoteContract.QuoteView, private val repo: QuoteRepo,
    dispatcherMain: CoroutineContext): QuoteContract.QuotePresenter, BasePresenter(quoteView, dispatcherMain) {

    override fun showQuote() {
        launch(coroutineContext) {
            val quote = withContext(backgroundDispatcher) {
                repo.getRandomQuote()
            }
            quoteView.setQuote(quote)
        }
    }
}
