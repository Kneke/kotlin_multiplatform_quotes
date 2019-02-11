package de.cknetsc.multiapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import data.Quote
import kotlinx.android.synthetic.main.activity_main.*
import presenter.QuoteContract
import quotePresenter

class MainActivity : AppCompatActivity(), QuoteContract.QuoteView {

    private val presenter = quotePresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nextButton.setOnClickListener { loadQuoteInUI() }
        loadQuoteInUI()
    }

    fun loadQuoteInUI() {
        loadingSpinner.visibility = View.VISIBLE
        quoteText.text = ""
        authorText.text = ""
        presenter.showQuote()
    }

    override fun setQuote(quote: Quote) = runOnUiThread {
        quoteText.text = quote.quote
        authorText.text = quote.author
        loadingSpinner.visibility = View.GONE
    }
}
