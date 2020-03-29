package de.kneke.common

import de.kneke.common.api.Api
import de.kneke.common.api.http.HttpClient
import de.kneke.common.api.quote.QuoteApi
import de.kneke.common.data.quote.Quote
import de.kneke.common.data.Resource
import de.kneke.common.repo.quote.QuoteRepo
import de.kneke.common.repo.Repo
import de.kneke.common.repo.cache.Cache
import de.kneke.common.repo.cache.InMemoryCache
import de.kneke.common.setting.ApplicationSettings
import de.kneke.common.setting.Settings
import de.kneke.common.viewmodel.ViewModel
import de.kneke.common.viewmodel.quote.QuoteViewModel
import org.kodein.di.Kodein
import org.kodein.di.erased.*

class JsModule : ClientModule() {

    override val kodeinDI = Kodein {
        constant("server_url") with "http://localhost:3001/api/v1/quote/random"

        bind<HttpClient>() with provider { HttpClient() }
        bind<Settings>() with singleton { ApplicationSettings() }
        /* QUOTE */
        bind<Cache<Quote>>("MEMORY") with singleton { InMemoryCache<Quote>() }
        bind<Api<Quote>>() with provider { QuoteApi(instance(), instance("server_url")) }
        bind<Repo<Resource<Quote>>>() with singleton {
            QuoteRepo(instance("MEMORY"), null, instance())
        }
        bind<ViewModel<Resource<Quote>>>() with singleton { QuoteViewModel(instance()) }
    }
}

val injectJs = JsModule()
