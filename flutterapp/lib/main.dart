import 'package:flutter/material.dart';
import 'page/quote/quote_page.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Multi Quotes',
        theme: ThemeData(
          brightness: Brightness.light,
          primaryColor: Colors.black,
          accentColor: Colors.white,
        ),
        home: QuotePage());
  }
}
