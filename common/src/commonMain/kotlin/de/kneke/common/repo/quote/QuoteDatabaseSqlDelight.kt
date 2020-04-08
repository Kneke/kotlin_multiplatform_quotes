package de.kneke.common.repo.quote

import de.kneke.common.data.quote.Quote
import de.kneke.common.db.QuoteDbRecord
import de.kneke.common.db.QuoteQueries
import de.kneke.common.repo.cache.DatabaseCache

class QuoteDatabaseSqlDelight(private val quoteQueries: QuoteQueries) : DatabaseCache<Quote>() {

    override fun saveInDb(data: Quote) {
        quoteQueries.insert(data.id.toLong(), data.quote, data.author, data.permalink)
    }

    override fun saveAllInDb(list: List<Quote>) {
        list.forEach { save(it) }
    }

    override fun loadFromDb(filter: ((List<Quote>) -> Quote?)?): Quote? {
        return if (filter != null) filter(quoteQueries.selectAll().executeAsList().map { it.cast() })
        else quoteQueries.selectRandom().executeAsOne().cast()

    }

    override fun loadAllFromDb(filter: ((List<Quote>) -> List<Quote>?)?): List<Quote>? {
        return if (filter != null) filter(quoteQueries.selectAll().executeAsList().map { it.cast() })
        else quoteQueries.selectAll().executeAsList().map { it.cast() }
    }

    override fun sizeInDb(): Int {
        return quoteQueries.selectAll().executeAsList().size;
    }

    override fun clearDb() {
        quoteQueries.deleteAll()
    }

    private fun QuoteDbRecord.cast(): Quote {
        return Quote(
            checkNotNull(this.id.toInt()),
            checkNotNull(this.quote),
            checkNotNull(this.author),
            this.permalink
        )
    }
}