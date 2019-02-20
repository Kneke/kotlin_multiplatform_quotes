//
//  ViewController.swift
//  MultiIIOs
//
//  Created by Knetschke, Christoph (415) on 10.01.19.
//  Copyright © 2019 Knetschke, Christoph (415). All rights reserved.
//

import UIKit
import main

class ViewController: UIViewController, QuoteContractQuoteView {
    
    @IBOutlet weak var quoteLabel: UILabel!
    @IBOutlet weak var authorLabel: UILabel!
    @IBOutlet weak var loadingSpinner: UIActivityIndicatorView!
    @IBOutlet weak var nextButton: UIButton!
    @IBOutlet weak var output: UILabel!
    
    var presenter: QuoteContractQuotePresenter!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        
        //quote.presenter = QuotePresenterImpl(quoteView: self)
        presenter = CommonModuleKt.injectQuotePresenter(quoteView: self)
        
        loadQuoteInUI(loadFreshQuote: false)
    }
    
    @IBAction func onNextButtonClick(_ sender: Any) {
        loadQuoteInUI(loadFreshQuote: true)
    }
    
    func loadQuoteInUI(loadFreshQuote: Bool) {
        loadingSpinner.startAnimating()
        quoteLabel.text = ""
        authorLabel.text = ""
        presenter.showQuote(getFreshQuote: loadFreshQuote)
    }
    
    func setQuote(quote: Quote) {
        quoteLabel.text = quote.quote
        authorLabel.text = quote.author
        loadingSpinner.stopAnimating()
    }
    
    func showError(error: KotlinThrowable) {
        print(error)
    }
}
