package de.kneke.multiapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import de.kneke.common.viewmodel.quote.QuoteViewModel
import kotlinx.android.synthetic.main.activity_main.*
import de.kneke.common.data.Quote
import de.kneke.common.data.Resource
import quoteViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: QuoteViewModel = quoteViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.quoteResource.watch {
            when(it) {
                is Resource.Loading -> showLoadingSpinner(true)
                is Resource.Success -> setQuote(it.data)
                is Resource.NetworkError -> showError(it.throwable)
            }
        }

        nextButton.setOnClickListener { loadQuoteInUI(true) }

        loadQuoteInUI()
    }

    override fun onPause() {
        super.onPause()
        viewModel.clear()
    }

    private fun loadQuoteInUI(loadNewQuote: Boolean = false) {
        quoteText.text = ""
        authorText.text = ""
        viewModel.get(loadNewQuote)
    }

    private fun setQuote(quote: Quote) {
        quoteText.text = quote.quote
        authorText.text = quote.author
        showLoadingSpinner(false)
    }

    private fun showError(error: Throwable) {
        showLoadingSpinner(false)
        Log.e("MainActivity", error.message)
        Toast.makeText(this, "Network Error happens", Toast.LENGTH_SHORT ).show()
    }

    fun showLoadingSpinner(visibility: Boolean) {
        loadingSpinner.visibility = if (visibility) View.VISIBLE else View.GONE
    }
}
