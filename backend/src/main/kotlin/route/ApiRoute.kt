package route

import injector
import org.kodein.di.generic.instance
import handler.quote.QuoteHandler
import io.ktor.http.content.resource
import io.ktor.http.content.static
import io.ktor.routing.Route
import io.ktor.routing.route
import route.quote.quote

fun Route.api() {
    val quoteHandler: QuoteHandler by injector.instance()
    route("api/v1/") {
        quote(quoteHandler)
    }
    static {
        resource("quotes","quotes.json")
    }
}