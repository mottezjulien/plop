
import 'package:go_router/go_router.dart';

import '../../contexts/game/views/game-menu-view.dart';
import '../../contexts/template/views/template-select.-view.dart';

class AppRouter {

  static const nameSelectTemplate = "template-select";
  static const pathSelectTemplate = "/template/select";

  static const nameGameMenu = "game-menu";
  static const pathGameMenu = "/game/menu";

  static GoRouter create() {
    return GoRouter(
      redirect: (context, state) {
        //final currentPage = state.matchedLocation;
        if(state.name == null) {
          return pathSelectTemplate;
        }

        /*if(state.name != null)  {

        }*/

        /*if(currentPage.startsWith("/init")) {
          return null;
        }*/
        /*if(!Settings.hasPlayer()) {
          return "/init/player";
        }
        if(!Settings.hasGame()) {
          return "/init/game";
        }*/
        return null;
      },
      routes: [
        GoRoute(
          name: nameSelectTemplate,
          path: pathSelectTemplate,
          builder: (context, state) => const SelectTemplateView(),
        ),
        GoRoute(
          name: nameGameMenu,
          path: pathGameMenu,
          builder: (context, state) => const GameMenuView(),
        )
        /*GoRoute(
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
          builder: (context, state) => GameView(),
        ),*/
      ],
    );
  }

}
