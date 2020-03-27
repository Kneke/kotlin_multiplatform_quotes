package de.kneke.common.repo.quote

import de.kneke.common.api.Api
import de.kneke.common.data.quote.Quote
import de.kneke.common.repo.ResourceRepo
import de.kneke.common.repo.cache.Cache

class QuoteRepo(
    memoryCache: Cache<Quote>?,
    database: Cache<Quote>?,
    api: Api<Quote>?
) : ResourceRepo<Quote>(memoryCache, database, api)


