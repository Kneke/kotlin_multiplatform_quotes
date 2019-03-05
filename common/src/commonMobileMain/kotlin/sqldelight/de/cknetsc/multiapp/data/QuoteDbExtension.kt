package sqldelight.de.cknetsc.multiapp.data

import de.cknetsc.multiapp.data.QuoteDb
import data.Quote

fun QuoteDb.cast(): Quote {
    return Quote(this.id.toInt(), this.quote, this.author, this.permalink)
}