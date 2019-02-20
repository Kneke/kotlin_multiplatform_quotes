package de.cknetsc.multiapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import data.Quote
import injectQuotePresenter
import kotlinx.android.synthetic.main.activity_main.*
import presenter.QuoteContract

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
        presenter.cancelJobs()
    }

    fun loadQuoteInUI(loadNewQuote: Boolean = false) {
        loadingSpinner.visibility = View.VISIBLE
        quoteText.text = ""
        authorText.text = ""
        presenter.showQuote(loadNewQuote)
    }

    override fun setQuote(quote: Quote) {
        quoteText.text = quote.quote
        authorText.text = quote.author
        loadingSpinner.visibility = View.GONE
    }

    override fun showError(error: Throwable) {
        Log.d(this::class.simpleName, error.message)
    }
}
