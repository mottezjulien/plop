import '../views/game/game-view.dart';
import '../views/init/game/init-player-view.dart';
import '../views/init/player/init-player-view.dart';
import 'settings.dart';
import 'package:go_router/go_router.dart';



class MyRouter {

  static const initPlayerViewName = "init-player";
  static const initGameViewName = "init-game";
  static const gameViewName = "game";

  static GoRouter create() {
    return GoRouter(
      redirect: (context, state) {
        final currentPage = state.matchedLocation;
        if(currentPage.startsWith("/init")) {
          return null;
        }
        if(!Settings.hasPlayer()) {
          return "/init/player";
        }
        if(!Settings.hasGame()) {
          return "/init/game";
        }
        return null;
      },
      routes: [
        GoRoute(
          name: initPlayerViewName,
          path: '/init/player',
          builder: (context, state) => const InitPlayerView(),
        ),
        GoRoute(
          name: initGameViewName,
          path: '/init/game',
          builder: (context, state) => const InitGameView(),
        ),
        GoRoute(
          name: gameViewName,
          path: '/game',
          builder: (context, state) => const GameView(),
        ),
      ],
    );
  }

}
