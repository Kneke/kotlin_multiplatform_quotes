import io.ktor.application.*
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.html.respondHtml
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.html.*
import quote.Quote

fun main(args: Array<String>) {
    embeddedServer(Netty, 8080, watchPaths = listOf("MainKt"), module = Application::module).start()
}

fun Application.module() {
    install(DefaultHeaders)
    install(CallLogging)
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }
    install(Routing) {
        get("/") {
            call.respondHtml {
                head {
                    title(content = "Ktor Website for Quotes")
                }
                body {
                    h1 {
                        + "Welcome to my Ktor Webservice"
                    }
                    h3 {
                        + "We serve Quotes as json from the mutiplatform lib. Just set path to /quote"
                    }
                }
            }
        }
        get("/quote") {
            call.respond(Quote(1337, "Guter Sourcecode macht mich geil.", "Christoph Knetschke", ""))
        }
    }

}