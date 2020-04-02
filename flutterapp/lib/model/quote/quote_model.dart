import 'dart:convert';
import 'package:json_annotation/json_annotation.dart';
import 'quote.dart';

part 'quote_model.g.dart';

@JsonSerializable(explicitToJson: true)
class QuoteModel {
  QuoteModel(this.loading, this.quote, this.error);

  final bool loading;
  final Quote quote;
  final String error;

  factory QuoteModel.fromJson(Map<String, dynamic> json) => _$QuoteModelFromJson(json);
  factory QuoteModel.fromJsonString(String jsonString) => _$QuoteModelFromJson(json.decode(jsonString));
  Map<String, dynamic> toJson() => _$QuoteModelToJson(this);
  String toJsonString() => _$QuoteModelToJson(this).toString();
}