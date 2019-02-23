package quote

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import util.Resource
import util.dispatcher.Dispatcher
import viewpresenter.BaseCoroutinePresenter
import viewpresenter.BasePresenter
import viewpresenter.BaseView

interface QuoteContract {
    interface QuoteView : BaseView {
        fun setQuote(quote: Quote)
    }

    interface QuotePresenter : BasePresenter {
        fun showQuote(getFreshQuote: Boolean = false)
    }
}

class QuotePresenterImpl(private val repo: QuoteRepo) : BaseCoroutinePresenter<QuoteContract.QuoteView>(),
    QuoteContract.QuotePresenter {

    override lateinit var currentView: QuoteContract.QuoteView

    override fun showQuote(getFreshQuote: Boolean) {
        launch {
            currentView.showLoadingSpinner(true)

            val quote = withContext(Dispatcher.io) {
                repo.getRandomQuote(getFreshQuote)
            }

            when (quote) {
                is Resource.Success -> currentView.setQuote(quote.data)
                is Resource.NetworkError -> currentView.showError(quote.throwable)
            }
            
            currentView.showLoadingSpinner(false)
        }
    }

}
