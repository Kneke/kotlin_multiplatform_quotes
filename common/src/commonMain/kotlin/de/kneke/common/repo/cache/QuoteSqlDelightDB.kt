package de.kneke.common.repo.cache

import de.kneke.common.data.Quote
import de.kneke.common.db.QuoteDbRecord
import de.kneke.common.db.QuoteQueries
import de.kneke.common.util.logger.log

class QuoteSqlDelightDB(private val quoteQueries: QuoteQueries) :
    Cache<Quote> {

    override fun save(data: Quote) {
        try {
            quoteQueries.insert(data.id.toLong(), data.quote, data.author, data.permalink)
        } catch (e: Exception) {
            log(e, "Record can not be saved in DB")
        }
    }

    override fun saveAll(list: List<Quote>) {
       list.forEach { save(it) }
    }

    override fun load(index: Int): Quote? {
        return try {
            quoteQueries.selectRandom().executeAsOne().cast()
        } catch (e: Exception) {
            log(e,"Can not load Record from DB")
            return null
        }
    }

    override fun loadAll(): List<Quote>? {
        return try {
            quoteQueries.selectAll().executeAsList().map { it.cast() }
        } catch (e: Exception) {
            log(e,"Can not load Record from DB")
            return null
        }
    }

    override fun clear() {
        return try {
            quoteQueries.deleteAll()
        } catch (e: Exception) {
            log(e,"Can not delete data in DB")
        }
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