
import 'package:get_storage/get_storage.dart';

import '../../contexts/game/domain/game.dart';
import '../../contexts/player/player.dart';
import '../../contexts/template/domain/template.dart';
import '../auth/auth.dart';
import '../config/language.dart';

class Settings {

  static String urlServer() {
    return 'https://6854-2a01-e0a-db5-bd20-c5ee-f70-6339-c39.ngrok-free.app';
  }

  static const String keyLocalStoreAuth = '40ad5247-7c9a-4ff8-997e-2dec1651a879';
  static const String keyLocalStorePlayer = '7bbb6370-147b-4944-b33d-7d9859756e91';
  static const String keyLocalStoreTemplate = '9e692c85-3d2b-42c9-98fa-91ea928121bd';
  static const String keyLocalStoreGame = '23425bd7-2ae6-4ba8-a98c-fe87fc39c168';


  static Future<void> init() async {

    final box = GetStorage();

    Auth? storedAuth = box.read(keyLocalStoreAuth);
    if(storedAuth != null) {
      _self._auth = storedAuth;

      Player? storedPlayer = box.read(keyLocalStorePlayer);
      if(storedPlayer != null) {
        _self._player = storedPlayer;

        Template? storedTemplate = box.read(keyLocalStorePlayer);
        if(storedTemplate != null) {
          _self._template = storedTemplate;

          Game? storedGame = box.read(keyLocalStoreGame);
          if(storedGame != null) {
            _self._game = storedGame;
          }

        }

      }
    }
  }

  static final Settings _self = Settings();

  // Auth
  static bool hasAuth() {
    return _self._auth != null;
  }

  static String token() {
    return _self._auth!.token;
  }

  static Auth get auth => _self._auth!;

  static set auth(Auth auth) {
    _self._auth = auth;
    GetStorage().write(keyLocalStoreAuth, auth);
  }
  //Auth

  // Player
  static bool hasPlayer() {
    return _self._player != null;
  }

  static Player get player => _self._player!;

  static set player(Player player) {
    _self._player = player;
    GetStorage().write(keyLocalStorePlayer, player);
  }

  static String? nullablePlayerId() {
    if(hasPlayer()) {
      return player.id;
    }
    return null;
  }
  // Player

  static Language language() {
    if(hasPlayer()) {
      return player.language;
    }
    return Language.byDefault();
  }


  //Template
  static bool hasTemplate() {
    return _self._template != null;
  }

  static Template get template => _self._template!;

  static set template(Template template) {
    _self._template = template;
    GetStorage().write(keyLocalStoreTemplate, template);
  }
  //Template

  //Game
  static bool hasGame() {
    return _self._game != null;
  }

  static Game get game => _self._game!;

  static set game(Game game) {
    _self._game = game;
    GetStorage().write(keyLocalStoreGame, game);
  }
  //Game

  Auth? _auth;
  Player? _player;
  Template? _template;
  Game? _game;

}
