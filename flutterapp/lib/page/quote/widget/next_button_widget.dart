import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class NextButton extends StatelessWidget {
  final nextCallback;

  NextButton(this.nextCallback);

  @override
  Widget build(BuildContext context) {
    return FlatButton(
      onPressed: () => nextCallback(true),
      child: Text('Next'),
    );
  }

}