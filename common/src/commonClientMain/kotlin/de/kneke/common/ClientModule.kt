package de.kneke.common

import de.kneke.common.api.Api
import de.kneke.common.api.http.KtorHttpClient
import de.kneke.common.api.quote.QuoteApi
import de.kneke.common.data.quote.Quote
import de.kneke.common.data.Resource
import de.kneke.common.repo.quote.QuoteRepo
import de.kneke.common.repo.Repo
import de.kneke.common.repo.cache.Cache
import de.kneke.common.repo.cache.InMemoryCache
import de.kneke.common.repo.cache.QuoteSqlDelightDB
import de.kneke.common.util.db.DatabaseHelper
import de.kneke.common.viewmodel.ViewModel
import de.kneke.common.viewmodel.quote.QuoteViewModel
import org.kodein.di.Kodein
import org.kodein.di.erased.*

open class ClientModule {

    open val kodeinDI = Kodein {
        constant("server_url") with "http://quotes.stormconsultancy.co.uk/random.json"

        bind<KtorHttpClient>() with provider { KtorHttpClient() }
        bind<Api<Quote>>() with provider { QuoteApi(instance(), instance("server_url")) }
        bind<Cache<Quote>>("MEMORY") with singleton { InMemoryCache<Quote>() }
        bind<Cache<Quote>>("DB") with singleton {
            QuoteSqlDelightDB(DatabaseHelper("test.db").database.quoteQueries)
        }
        bind<Repo<Resource<Quote>>>() with singleton {
            QuoteRepo(instance("MEMORY"), instance("DB"), instance())
        }
        bind<ViewModel<Resource<Quote>>>() with singleton {
            QuoteViewModel(instance())
        }
    }

    open fun quoteViewModel(): QuoteViewModel {
        val viewModel: ViewModel<Resource<Quote>> by kodeinDI.instance()
        return viewModel as QuoteViewModel
    }
}


val injectClient = ClientModule()
