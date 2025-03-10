
import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';

import '../../../generic/config/router.dart';
import '../../../generic/redirect/settings.dart';
import '../../template/domain/template.dart';
import '../data/game-repository.dart';

class GameMenuView extends StatelessWidget {

  final GameMenuViewModel _viewModel = GameMenuViewModel();

  GameMenuView({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
          backgroundColor: Theme.of(context).colorScheme.inversePrimary,
          title: Text("Game Menu :)")
      ),
      body: Center(
        child:Column(
          children: [
            if (_viewModel.hasGame)
              TextButton(
                /*style: TextButton.styleFrom(
                shape: RoundedRectangleBorder(
                  borderRadius: const BorderRadius.all(Radius.circular(8)),
                  side: BorderSide(color: colorScheme.primary, width: 5)),
                ),*/
                  onPressed: () {
                    _viewModel.continueGame(context);
                  },
                  child: const Text('Continuer :)')
              ),
            if(!_viewModel.hasGame)
              TextButton(
                /*style: TextButton.styleFrom(
                shape: RoundedRectangleBorder(
                  borderRadius: const BorderRadius.all(Radius.circular(8)),
                  side: BorderSide(color: colorScheme.primary, width: 5)),
                ),*/
                onPressed: () {
                  _viewModel.startGame(context);
                },
                child: const Text('Démarrer :)')
              ),
            TextButton(
                onPressed: () {

                },
                child: const Text('Configuration :)')
            ),
            TextButton(
                onPressed: () {

                },
                child: const Text('Sortir :)')
            ),
          ]
        )
      )
    );
  }

}

class GameMenuViewModel {

  final GameRepository repository = GameRepository();

  final Template template;
  final bool hasGame;

  GameMenuViewModel():
        template = Settings.template,
        hasGame = Settings.hasGame();

  Future<void> startGame(BuildContext context) async {
    Settings.game = await repository.generate(template);
    context.pushNamed(AppRouter.nameGameView);
  }

  void continueGame(BuildContext context) {
    context.pushNamed(AppRouter.nameGameView);
  }

}