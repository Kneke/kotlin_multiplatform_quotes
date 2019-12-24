package de.kneke.common.repo

import de.kneke.common.api.Api
import de.kneke.common.data.Quote
import de.kneke.common.repo.cache.Cache

class QuoteRepo(
    api: Api<Quote>,
    database: Cache<Quote>?,
    memoryCache: Cache<Quote>?
) : ResourceRepo<Quote>(api, database, memoryCache)


