package route.health

import io.ktor.application.call
import io.ktor.html.respondHtml
import io.ktor.routing.Route
import io.ktor.routing.get
import kotlinx.html.*

fun Route.health() {
    get("health") {
        call.respondHtml {
            head {
                title(content = "Ktor service health check")
            }
            body {
                div {
                    h1 {
                        + "Health Check"
                    }
                    p {
                        + "Ok"
                    }
                }
            }
        }
    }
}