import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
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
            padding: EdgeInsets.all(30),
            child: Text(
              quote != null ? quote.quote.toUpperCase() : '',
              textAlign: TextAlign.center,
              style: TextStyle(height: 1.5, fontSize: 18.0, wordSpacing: 2.0),
            )),
        Container(
          padding: EdgeInsets.all(20),
          child: Text(quote != null ? quote.author.toUpperCase() : '',
              textAlign: TextAlign.center,
              style: TextStyle(
                color: Colors.grey,
                fontSize: 13,
                fontWeight: FontWeight.w300,
                shadows: <Shadow>[
                  Shadow(
                    offset: Offset(2.0, 2.0),
                    blurRadius: 3.0,
                    color: Color.fromARGB(40, 0, 0, 0),
                  ),
                ],
              )),
        ),
      ],
    );
  }
}
