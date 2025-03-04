
import '../../../generic/redirect/settings.dart';
import '../http/headers.dart';
import '../repository-exception.dart';


import 'package:http/http.dart' as http;

class HealthCheckRepository {

  static const String path = '/actuator/health';

  Future<void> check() async {
    String url = "${Settings.urlServer()}$path";
    Uri uri = Uri.parse(url);
    final http.Response response = await http.get(uri,
        headers: Headers.noAuth()
    );
    if(response.statusCode >= 400) {
      throw RepositoryException(response.statusCode, response.body);
    }
  }

}