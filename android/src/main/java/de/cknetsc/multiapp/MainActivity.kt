package de.cknetsc.multiapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import injectQuotePresenter
import kotlinx.android.synthetic.main.activity_main.*
import quote.Quote
import quote.QuoteContract

class MainActivity : AppCompatActivity(), QuoteContract.QuoteView {
    private val presenter = injectQuotePresenter(this)

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
        Log.d(this::class.simpleName, error.message)
        Toast.makeText(this, "Network Error happens", Toast.LENGTH_SHORT ).show()
    }

    override fun showLoadingSpinner(visibility: Boolean) {
        loadingSpinner.visibility = if (visibility) View.VISIBLE else View.GONE
    }
}
