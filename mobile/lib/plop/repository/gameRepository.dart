
import 'dart:convert';

import 'package:http/http.dart' as http;

import '../../config/settings.dart';

class GameRepository {

  static final String path = '/games';

  Future<GameResponse> generate(String gameCode) async {


    String urlServer = Settings.urlServer();

    http.Response response = await http.post(
      Uri.parse(urlServer + path),
      headers: <String, String> {
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(<String, String>{
        'code': gameCode,
      }),
    );
  }


}

class GameResponse {

}