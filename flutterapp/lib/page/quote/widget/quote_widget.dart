import 'package:flutter/cupertino.dart';
import '../../../model/quote/quote.dart';

class QuoteWidget extends StatelessWidget {
  final Quote quote;

  QuoteWidget(this.quote);

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: <Widget>[
        Container(
            padding: EdgeInsets.fromLTRB(20, 20, 20, 20),
            child: Text(quote != null ? quote.quote : '', textAlign: TextAlign.center)),
        Text(quote != null ? quote.author : '',
            textAlign: TextAlign.center, style: TextStyle(fontWeight: FontWeight.bold)),
      ],
    );
  }
}
