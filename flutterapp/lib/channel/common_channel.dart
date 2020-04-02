import 'dart:async';

import 'package:flutter/services.dart';

class CommonChannel {
  static const platform = MethodChannel('de.kneke.common/quote');
  static const stream = EventChannel('de.kneke.common/quotestream');

  StreamSubscription _quoteStream;

  void getQuoteViewModel(freshData) {
    try {
      print("Get Quote View Model");
      platform.invokeMethod('getQuoteViewModel', freshData);
    } on PlatformException catch (e) {
      print('ERROR HAPPENS !!!');
      print(e);
    }
  }

  void watchQuoteViewModel(callback) {
    try {
      if (_quoteStream == null) {
        print("IM WATCHING");
        _quoteStream = stream.receiveBroadcastStream().listen(callback);
      }
    } on Exception catch (e) {
      print('ERROR HAPPENS Stream !!!');
      print(e);
    }
  }

  void clearQuoteViewModel() {
    if (_quoteStream != null) {
      _quoteStream.cancel();
      _quoteStream = null;
    }
  }
}