
import '../config/settings.dart';

class Headers {

  static Map<String, String> byDefault() {
    String token = Settings.auth.token;
    return {
      'Content-Type': 'application/json; charset=UTF-8',
      'Accept': 'application/json',
      'Authorization': 'Bearer $token'
    };
  }

}