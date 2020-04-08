
import Foundation
import Flutter

class BaseStreamChannel: NSObject, FlutterStreamHandler {
    
    let channelConfig: ChannelConfig
    let flutterController: FlutterViewController
    
    private var eventSink: FlutterEventSink?
    
    init(channelConfig: ChannelConfig, flutterController: FlutterViewController) {
        self.channelConfig = channelConfig
        self.flutterController = flutterController
    }
    
    func regiterChannels() {
        // Note: this methods are invoked on the UI thread.
        let methodChannel = FlutterMethodChannel(name: channelConfig.getMethodChannelName(), binaryMessenger: flutterController.binaryMessenger)
        methodChannel.setMethodCallHandler({
            [self] (call: FlutterMethodCall, result: FlutterResult) -> Void in
            switch call.method {
            case self.channelConfig.getChannelMethods()[0]:
                self.update(arguments: call.arguments)
            default:
                result(FlutterMethodNotImplemented)
            }
        })
        
        let quoteEventChannel = FlutterEventChannel(name: channelConfig.getEventChannelName(), binaryMessenger: flutterController.binaryMessenger)
        quoteEventChannel.setStreamHandler(self)
    }
    
    func onListen(withArguments arguments: Any?, eventSink events: @escaping FlutterEventSink) -> FlutterError? {
        self.eventSink = events
        watch(eventSink: self.eventSink)
        return nil
    }
    
    func onCancel(withArguments arguments: Any?) -> FlutterError? {
        self.eventSink = nil
        return nil
    }
    
    func update(arguments: Any?) {
        fatalError("Must be implemented in Subclass")
    }
    
    func watch(eventSink: FlutterEventSink?) {
        fatalError("Must be implemented in Subclass")
    }
}
