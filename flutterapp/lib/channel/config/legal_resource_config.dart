import '../channel_config.dart';

class LegalResourceChannelConfig extends ChannelConfig {
  @override
  String getMethodChannelName() {
    return 'de.kneke.common/legal';
  }

  @override
  String getEventChannelName() {
    return null;
  }

  @override
  List<String> getChannelMethods() {
    return ["getLegalText"];
  }
}
