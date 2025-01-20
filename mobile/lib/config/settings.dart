class Settings {

  static final String welcomePopup = 'welcomePopup';

  static void set(String key, bool value) {
    _self.config[key] = value;
  }

  static bool get(String key) {
    return _self.config[key] ??= false;
  }

  static Settings _self = Settings();

  Map<String, bool> config = {};



}