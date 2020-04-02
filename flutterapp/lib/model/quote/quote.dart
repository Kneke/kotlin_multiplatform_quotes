import 'dart:convert';
import 'package:json_annotation/json_annotation.dart';

part 'quote.g.dart';

@JsonSerializable()
class Quote {
  Quote(this.id, this.quote, this.author, this.permalink);

  final int id;
  final String quote;
  final String author;
  final String permalink;

  factory Quote.fromJson(Map<String, dynamic> json) => _$QuoteFromJson(json);
  factory Quote.fromJsonString(String jsonString) => _$QuoteFromJson(json.decode(jsonString));
  Map<String, dynamic> toJson() => _$QuoteToJson(this);
  String toJsonString() => _$QuoteToJson(this).toString();
}