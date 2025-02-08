import 'package:plop/contexts/auth/auth-repository.dart';
import 'package:plop/contexts/game/game-repository.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../contexts/auth/auth.dart';
import '../contexts/game/game.dart';
import '../contexts/player/player-repository.dart';
import '../contexts/player/player.dart';


class Settings {

  static String urlServer() {
    return 'https://81ad-2a01-e0a-db5-bd20-41b-a00d-698b-572.ngrok-free.app';
  }

  static const String keyLocalStorePlayerId = '7bbb6370-147b-4944-b33d-7d9859756e91';
  static const String keyLocalStoreGameId = '23425bd7-2ae6-4ba8-a98c-fe87fc39c168';

  static Future<void> init() async {

    final prefs = await SharedPreferences.getInstance();
    final AuthRepository authRepository = AuthRepository();
    final PlayerRepository playerRepository = PlayerRepository();
    final GameRepository gameRepository = GameRepository();



    String? currentPlayerId = prefs.getString(keyLocalStorePlayerId);
    if(currentPlayerId != null) {
      _self._player = await playerRepository.findById(currentPlayerId);
      Auth auth = await authRepository.create();
      _self._auth = auth;
    }

    print("need to create token everty time ?? When reload app ??"); //TODO
    Auth auth = await authRepository.create();
    _self._auth = auth;





    String? currentGameId = prefs.getString(keyLocalStoreGameId);
    if(currentGameId != null) {
      _self._game = await gameRepository.findById(currentPlayerId!);
    }

  }

  static final Settings _self = Settings();

  Player? _player;
  Auth? _auth;
  Game? _game;

  static bool hasPlayer() {
    return _self._player != null;
  }

  static bool hasGame() {
    return _self._game != null;
  }

  static set auth(Auth auth) => _self._auth = auth;

  static Auth get auth => _self._auth!;

  static Future<void> setPlayer(Player player) async {
    _self._player = player;
    final prefs = await SharedPreferences.getInstance();
    await prefs.setString(keyLocalStorePlayerId, player.id);
  }

  static Player get player => _self._player!;

  static Future<void> game(Game game) async {
    _self._game = game;
    final prefs = await SharedPreferences.getInstance();
    await prefs.setString(keyLocalStoreGameId, game.id);
  }

}
