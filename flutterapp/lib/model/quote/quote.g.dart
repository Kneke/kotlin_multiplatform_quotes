// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'quote.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

Quote _$QuoteFromJson(Map<String, dynamic> json) {
  return Quote(
    json['id'] as int,
    json['quote'] as String,
    json['author'] as String,
    json['permalink'] as String,
  );
}

Map<String, dynamic> _$QuoteToJson(Quote instance) => <String, dynamic>{
      'id': instance.id,
      'quote': instance.quote,
      'author': instance.author,
      'permalink': instance.permalink,
    };
