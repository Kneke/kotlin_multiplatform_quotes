package de.kneke.common.viewmodel.quote

import de.kneke.common.data.Quote
import de.kneke.common.data.Resource
import de.kneke.common.repo.Repo
import de.kneke.common.util.dispatcher.Dispatcher
import de.kneke.common.viewmodel.ViewModel
import de.kneke.common.viewmodel.ViewModelObservable
import kotlinx.coroutines.*

class QuoteViewModel(private val quoteRepo: Repo<Resource<Quote>>):
    ViewModel<Resource<Quote>> {

    private var quoteJob: Job? = null

    val quoteResource = ViewModelObservable<Resource<Quote>>()

    fun get(freshData: Boolean) {
        quoteJob = CoroutineScope(Dispatcher.main).launch {

            quoteResource.value = Resource.Loading()

            val quote = withContext(Dispatcher.io) {
                quoteRepo.get(freshData)
            }

            quoteResource.value = quote
        }
    }

    override fun clear() {
        quoteResource.unwatch()
        quoteJob?.cancel()
    }
}