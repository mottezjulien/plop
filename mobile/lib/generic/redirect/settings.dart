
import 'package:get_storage/get_storage.dart';

import '../../contexts/player/player.dart';

class Settings {

  static String urlServer() {
    return 'https://e297-2a01-e0a-db5-bd20-8d7-19a-4d68-b369.ngrok-free.app';
  }

  static const String keyLocalStorePlayer = '7bbb6370-147b-4944-b33d-7d9859756e91';
  static const String keyLocalStoreGame = '23425bd7-2ae6-4ba8-a98c-fe87fc39c168';

  static Future<void> init() async {

    final box = GetStorage();

    Player? storedPlayer = box.read(keyLocalStorePlayer);
    if(storedPlayer != null) {
      _self._player = storedPlayer;
    }

    /*
    final AuthRepository authRepository = AuthRepository();
    final PlayerRepository playerRepository = PlayerRepository();
    final GameRepository gameRepository = GameRepository();

    */

    /*
    final pref = await SharedPreferences.getInstance();

    //TODO print("need to create token everty time ?? When reload app ??");

    String? currentPlayerId = prefs.getString(keyLocalStorePlayerId);
    if(currentPlayerId != null) {
      try {
        Auth auth = await authRepository.create(playerId: currentPlayerId);
        _self._auth = auth;
        _self._player = await playerRepository.findById(currentPlayerId);
      } on RepositoryException catch (e) {
        if(e.statusCode == 400) {
          prefs.remove(keyLocalStorePlayerId);
          _self._auth = await authRepository.create();
        }
      }
    } else {
      _self._auth = await authRepository.create();
    }

    String? currentGameId = prefs.getString(keyLocalStoreGameId);
    if(currentGameId != null) {
      _self._game = await gameRepository.findById(currentPlayerId!);
    }*/

  }

  static final Settings _self = Settings();

  Player? _player;

  static bool hasPlayer() {
    return _self._player != null;
  }

  static Player get player => _self._player!;

  /*

  Auth? _auth;
  Game? _game;



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



  static Future<void> setGame(Game game) async {
    _self._game = game;
    final prefs = await SharedPreferences.getInstance();
    await prefs.setString(keyLocalStoreGameId, game.id);
  }

  static Game get game => _self._game!;*/

}
