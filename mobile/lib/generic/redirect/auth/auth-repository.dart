
import 'dart:convert';

import 'package:http/http.dart' as http;

import '../../../generic/redirect/device.dart';
import '../../../generic/redirect/settings.dart';
import '../http/headers.dart';
import '../repository-exception.dart';
import 'auth.dart';

class AuthRepository {

  static const String path = '/auths';

  Future<Auth> create({String? playerId}) async {
    String url = "${Settings.urlServer()}$path";
    Uri uri = Uri.parse(url);
    Map<String, String> body = {
      'deviceId': await Device.id()
    };
    if(playerId != null) {
      body['playerId'] = playerId;
    }

    final http.Response response = await http.post(uri,
        headers: Headers.noAuth(),
        body: jsonEncode(body)
    );
    if(response.statusCode >= 400) {
      throw RepositoryException(response.statusCode, response.body);
    }
    return toModel(jsonDecode(response.body));
  }


  Future<Auth> updateWithSettingsPlayer(String playerId) async {
    String url = "${Settings.urlServer()}$path";
    Uri uri = Uri.parse(url);
    Map<String, String> body = {
      'playerId': playerId
    };
    final http.Response response = await http.post(uri,
        headers: Headers.withAuth(),
        body: body
    );
    if(response.statusCode >= 400) {
      throw RepositoryException(response.statusCode, response.body);
    }
    return toModel(jsonDecode(response.body));
  }

  Auth toModel(Map<String, dynamic> json) {
    return Auth(token: json['token']);
  }

}