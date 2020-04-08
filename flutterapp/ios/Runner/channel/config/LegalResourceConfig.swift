import Foundation
import Flutter
import main

class LegalResourceChannelConfig: ChannelConfig {
    func getMethodChannelName() -> String {
        "de.kneke.common/legal"
    }
    
    func getEventChannelName() -> String {
        ""
    }
    
    func getChannelMethods() -> [String] {
        ["getLegalText"]
    }
}

class LegalResourceChannel : BaseResourceChannel {
    
    private let licenceProvider = ClientModuleKt.injectClient.licenceProvider()
    
    init(flutterController: FlutterViewController) {
        super.init(channelConfig: LegalResourceChannelConfig(), flutterController: flutterController)
    }
    
    override func get(arguments: Any?, result: FlutterResult) {
        let htmlText = "<html><body><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0,Proxima Nova-Regular\">"
        + licenceProvider.getPrivacyPolicyHTMLText() + "\n"
        + licenceProvider.getAssetLicenceHTMLText() + "\n"
        + licenceProvider.getFossHTMLText()
        + "</body></html>"
        result(String(htmlText))
    }
}
