package network.intercept

import io.ktor.client.HttpClient
import io.ktor.client.features.HttpClientFeature
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.HttpResponsePipeline
import io.ktor.http.isSuccess
import io.ktor.util.AttributeKey

object QuoteApiInterceptor : HttpClientFeature<Unit, QuoteApiInterceptor> {

    override val key: AttributeKey<QuoteApiInterceptor> = AttributeKey("QuoteApiInterceptor")

    override fun prepare(block: Unit.() -> Unit): QuoteApiInterceptor = this

    override fun install(feature: QuoteApiInterceptor, scope: HttpClient) {
        scope.responsePipeline.intercept(HttpResponsePipeline.Receive) {
            val response = subject.response as HttpResponse

            if (!response.status.isSuccess()) throw ApiException(response)

            proceedWith(subject)
        }
    }
}

class ApiException(val response: HttpResponse) : Throwable()
