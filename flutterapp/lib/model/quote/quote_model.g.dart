// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'quote_model.dart';

// **************************************************************************
// JsonSerializableGenerator
// **************************************************************************

QuoteModel _$QuoteModelFromJson(Map<String, dynamic> json) {
  return QuoteModel(
    json['loading'] as bool,
    json['quote'] == null
        ? null
        : Quote.fromJson(json['quote'] as Map<String, dynamic>),
    json['error'] as String,
  );
}

Map<String, dynamic> _$QuoteModelToJson(QuoteModel instance) =>
    <String, dynamic>{
      'loading': instance.loading,
      'quote': instance.quote,
      'error': instance.error,
    };
