package de.kneke.common.api.http.intercept

import io.ktor.client.HttpClient
import io.ktor.client.features.HttpClientFeature
import io.ktor.client.statement.HttpResponsePipeline
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import io.ktor.util.AttributeKey

object ResponseInterceptor : HttpClientFeature<Unit, ResponseInterceptor> {

    override val key: AttributeKey<ResponseInterceptor> = AttributeKey("ResponseInterceptor")

    override fun prepare(block: Unit.() -> Unit): ResponseInterceptor = this

    override fun install(feature: ResponseInterceptor, scope: HttpClient) {
        scope.responsePipeline.intercept(HttpResponsePipeline.Receive) {
            val response = context.response
            if (!response.status.isSuccess()) throw ApiException(response.status)
            proceedWith(subject)
        }
    }
}

class ApiException(val response: HttpStatusCode) : Throwable()
