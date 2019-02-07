package repo

import data.Quote
import network.api.QuoteClient
import dispatcher.backgroundDispatcher
import kotlinx.coroutines.*

interface QuoteRepo {

    fun getRandomQuote(callback: (Quote) -> Unit)
}

class QuoteRepoImpl: QuoteRepo {

    private val api = QuoteClient()

     override fun getRandomQuote(callback: (Quote) -> Unit) {
         GlobalScope.launch(backgroundDispatcher) {
             val result = api.load()
             callback(result)
         }
    }

}

