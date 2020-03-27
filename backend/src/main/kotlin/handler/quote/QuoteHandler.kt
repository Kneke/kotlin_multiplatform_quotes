package handler.quote

import de.kneke.common.data.Resource
import de.kneke.common.data.quote.Quote
import de.kneke.common.repo.Repo
import handler.Handler
import kotlin.random.Random

class QuoteHandler(private val quoteRepo: Repo<Resource<Quote>>, private val numberOfQuotes: Int) :
    Handler<Resource<Quote>> {

    suspend fun getRandomQuote(): Resource<Quote> {
        return quoteRepo.get(false, index = Random.nextInt(0, (numberOfQuotes - 1)))
    }
}