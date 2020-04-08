package de.kneke.multiapp.legal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import de.kneke.common.injectClient
import de.kneke.multiapp.R
import kotlinx.android.synthetic.main.activity_legal.*

class LegalActivity : AppCompatActivity() {

    private val licenceProvider = injectClient.licenceProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_legal)

        val licenceHTML = licenceProvider.getPrivacyPolicyHTMLText() + "\n" +
                licenceProvider.getAssetLicenceHTMLText() + "\n" +
                licenceProvider.getFossHTMLText()
        legalWebView.loadData(licenceHTML, "text/html; charset=utf-8", "UTF-8")
    }
}
