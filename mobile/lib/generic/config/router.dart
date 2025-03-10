
import 'package:go_router/go_router.dart';

import '../../contexts/game/views/game-menu-view.dart';
import '../../contexts/game/views/game-view-wip.dart';
import '../../contexts/template/views/template-select-view.dart';
import '../redirect/settings.dart';

class AppRouter {

  static const nameSelectTemplate = "template-select";
  static const pathSelectTemplate = "/template/select";

  static const nameGameMenu = "game-menu";
  static const pathGameMenu = "/game/menu";

  static const nameGameView = "game-view";
  static const pathGameView = "/game";

  static GoRouter create() {
    return GoRouter(
      redirect: (context, state) {
        //final currentPage = state.matchedLocation;



        if(state.name == null) {
          if(Settings.hasTemplate()) {
            return pathGameMenu;
          }
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
          builder: (context, state) => SelectTemplateView(),
        ),
        GoRoute(
          name: nameGameMenu,
          path: pathGameMenu,
          builder: (context, state) => GameMenuView(),
        ),
        GoRoute(
          name: nameGameView,
          path: pathGameView,
          builder: (context, state) => GameViewWip(),
        )

      ],
    );
  }

}
