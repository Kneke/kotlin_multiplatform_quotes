package presenter

import data.Quote
import repo.QuoteRepo

interface QuoteContract {
    interface QuoteView {
        fun setQuote(quote: Quote)
    }
    interface QuotePresenter {
        fun showQuote()
    }
}

class QuotePresenterImpl(
    private val quoteView: QuoteContract.QuoteView,
    private val repo: QuoteRepo): QuoteContract.QuotePresenter {

    override fun showQuote() {
        repo.getRandomQuote{
            quoteView.setQuote(it)
        }
    }
}
