package de.kneke.multiapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import de.kneke.common.viewmodel.quote.QuoteModel
import kotlinx.android.synthetic.main.activity_main.*
import de.kneke.common.injectClient
import de.kneke.multiapp.legal.LegalActivity

class MainActivity : AppCompatActivity() {

    private val viewModel = injectClient.quoteViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adBannerBottom.loadAd(AdRequest.Builder().build())

        legalNavigationButton.setOnClickListener {
            startActivity(Intent(this, LegalActivity::class.java))
        }

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
            Log.e("MainActivity", it)
            Toast.makeText(this, "Network Error happens", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showLoadingSpinner(visibility: Boolean) {
        loadingAnimation.visibility = if (visibility) View.VISIBLE else View.GONE
    }
}
