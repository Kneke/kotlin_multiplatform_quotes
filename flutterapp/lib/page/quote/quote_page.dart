import 'package:flutter/material.dart';
import '../../channel/common_channel.dart';
import '../../model/quote/quote_model.dart';
import 'widget/next_button_widget.dart';
import 'widget/quote_widget.dart';

class QuotePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        title: 'Flutter MultiApp',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: Scaffold(body: QuotePageContent()));
  }
}

class QuotePageContent extends StatefulWidget {
  @override
  _QuotePageContentState createState() => _QuotePageContentState();
}

class _QuotePageContentState extends State<QuotePageContent> {
  final commonChannel = CommonChannel();

  QuoteModel _currentQuoteModel;
  bool _showLoading = true;

  @override
  void initState() {
    super.initState();
    commonChannel.watchQuoteViewModel(_updateQuoteViewModel);
    _loadNextQuote(false);
  }

  void _loadNextQuote(bool fromNetwork) async {
    setState(() {
      _currentQuoteModel = null;
      _showLoading = true;
    });
    commonChannel.getQuoteViewModel(true);
  }

  void _updateQuoteViewModel(quoteViewModel) {
    setState(() {
      _currentQuoteModel = QuoteModel.fromJsonString(quoteViewModel);
      _showLoading = false;
    });
  }

  getBody() {
    if (_showLoading) {
      return CircularProgressIndicator();
    } else {
      return Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: <Widget>[
          QuoteWidget(_currentQuoteModel.quote),
          NextButton(_loadNextQuote),
        ],
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: getBody(),
    );
  }
}
