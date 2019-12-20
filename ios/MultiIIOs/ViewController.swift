//
//  ViewController.swift
//  MultiIIOs
//
//  Created by Knetschke, Christoph (415) on 10.01.19.
//  Copyright © 2019 Knetschke, Christoph (415). All rights reserved.
//

import UIKit
import main

class ViewController: UIViewController {
    
    @IBOutlet weak var quoteLabel: UILabel!
    @IBOutlet weak var authorLabel: UILabel!
    @IBOutlet weak var loadingSpinner: UIActivityIndicatorView!
    @IBOutlet weak var nextButton: UIButton!
    @IBOutlet weak var output: UILabel!

    var viewModel: QuoteViewModel = ClientModuleKt.injectClient.quoteViewModel()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
                
        viewModel.quoteResource.watch { quoteRes in
            switch quoteRes {
            case is ResourceLoading<Quote>:
                self.showLoadingSpinner(visibility: true)
            case let success as ResourceSuccess<Quote>:
                self.setQuote(quote: success.data!)
            case let error as ResourceFailure<Quote>:
                self.showError(error: error.throwable)
            default:
                print("Will not be called")
            }
        }
        
        loadQuoteInUI(loadFreshQuote: false)
    }
    
    @IBAction func onNextButtonClick(_ sender: Any) {
        loadQuoteInUI(loadFreshQuote: true)
    }
    
    func loadQuoteInUI(loadFreshQuote: Bool) {
        quoteLabel.text = ""
        authorLabel.text = ""
        viewModel.get(freshData: loadFreshQuote)
    }
    
    func setQuote(quote: Quote) {
        quoteLabel.text = quote.quote
        authorLabel.text = quote.author
        showLoadingSpinner(visibility: false)
    }
    
    func showError(error: KotlinThrowable) {
        print(error)
        showLoadingSpinner(visibility: false)

        
        let alert = UIAlertController(title: nil, message: "Network Error happens", preferredStyle: .alert)
        self.present(alert, animated: true, completion: nil)
        DispatchQueue.main.asyncAfter(deadline: DispatchTime.now() + 2) {
            alert.dismiss(animated: true, completion: nil)
        }
    }
    
    func showLoadingSpinner(visibility: Bool) {
        if (visibility) { loadingSpinner.startAnimating() }
        else { loadingSpinner.stopAnimating() }
    }
}
