import 'dart:async';
import 'package:flutter/services.dart';
import '../channel_config.dart';

class ViewModelChannel {
  final ChannelConfig _channelConfig;
  var _methodChannel;
  var _eventChannel;

  StreamSubscription streamSubscription;

  ViewModelChannel(this._channelConfig) {
    _methodChannel = MethodChannel(_channelConfig.getMethodChannelName());
    _eventChannel = EventChannel(_channelConfig.getEventChannelName());
  }

  void update(param) {
    try {
      _methodChannel.invokeMethod(_channelConfig.getChannelMethods()[0].toString(), param);
    } on Exception catch (e) {
      print(e);
    }
  }

  void watch(callback) {
    try {
      if (streamSubscription == null) {
        streamSubscription = _eventChannel.receiveBroadcastStream().listen(callback);
      }
    } on Exception catch (e) {
      print(e);
    }
  }

  void clear() {
    if (streamSubscription != null) {
      streamSubscription.cancel();
      streamSubscription = null;
    }
  }
}
