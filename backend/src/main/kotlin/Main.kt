import data.Quote
import io.ktor.application.*
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.html.respondHtml
import io.ktor.http.content.resource
import io.ktor.http.content.static
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.serialization
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.html.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.list
import java.lang.Exception
import kotlin.random.Random

lateinit var quoteList: List<Quote>

fun main(args: Array<String>) {
    quoteList = getQuotesFromResources()
    embeddedServer(Netty, 8080, watchPaths = listOf("MainKt"), module = Application::module).start()
}

fun Application.module() {
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        serialization()
    }
    install(Routing) {
        static("static") {
            resource("quotes.json")
        }
        get("/") {
            val randomQuote = getRandomQuote()
            call.respondHtml {
                head {
                    title(content = "Ktor Website for Quotes")
                }
                body {
                    div {
                        p {
                            + randomQuote.quote
                        }
                        p {
                            + randomQuote.author
                        }
                        a {

                        }
                    }
                    div {
                        form(action = "/", method = FormMethod.get) {
                            input(InputType.submit) { value = "next" }
                        }
                    }
                }
            }
        }
        get("/quote") {
            call.respond(getRandomQuote())
        }
    }
}

fun getRandomQuote(): Quote {
    return quoteList[Random.nextInt(0, quoteList.lastIndex)]
}


fun getQuotesFromResources(): List<Quote> {
    return try {
        val jsonResponses = object {}.javaClass.getResource("quotes.json").readText(Charsets.UTF_8)
        Json.parse(Quote.serializer().list, jsonResponses)
    } catch (e: Exception) {
        println(e)
        listOf(Quote(1337, "Guter Sourcecode macht mich geil.", "Christoph Knetschke", ""))
    }
}
