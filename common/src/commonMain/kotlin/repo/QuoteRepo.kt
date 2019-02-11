package repo

import api.QuoteApi
import data.Quote

interface QuoteRepo {
    suspend fun getRandomQuote(): Quote
}

class QuoteRepoImpl(private val api: QuoteApi): QuoteRepo {

     override suspend fun getRandomQuote(): Quote {
         return api.load()
    }
}

