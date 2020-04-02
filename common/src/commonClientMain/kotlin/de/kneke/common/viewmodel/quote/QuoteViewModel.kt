package de.kneke.common.viewmodel.quote

import de.kneke.common.data.quote.Quote
import de.kneke.common.data.Resource
import de.kneke.common.repo.Repo
import de.kneke.common.util.dispatcher.Dispatcher
import de.kneke.common.viewmodel.ViewModel
import de.kneke.common.viewmodel.ViewModelObservable
import kotlinx.coroutines.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

@Serializable
data class QuoteModel(val loading: Boolean, val quote: Quote? = null, val error: String? = null) {
    fun toJsonString(): String {
        val json = Json(JsonConfiguration.Stable)
        return json.stringify(serializer(), this)
    }
}

class QuoteViewModel(private val quoteRepo: Repo<Resource<Quote>>) : ViewModel<Resource<Quote>> {

    private var quoteJob: Job? = null

    val quoteModel = ViewModelObservable<QuoteModel>()

    fun get(freshData: Boolean) {
        quoteJob = CoroutineScope(Dispatcher.main).launch {

            quoteModel.value = QuoteModel(true, Quote(0, "", "", ""))

            val quoteResource = withContext(Dispatcher.io) {
                quoteRepo.get(freshData)
            }

            when (quoteResource) {
                is Resource.Loading -> quoteModel.value = QuoteModel(
                    true,
                    Quote(0, "", "", "")
                )
                is Resource.Success -> quoteModel.value = QuoteModel(false, quoteResource.data)
                is Resource.NetworkError -> quoteModel.value = QuoteModel(false, null, quoteResource.throwable.message)
            }
        }
    }

    override fun clear() {
        quoteModel.unwatch()
        quoteJob?.cancel()
    }

}