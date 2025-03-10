
import 'dart:convert';

import 'package:http/http.dart' as http;

import '../../../generic/redirect/http/headers.dart';
import '../../../generic/redirect/settings.dart';
import '../../../generic/redirect/repository-exception.dart';
import '../../template/domain/template.dart';
import '../domain/coord.dart';
import '../domain/game.dart';

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

  Future<Game> generate(Template template) async {
    String url = "${Settings.urlServer()}$path/generate";
    Uri uri = Uri.parse(url);

    final http.Response response = await http.post(uri,
      headers: Headers.withAuth(),
      body: jsonEncode({'templateId': template.id})
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

  Future<void> move(Coord coord) async {

    /*
    Game game = Settings.game;

    String url = "${Settings.urlServer()}$path/${game.id}/move";
    Uri uri = Uri.parse(url);

    Map<String, String> body = {
      'lat': coord.lat.toString(),
      'lng': coord.lng.toString()
    };

    //game is not playing bouton start

    final http.Response response = await http.post(uri,
        headers: Headers.withAuth(),
        body: jsonEncode(body)
    );
    if(response.statusCode >= 400) {
      throw RepositoryException(response.statusCode, response.body);
    }*/

  }

}