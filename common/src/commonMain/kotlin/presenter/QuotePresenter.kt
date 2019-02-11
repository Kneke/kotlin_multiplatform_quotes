package presenter

import data.Quote
import di.quoteRepo

interface QuoteContract {
    interface QuoteView {
        fun setQuote(quote: Quote)
    }
    interface QuotePresenter {
        fun showQuote()
    }
}

class QuotePresenterImpl(val quoteView: QuoteContract.QuoteView): QuoteContract.QuotePresenter {

    var repo = quoteRepo

    override fun showQuote() {
        repo.getRandomQuote{
            quoteView.setQuote(it)
        }
    }
}
