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
        presenter = CommonModuleKt.getPresenter(quoteView: self)
        
        loadQuoteInUI(loadFreshQuote: false)
    }
    
    @IBAction func onNextButtonClick(_ sender: Any) {
        loadQuoteInUI(loadFreshQuote: true)
    }
    
    func loadQuoteInUI(loadFreshQuote: Bool) {
        quoteLabel.text = ""
        authorLabel.text = ""
        presenter.showQuote(getFreshQuote: loadFreshQuote)
    }
    
    func setQuote(quote: Quote) {
        quoteLabel.text = quote.quote
        authorLabel.text = quote.author
    }
    
    func showError(error: KotlinThrowable) {
        print(error)
        
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
