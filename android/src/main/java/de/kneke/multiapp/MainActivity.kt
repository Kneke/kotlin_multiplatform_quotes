package de.kneke.multiapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import de.kneke.common.data.Quote
import de.kneke.common.quote.QuoteContract
import getPresenter

class MainActivity : AppCompatActivity(), QuoteContract.QuoteView {
    private val presenter = getPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nextButton.setOnClickListener { loadQuoteInUI(true) }
    }

    override fun onResume() {
        super.onResume()
        loadQuoteInUI()
    }

    override fun onPause() {
        super.onPause()
        presenter.cleanUp()
    }

    fun loadQuoteInUI(loadNewQuote: Boolean = false) {
        quoteText.text = ""
        authorText.text = ""
        presenter.showQuote(loadNewQuote)
    }

    override fun setQuote(quote: Quote) {
        quoteText.text = quote.quote
        authorText.text = quote.author
    }

    override fun showError(error: Throwable) {
        Log.e("MainActivity", error.message)
        Toast.makeText(this, "Network Error happens", Toast.LENGTH_SHORT ).show()
    }

    override fun showLoadingSpinner(visibility: Boolean) {
        loadingSpinner.visibility = if (visibility) View.VISIBLE else View.GONE
    }
}
