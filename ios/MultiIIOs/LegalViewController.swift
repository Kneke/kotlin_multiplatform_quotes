//
//  LegalViewController.swift
//  MultiIIOs
//
//  Created by Knetschke, Christoph (415) on 08.04.20.
//  Copyright © 2020 Knetschke, Christoph (415). All rights reserved.
//

import UIKit
import main
import WebKit

class LegalViewController: UIViewController {
    
    @IBOutlet weak var webView: WKWebView!

    var licenceProvider = ClientModuleKt.injectClient.licenceProvider()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        let htmlText = "<html><body><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0,Proxima Nova-Regular\">"
            + licenceProvider.getPrivacyPolicyHTMLText() + "\n"
            + licenceProvider.getAssetLicenceHTMLText() + "\n"
            + licenceProvider.getFossHTMLText()
            + "</body></html>"
        webView.loadHTMLString(htmlText, baseURL: nil)

    }

}
