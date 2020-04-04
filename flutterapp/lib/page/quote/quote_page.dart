import 'package:flutter/material.dart';
import '../../channel/viewmodel/viewmodel_channel.dart';
import '../../channel/viewmodel/viewmodel_configs.dart';
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
  final viewModelChannel = ViewModelChannel(QuoteViewModelChannelConfig());

  QuoteModel _currentQuoteModel;

  @override
  void initState() {
    super.initState();
    viewModelChannel.watch(_updateQuoteViewModel);
    _loadNextQuote(false);
  }

  void _updateQuoteViewModel(jsonResponse) {
    setState(() {
      _currentQuoteModel = QuoteModel.fromJsonString(jsonResponse);
    });
  }

  void _loadNextQuote(bool fromNetwork) async {
    viewModelChannel.update(fromNetwork);
  }

  @override
  void dispose() {
    viewModelChannel.clear();
    super.dispose();
  }

  getBody() {
    if (_currentQuoteModel == null || _currentQuoteModel.loading) {
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
