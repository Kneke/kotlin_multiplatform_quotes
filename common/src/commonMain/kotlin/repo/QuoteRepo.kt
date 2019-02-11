package repo

import data.Quote
import di.quoteApi
import util.dispatcher.backgroundDispatcher
import kotlinx.coroutines.*

interface QuoteRepo {
    fun getRandomQuote(callback: (Quote) -> Unit)
}

class QuoteRepoImpl: QuoteRepo {

    private val api = quoteApi

     override fun getRandomQuote(callback: (Quote) -> Unit) {
         GlobalScope.launch(backgroundDispatcher) {
             val result = api.load()
             callback(result)
         }
    }
}

