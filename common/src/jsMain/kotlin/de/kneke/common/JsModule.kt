package de.kneke.common

import de.kneke.common.api.Api
import de.kneke.common.api.http.KtorHttpClient
import de.kneke.common.api.quote.QuoteApi
import de.kneke.common.data.Quote
import de.kneke.common.data.Resource
import de.kneke.common.repo.QuoteRepo
import de.kneke.common.repo.Repo
import de.kneke.common.repo.cache.Cache
import de.kneke.common.repo.cache.InMemoryCache
import de.kneke.common.viewmodel.quote.QuoteViewModel
import org.kodein.di.Kodein
import org.kodein.di.erased.*

class JsModule: ClientModule() {

    override val kodeinDI = Kodein {
        constant("server_url") with "http://quotes.stormconsultancy.co.uk/random.json"

        bind<KtorHttpClient>() with provider { KtorHttpClient() }
        bind<Api<Quote>>() with provider { QuoteApi(instance(), instance("server_url")) }
        bind<Cache<Quote>>("MEMORY") with singleton { InMemoryCache<Quote>() }
        bind<Repo<Resource<Quote>>>() with singleton {
            QuoteRepo(instance(), null, instance("MEMORY"))
        }
        bind<QuoteViewModel>() with singleton { QuoteViewModel(instance()) }
    }
}

val injectJs = JsModule()
