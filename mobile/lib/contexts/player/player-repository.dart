
import 'dart:convert';
import 'dart:ui';

import 'package:http/http.dart' as http;

import 'package:plop/contexts/player/player.dart';

import '../../config/settings.dart';
import '../../http/headers.dart';
import '../repository-exception.dart';

class PlayerRepository {

  static const String path = '/players';

  Future<Player?> findById(String playerId) async {
    String url = "${Settings.urlServer()}$path/$playerId";
    Uri uri = Uri.parse(url);
    final http.Response response = await http.get(uri, headers: Headers.withAuth());
    if(response.statusCode >= 404) {
      return null;
    }
    if(response.statusCode >= 400) {
      throw RepositoryException(response.statusCode, response.body);
    }
    return toModel(jsonDecode(response.body));
  }

  Future<Player> create({
    required String name,
    required String deviceId,
    required Locale locale
  }) async {
    String url = "${Settings.urlServer()}$path/";
    Uri uri = Uri.parse(url);
    final http.Response response = await http.post(
        uri,
        headers: Headers.withAuth(),
        body: jsonEncode({
              'name': name,
              'deviceId': deviceId,
              'locale': locale.languageCode
            })
    );
    if(response.statusCode >= 400) {
      throw RepositoryException(response.statusCode, response.body);
    }
    return toModel(jsonDecode(response.body));
  }

  Player toModel(Map<String, dynamic> json) {
    return Player(
      id: json['id'],
      name: json['name'],
      locale: Locale(json['locale']),
    );
  }

}