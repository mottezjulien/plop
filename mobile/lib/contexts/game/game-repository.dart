
import 'dart:convert';

import 'package:http/http.dart' as http;

import '../../config/settings.dart';
import '../../http/headers.dart';
import '../repository-exception.dart';
import 'game.dart';

class GameRepository {

  static const String path = '/games';

  Future<Game?> findById(String gameId) async {
    String url = "${Settings.urlServer()}$path/$gameId";
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

  Future<Game> generate(String gameCode) async {
    String url = "${Settings.urlServer()}$path/generate";
    Uri uri = Uri.parse(url);

    final http.Response response = await http.post(uri,
      headers: Headers.withAuth(),
      body: jsonEncode({'code': gameCode})
    );
    if(response.statusCode >= 400) {
      throw RepositoryException(response.statusCode, response.body);
    }
    return toModel(jsonDecode(response.body));
  }


  Game toModel(Map<String, dynamic> json) {
    return Game(
      id: json['id'],
      label: json['label'],
    );
  }

}