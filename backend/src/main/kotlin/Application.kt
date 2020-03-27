import de.kneke.common.util.logger.setupLogger
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.routing.Routing
import io.ktor.serialization.serialization
import route.api
import route.client
import route.health.health

fun Application.mainModule() {
    setupLogger(true, "BACKEND-LOGGER")
    install(DefaultHeaders)
    install(CORS) {
        host("localhost:3000")
    }
    install(CallLogging)
    install(ContentNegotiation) {
        serialization()
    }
    install(Routing) {
        health()
        client()
        api()
    }
}