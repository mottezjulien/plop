import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';

import 'package:go_router/go_router.dart';
import 'package:plop/views/game/game-view.dart';
import 'package:plop/views/init/game/init-player-view.dart';
import 'package:plop/views/init/player/init-player-view.dart';

import 'config/settings.dart';

class App extends StatelessWidget {

  const App({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp.router(
      title: 'The Plop App',

      locale: context.locale,
      supportedLocales: context.supportedLocales,
      localizationsDelegates: context.localizationDelegates,

      themeMode: ThemeMode.dark,
      theme: _themeData(Brightness.light),
      darkTheme: _themeData(Brightness.dark),

      routerConfig: _router()
    );
  }

  ThemeData _themeData(Brightness brightness) {
    return ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepOrange, brightness: brightness),
        useMaterial3: true,
        brightness: brightness
    );
  }

  static const initGameViewName = "initGame";

  GoRouter _router() {
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
          path: '/init',
          routes: [
            GoRoute(
                path: '/player',
                builder: (context, state) => const InitPlayerView(),
            ),
            GoRoute(
              name: initGameViewName,
              path: '/game',
              builder: (context, state) => const InitGameView(),
            ),
          ]
        ),
        GoRoute(
          path: '/game',
          builder: (context, state) => GameView(),
        ),
      ],
    );
  }
}
