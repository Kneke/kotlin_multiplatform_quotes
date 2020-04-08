import 'package:flutter/material.dart';
import '../../channel/config/quote_viewmodel_config.dart';
import '../../channel/stream_channel.dart';
import '../../model/quote/quote_model.dart';
import '../legal/legal_page.dart';
import 'widget/next_button_widget.dart';
import 'widget/quote_widget.dart';

class QuotePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: QuotePageContent()
    );
  }
}

class QuotePageContent extends StatefulWidget {
  @override
  _QuotePageContentState createState() => _QuotePageContentState();
}

class _QuotePageContentState extends State<QuotePageContent> {
  final viewModelChannel = StreamChannel(QuoteViewModelChannelConfig());

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
      return Expanded(child: Center(child: CircularProgressIndicator()));
    } else {
      return Expanded(
        child: Stack(
          children: <Widget>[
            Align(alignment: FractionalOffset.center, child: QuoteWidget(_currentQuoteModel.quote)),
            Align(
                alignment: FractionalOffset.bottomCenter,
                child: Container(margin: EdgeInsets.all(50), child: NextButton(_loadNextQuote))),
          ],
        ),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Container(
        child: Column(
      children: <Widget>[
        Container(
          margin: EdgeInsets.fromLTRB(10, 20, 10, 10),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.end,
            children: <Widget>[
              IconButton(
                icon: Icon(Icons.info_outline),
                iconSize: 30,
                onPressed: () {
                  Navigator.push(context, MaterialPageRoute(builder: (context) => LegalPage()));
                },
              )
            ],
          ),
        ),
        getBody()
      ],
    ));
  }
}
