package de.kneke.common.quote

import de.kneke.common.data.Quote
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import de.kneke.common.data.Resource
import de.kneke.common.util.dispatcher.Dispatcher
import de.kneke.common.viewpresenter.BaseCoroutinePresenter
import de.kneke.common.viewpresenter.BasePresenter
import de.kneke.common.viewpresenter.BaseView

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
