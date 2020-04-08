
import Foundation
import Flutter

class BaseResourceChannel {
    
    let channelConfig: ChannelConfig
    let flutterController: FlutterViewController
    
    init(channelConfig: ChannelConfig, flutterController: FlutterViewController) {
        self.channelConfig = channelConfig
        self.flutterController = flutterController
    }
    
    func regiterChannels() {
        let methodChannel = FlutterMethodChannel(name: channelConfig.getMethodChannelName(), binaryMessenger: flutterController.binaryMessenger)
        methodChannel.setMethodCallHandler({
            [self] (call: FlutterMethodCall, result: FlutterResult) -> Void in
            switch call.method {
            case self.channelConfig.getChannelMethods()[0]:
                self.get(arguments: call.arguments, result: result)
            default:
                print(call.method)
                print(self.channelConfig.getChannelMethods()[0])
                result(FlutterMethodNotImplemented)
            }
        })
    }
    
    func get(arguments: Any?, result: FlutterResult) {
        fatalError("Must be implemented in Subclass")
    }
}
