import Foundation
import Flutter
import main

class QuoteViewModelChannelConfig: ChannelConfig {
    func getMethodChannelName() -> String {
        "de.kneke.common/quote"
    }
    
    func getEventChannelName() -> String {
        "de.kneke.common/quotestream"
    }
    
    func getChannelMethods() -> [String] {
        ["getQuoteViewModel"]
    }
}

class QuoteViewModelChannel : BaseStreamChannel {
    
    private let viewModel: QuoteViewModel = ClientModuleKt.injectClient.quoteViewModel()
    
    init(flutterController: FlutterViewController) {
        super.init(channelConfig: QuoteViewModelChannelConfig(), flutterController: flutterController)
    }
    
    override func update(arguments: Any?) {
        let freshData = arguments as! Bool
        self.viewModel.get(freshData: freshData)
    }
    
    override func watch(eventSink: FlutterEventSink?) {
        self.viewModel.quoteModel.watch {quoteModel in
            let quoteJson = quoteModel!.toJsonString()
            eventSink?(quoteJson)
        }
    }
}
