package de.kneke.common.viewmodel.quote

import de.kneke.common.data.Quote

data class QuoteModel(val loading: Boolean, val quote: Quote? = null, val error: Throwable? = null)