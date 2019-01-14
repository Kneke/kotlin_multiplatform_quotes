package presenter

import data.Quote
import repo.QuoteRepoImpl

interface QuoteContract {
    interface QuoteView {
        fun setQuote(quote: Quote)
    }
    interface QuotePresenter {
        fun showQuote()
    }
}

class QuotePresenterImpl(val quoteView: QuoteContract.QuoteView): QuoteContract.QuotePresenter {

    var quoteRepo = QuoteRepoImpl()

    override fun showQuote() {
        quoteRepo.getRandomQuote{
            quoteView.setQuote(it)
        }
    }
}
