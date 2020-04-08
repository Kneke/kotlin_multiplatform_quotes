package de.kneke.common

import de.kneke.common.api.Api
import de.kneke.common.api.http.HttpClient
import de.kneke.common.api.quote.QuoteApi
import de.kneke.common.data.quote.Quote
import de.kneke.common.legal.LicenceProvider
import de.kneke.common.repo.quote.QuoteRepo
import de.kneke.common.repo.ResourceRepo
import de.kneke.common.repo.cache.Cache
import de.kneke.common.repo.cache.InMemoryCache
import de.kneke.common.repo.quote.QuoteDatabaseSqlDelight
import de.kneke.common.setting.ApplicationSettings
import de.kneke.common.setting.Settings
import de.kneke.common.util.db.DatabaseHelper
import de.kneke.common.viewmodel.ViewModel
import de.kneke.common.viewmodel.quote.QuoteModel
import de.kneke.common.viewmodel.quote.QuoteViewModel
import org.kodein.di.Kodein
import org.kodein.di.erased.*

open class ClientModule {

    open val kodeinDI = Kodein {
        constant("server_url") with "http://quotes.stormconsultancy.co.uk/random.json"

        bind<HttpClient>() with provider { HttpClient() }
        bind<Settings>() with singleton { ApplicationSettings() }
        bind<LicenceProvider>() with singleton { LicenceProvider() }
        /* QUOTE */
        bind<Cache<Quote>>("MEMORY") with singleton { InMemoryCache<Quote>() }
        bind<Cache<Quote>>("DB") with singleton {
            QuoteDatabaseSqlDelight(DatabaseHelper("test.db").database.quoteQueries)
        }
        bind<Api<Quote>>() with provider { QuoteApi(instance(), instance("server_url")) }
        bind<ResourceRepo<Quote>>() with singleton {
            QuoteRepo(instance("MEMORY"), instance("DB"), instance())
        }
        bind<ViewModel<QuoteModel>>() with singleton { QuoteViewModel(instance()) }
    }

    open fun quoteViewModel(): QuoteViewModel {
        val viewModel: ViewModel<QuoteModel> by kodeinDI.instance()
        return viewModel as QuoteViewModel
    }

    open fun appSettings(): Settings {
        val appSettings: Settings by kodeinDI.instance()
        return appSettings
    }

    open fun licenceProvider(): LicenceProvider {
        val licenceProvider: LicenceProvider by kodeinDI.instance()
        return licenceProvider
    }
}

val injectClient = ClientModule()
