package repo

import api.QuoteApi
import data.Quote
import util.dispatcher.backgroundDispatcher
import kotlinx.coroutines.*

interface QuoteRepo {
    fun getRandomQuote(callback: (Quote) -> Unit)
}

class QuoteRepoImpl(private val api: QuoteApi): QuoteRepo {

     override fun getRandomQuote(callback: (Quote) -> Unit) {
         GlobalScope.launch(backgroundDispatcher) {
             val result = api.load()
             callback(result)
         }
    }
}

