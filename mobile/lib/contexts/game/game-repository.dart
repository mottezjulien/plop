
import 'dart:convert';

import 'package:http/http.dart' as http;

import '../../config/settings.dart';
import '../repository-exception.dart';

class GameRepository {

  static final String path = '/games';

  Future<GameResponse> generate(String gameCode) async {

    String url = Settings.urlServer() + path + "/generate";
    Uri uri = Uri.parse(url);

    final http.Response response = await http.post(uri,
      headers: <String, String> {
        'Content-Type': 'application/json; charset=UTF-8',
        'Accept': 'application/json',
        'Authorization': 'Bearer abcToken',
      },
      body: jsonEncode(<String, String>{
        'code': gameCode,
      }),
    );
    if(response.statusCode >= 400) {
      throw RepositoryException(response.statusCode, response.body);
    }
    return GameResponse.fromJsonMap(jsonDecode(response.body));
  }


}

class GameResponse {

  static GameResponse fromJsonMap(jsonDecode) {
    return GameResponse(
      id: jsonDecode['id'],
      code: jsonDecode['code'],
      label: jsonDecode['label'],
    );
  }

  String? id, code, label;

  GameResponse({required this.id, required this.code, required this.label});

}