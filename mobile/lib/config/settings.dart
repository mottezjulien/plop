class Settings {

  static final String welcomePopup = 'welcomePopup';

  static String urlServer() {
    return 'https://81ad-2a01-e0a-db5-bd20-41b-a00d-698b-572.ngrok-free.app';
  }

  static void set(String key, bool value) {
    _self.config[key] = value;
  }

  static bool get(String key) {
    return _self.config[key] ??= false;
  }

  static Settings _self = Settings();

  Map<String, bool> config = {};





}