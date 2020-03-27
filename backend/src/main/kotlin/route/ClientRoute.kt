package route

import io.ktor.http.content.*
import io.ktor.routing.Route

fun Route.client() {
    static {
        resource("/", "client/index.html")
        resources("client")
        static("static") {
            resources("client/static")
        }
    }
}