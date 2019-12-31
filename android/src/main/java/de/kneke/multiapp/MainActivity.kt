package de.kneke.multiapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import de.kneke.common.viewmodel.quote.QuoteViewModel
import de.kneke.common.viewmodel.quote.QuoteModel
import kotlinx.android.synthetic.main.activity_main.*
import de.kneke.common.injectClient

class MainActivity : AppCompatActivity() {

    private val viewModel: QuoteViewModel = injectClient.quoteViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.quoteModel.watch { updateView(it!!) }
        nextButton.setOnClickListener { loadQuote(true) }
        loadQuote()
    }

    override fun onPause() {
        super.onPause()
        viewModel.clear()
    }

    private fun loadQuote(loadNewQuote: Boolean = false) {
        viewModel.get(loadNewQuote)
    }

    private fun updateView(quoteModel: QuoteModel) {
        showLoadingSpinner(quoteModel.loading)

        quoteModel.quote?.let {
            quoteText.text = it.quote
            authorText.text = it.author
        }

        quoteModel.error?.let {
            Log.e("MainActivity", it.message)
            Toast.makeText(this, "Network Error happens", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showLoadingSpinner(visibility: Boolean) {
        loadingAnimation.visibility = if (visibility) View.VISIBLE else View.GONE
    }
}
