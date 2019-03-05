import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLDivElement
import data.Quote
import quote.QuoteContract
import kotlin.browser.document

class QuotePage: QuoteContract.QuoteView {

    private val quotePresenter = injectQuotePresenter(this)

    private val quoteText = document.getElementById("quoteText") as HTMLDivElement
    private val authorText = document.getElementById("authorText") as HTMLDivElement
    private val button = document.getElementById("button") as HTMLButtonElement

    init {
        button.addEventListener("click", {
            loadQuote()
        })
    }

    fun loadQuote() {
        quotePresenter.showQuote(true)
    }

    override fun setQuote(quote: Quote) {
        quoteText.innerHTML = quote.quote
        authorText.innerHTML = quote.author
    }

    override fun showError(error: Throwable) {
        println(error.message)
    }

    override fun showLoadingSpinner(visibility: Boolean) {
        println("Set loading spinner to: $visibility")
    }

    /*
    fun start() {
        val depList = listOf(
            "kotlin-test.js",
            "kotlin.js",
            "kotlinx-atomicfu.js",
            "kotlinx-coroutines-core.js",
            "kotlinx-coroutines-io.js",
            "kotlinx-io.js",
            "kotlinx-serialization-runtime-js.js",
            "ktor-client-core.js",
            "ktor-client-js.js",
            "ktor-client-json.js",
            "ktor-client-logging.js",
            "ktor-http.js",
            "ktor-utils.js",
            "sqldelight-runtime.js",
            "common.js")
        depList.forEach {
            val script = document.createElement("script") as HTMLScriptElement
            script.type = "text/javascript"
            script.src = "js/lib/$it"
            document.appendChild(script)
        }
        val script = document.createElement("script") as HTMLScriptElement
        script.type = "text/javascript"
        script.src = "js/app.js"
        document.appendChild(script)

        val test = document.createElement("div") as HTMLDivElement
        test.innerHTML = "TEST"
        document.appendChild(test)
    }
    */

}