import 'package:flutter/services.dart';
import 'channel_config.dart';

class ResourceChannel {
  final ChannelConfig _channelConfig;
  var _methodChannel;

  ResourceChannel(this._channelConfig) {
    _methodChannel = MethodChannel(_channelConfig.getMethodChannelName());
  }

  Future<String> get(param) async {
    try {
      var resource = await _methodChannel.invokeMethod(_channelConfig.getChannelMethods()[0].toString(), param);
      return resource;
    } on Exception catch (e) {
      print(e);
      return null;
    }
  }
}
