//
//  ViewController.swift
//  MultiIIOs
//
//  Created by Knetschke, Christoph (415) on 10.01.19.
//  Copyright © 2019 Knetschke, Christoph (415). All rights reserved.
//

import UIKit
import main
import Lottie
import GoogleMobileAds

class ViewController: UIViewController {
    
    @IBOutlet weak var quoteLabel: UILabel!
    @IBOutlet weak var authorLabel: UILabel!
    @IBOutlet weak var loadingAnimation: AnimationView!
    @IBOutlet weak var nextButton: UIButton!
    @IBOutlet weak var adBannerView: GADBannerView!
    
    var viewModel: QuoteViewModel = ClientModuleKt.injectClient.quoteViewModel()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        loadingAnimation.animation = Animation.named("loading_message_lottie")
        loadingAnimation.contentMode = .scaleAspectFit
        loadingAnimation.loopMode = .loop
        
        adBannerView.adUnitID = "ca-app-pub-8543132817382656/5652136337"
        adBannerView.rootViewController = self
        adBannerView.load(GADRequest())
        
        viewModel.quoteModel.watch(block: updateView)
        loadQuoteInUI(loadFreshQuote: false)
    }
    
    @IBAction func onNextButtonClick(_ sender: Any) {
        loadQuoteInUI(loadFreshQuote: true)
    }
    
    func loadQuoteInUI(loadFreshQuote: Bool) {
        viewModel.get(freshData: loadFreshQuote)
    }
    
    func updateView(quoteModel: QuoteModel?) {
        guard let quoteModel = quoteModel else { return }
        
        showLoadingSpinner(visibility: quoteModel.loading)
        
        if let quote = quoteModel.quote {
            quoteLabel.text = quote.quote
            authorLabel.text = quote.author
        }
        
        if let error = quoteModel.error {
            showError(error: error)
        }
    }
    
    func showError(error: String) {
        print(error)
        showLoadingSpinner(visibility: false)

        let alert = UIAlertController(title: nil, message: "Network Error happens", preferredStyle: .alert)
        self.present(alert, animated: true, completion: nil)
        DispatchQueue.main.asyncAfter(deadline: DispatchTime.now() + 2) {
            alert.dismiss(animated: true, completion: nil)
        }
    }
    
    func showLoadingSpinner(visibility: Bool = false) {
        if (visibility) {
            loadingAnimation.isHidden = false
            loadingAnimation.play()
        } else {
            loadingAnimation.isHidden = true
            loadingAnimation.stop()
        }
    }
}
