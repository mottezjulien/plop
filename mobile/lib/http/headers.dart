
import '../config/settings.dart';

class Headers {

  static Map<String, String> noAuth() {
    return {
      'Content-Type': 'application/json; charset=UTF-8',
      'Accept': 'application/json'
    };
  }

  static Map<String, String> withAuth() {
    String token = Settings.auth.token;
    return {
      'Content-Type': 'application/json; charset=UTF-8',
      'Accept': 'application/json',
      'Authorization': token
    };
  }

}