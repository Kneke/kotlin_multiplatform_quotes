import de.kneke.common.data.quote.Quote
import de.kneke.common.repo.ResourceRepo
import de.kneke.common.repo.cache.Cache
import de.kneke.common.repo.cache.InMemoryCache
import de.kneke.common.repo.quote.QuoteRepo
import de.kneke.common.repo.quote.QuoteDatabaseSqlDelight
import de.kneke.common.setting.ApplicationSettings
import de.kneke.common.setting.Settings
import de.kneke.common.util.db.DatabaseHelper
import handler.quote.QuoteHandler
import loader.QuoteLoader
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import org.kodein.di.generic.with

val injector = Kodein {
    bind<Settings>() with singleton { ApplicationSettings() }
    /* QUOTE */
    val localQuoteList = QuoteLoader().getQuotesFromResources()
    constant("NUMBER_OF_QUOTES") with localQuoteList.size
    bind<Cache<Quote>>("IN_MEMORY") with singleton { InMemoryCache<Quote>() }
    bind<Cache<Quote>>("DB") with singleton {
        val quoteDB = QuoteDatabaseSqlDelight(DatabaseHelper("test.db").database.quoteQueries)
        quoteDB.saveAll(localQuoteList)
        return@singleton quoteDB
    }
    bind<ResourceRepo<Quote>>() with singleton { QuoteRepo(instance("IN_MEMORY"), instance("DB"), null) }
    bind<QuoteHandler>() with singleton { QuoteHandler(instance(), instance("NUMBER_OF_QUOTES")) }
}