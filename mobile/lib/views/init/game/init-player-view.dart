import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';

import '../../../config/settings.dart';
import '../../../contexts/game/game-repository.dart';
import '../../../contexts/game/game.dart';
import '../../generic/form/text-field-with-label.dart';
import '../init-appbar-widget.dart';
import '../init-floating-action-button.dart';

class InitGameView extends StatefulWidget {

  const InitGameView({super.key});

  @override
  State<InitGameView> createState() => _InitGameViewState();
}

class _InitGameViewState extends State<InitGameView> {

  final InitGameViewModel _viewModel = InitGameViewModel();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: InitAppBar(context),
        body: Center(
          child:
          Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                TextFieldGenericWidget(
                    label: 'init.enter_game_code'.tr(),
                    onChanged: (value) => _viewModel.setGame(value)
                )
              ]
          ),
          //
        ),
        floatingActionButton: InitFloatingActionButton(
          onPressed: () => _viewModel.next(context),
        )
    );
  }
}

class InitGameViewModel {

  final GameRepository repository = GameRepository();

  String _gameCode = "";

  Future<void> next(BuildContext context) async {
    Game game = await repository.generate(_gameCode);
    Settings.game(game);
    print(game.label);
    //TODO Go Next View ...
  }

  void setGame(String value) {
    _gameCode = value;
  }


}


