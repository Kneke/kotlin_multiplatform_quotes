import '../channel_config.dart';

class QuoteViewModelChannelConfig extends ChannelConfig {
  @override
  String getMethodChannelName() {
    return 'de.kneke.common/quote';
  }

  @override
  String getEventChannelName() {
    return 'de.kneke.common/quotestream';
  }

  @override
  List<String> getChannelMethods() {
    return ["getQuoteViewModel"];
  }
}
