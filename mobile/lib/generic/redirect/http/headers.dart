
import '../settings.dart';

class Headers {

  static Map<String, String> noAuth() {
    return {
      'Content-Type': 'application/json; charset=UTF-8',
      'Accept': 'application/json',
      'Language': Settings.language().toString()
    };
  }

  static Map<String, String> withAuth() {
    return {
      'Content-Type': 'application/json; charset=UTF-8',
      'Accept': 'application/json',
      'Language': Settings.language().toString(),
      'Authorization': Settings.token()
    };
  }

}