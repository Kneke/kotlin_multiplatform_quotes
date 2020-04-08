package handler.quote

import de.kneke.common.data.Resource
import de.kneke.common.data.quote.Quote
import de.kneke.common.repo.ResourceRepo
import handler.Handler
import kotlin.random.Random

class QuoteHandler(private val quoteRepo: ResourceRepo<Quote>, private val numberOfQuotes: Int) :
    Handler<Resource<Quote>> {

    suspend fun getRandomQuote(): Resource<Quote> {
        return quoteRepo.get(Pair(false, { quoteList -> quoteList[Random.nextInt(0, (numberOfQuotes - 1))] }))
    }
}