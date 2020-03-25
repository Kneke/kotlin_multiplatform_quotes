package route.quote

import de.kneke.common.data.Resource
import handler.quote.QuoteHandler
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route

fun Route.quote(quoteHandler: QuoteHandler) {
    route("quote") {
        get("random") {
            when (val quoteResource = quoteHandler.getRandomQuote()) {
                is Resource.Failure -> call.respond("Error")
                is Resource.Success -> call.respond(quoteResource.data)
            }
        }
    }
}