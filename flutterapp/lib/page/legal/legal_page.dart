import 'dart:async';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:webview_flutter/webview_flutter.dart';
import '../../channel/config/legal_resource_config.dart';
import '../../channel/resource_channel.dart';

class LegalPage extends StatelessWidget {
  final Completer<WebViewController> _controller = Completer<WebViewController>();

  final resourceChannel = ResourceChannel(LegalResourceChannelConfig());

  Future<String> _getLegalResource() async {
    final legalHtmlText = await resourceChannel.get(null);
    return base64Encode(const Utf8Encoder().convert(legalHtmlText));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          leading: IconButton(
            icon: Icon(Icons.arrow_back),
            onPressed: () {
              Navigator.pop(context);
            },
          ),
          title: const Text('Multi Quotes'),
        ),
        body: FutureBuilder(
          future: _getLegalResource(),
          builder: (context, snapshot) {
            if (snapshot.hasData) {
              return WebView(
                initialUrl: 'data:text/html;base64,${snapshot.data}',
                javascriptMode: JavascriptMode.unrestricted,
                onWebViewCreated: _controller.complete,
              );
            } else {
              return Center(child: CircularProgressIndicator());
            }
          },
        ));
  }
}
