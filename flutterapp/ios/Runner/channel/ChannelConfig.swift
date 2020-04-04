import Foundation

protocol ChannelConfig {
    func getMethodChannelName() -> String
    func getEventChannelName() -> String
    func getChannelMethods() -> [String]
}
