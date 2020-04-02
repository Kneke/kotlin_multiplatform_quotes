import UIKit
import Flutter
import main

enum ChannelName {
    static let quote = "de.kneke.common/quote"
    static let quotestream = "de.kneke.common/quotestream"
}

@UIApplicationMain
@objc class AppDelegate: FlutterAppDelegate, FlutterStreamHandler {
    
    private var viewModel: QuoteViewModel = ClientModuleKt.injectClient.quoteViewModel()
    private var eventSink: FlutterEventSink?
    
    override func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        GeneratedPluginRegistrant.register(with: self)
        
        let controller : FlutterViewController = window?.rootViewController as! FlutterViewController
        
        let quoteChannel = FlutterMethodChannel(name: ChannelName.quote, binaryMessenger: controller.binaryMessenger)
        quoteChannel.setMethodCallHandler({
            [weak self] (call: FlutterMethodCall, result: FlutterResult) -> Void in
            // Note: this method is invoked on the UI thread.
            switch call.method {
            case "getQuoteViewModel":
                let freshData = call.arguments as! Bool
                self?.viewModel.get(freshData: freshData)
            default:
                result(FlutterMethodNotImplemented)
            }
        })
        
        let quoteEventChannel = FlutterEventChannel(name: ChannelName.quotestream, binaryMessenger: controller.binaryMessenger)
        quoteEventChannel.setStreamHandler(self)
        
        return super.application(application, didFinishLaunchingWithOptions: launchOptions)
    }
    
    func onListen(withArguments arguments: Any?, eventSink: @escaping FlutterEventSink) -> FlutterError? {
        self.eventSink = eventSink
        self.viewModel.quoteModel.watch {quoteModel in
            let quoteJson = quoteModel!.toJsonString()
            self.eventSink!(quoteJson)
        }
        return nil
    }
    
    func onCancel(withArguments arguments: Any?) -> FlutterError? {
        self.eventSink = nil
        return nil
    }
}
