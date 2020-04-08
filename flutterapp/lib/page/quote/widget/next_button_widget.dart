import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class NextButton extends StatelessWidget {
  final nextCallback;

  NextButton(this.nextCallback);

  @override
  Widget build(BuildContext context) {
    return FlatButton(
      textColor: Colors.blueAccent,
      onPressed: () => nextCallback(true),
      child: Text('NEXT', style: TextStyle(fontSize: 16.0)),
    );
  }
}
