import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';

import '../../../contexts/game/game-repository.dart';
import '../../../utils/dialog.dart';
import '../player/init-player-select-lang-widget.dart';
import 'init-config-text-field-game-code-widget.dart';
import 'init-config-text-field-player-name-widget.dart';

class OldInitConfigView extends StatefulWidget {
  @override
  State<OldInitConfigView> createState() => _InitConfigViewState();
}

class _InitConfigViewState extends State<OldInitConfigView> {

  final OldInitConfigViewModel _viewModel = OldInitConfigViewModel();

  @override
  Widget build(BuildContext context) {
    final ColorScheme colorScheme = Theme.of(context).colorScheme;
    return Scaffold(
        appBar: AppBar(
            backgroundColor: colorScheme.inversePrimary,
            title: Text('init.title'.tr())),
        body: Center(
          child:
          Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                if(_viewModel.isSelectLanguageStep())
                  const InitPlayerSelectLanguageWidget(),
                if(_viewModel.isPlayerNameStep())
                  InitConfigTextFieldPlayerNameWidget(onChanged: (String name) {
                    _viewModel.setPlayerName(name);
                  }),
                if(_viewModel.isGameCodeStep())
                  InitConfigTextFieldGameCodeWidget(onChanged: (value) {
                    _viewModel.setGame(value);
                  }),
              ]
          ),
          //
        ),
        floatingActionButton: FloatingActionButton(
          backgroundColor: colorScheme.inversePrimary,
          onPressed: () async {
            await _viewModel.next(context);
            setState(() {});
          },
          child: const Icon(Icons.done),
        )
    );
  }
}


enum FirstViewStep {
  LANGUAGE, NAME, GAME_CODE
}

class OldInitConfigViewModel {

  final GameRepository repository = GameRepository();

  FirstViewStep _step = FirstViewStep.LANGUAGE;

  String _gameCode = "";
  String _playerName = "";

  Future<void> next(BuildContext context) async {

    /*
    en 1 rajouter l'étape de récupération du nom -> Done
    en 2 créer un player (appel en back)
    en 3 stocker localement les données (locale, nom, player id) et réutiliser au démarrage de l'application'
    4 AJOUTER DES  POPUPS POUR ACCOMPAGNER LES ÉTAPES
     */
    /*
          void displayedWelcomePopup() {
    Settings.set(Settings.welcomePopup, true);
  }

  bool needsWelcomePopup() {
    return isSecondStep() && !Settings.get(Settings.welcomePopup);
  }
         */

    switch(_step) {
      case FirstViewStep.LANGUAGE:
        await showDialogOnlyDone(
          context: context,
          title: 'init.welcome_popup.title'.tr(),
          content: 'init.welcome_popup.content'.tr(),
        );
        _step = FirstViewStep.NAME;
        break;
      case FirstViewStep.NAME:
        print(_playerName);
        _step = FirstViewStep.GAME_CODE;
        break;
      case FirstViewStep.GAME_CODE:
        /*GameResponse game = await repository.generate(_gameCode);
        print(game.label);*/
        //TODO Go Next View ...
        break;
    }
  }

  bool isSelectLanguageStep() {
    return _step == FirstViewStep.LANGUAGE;
  }

  bool isPlayerNameStep() {
    return _step == FirstViewStep.NAME;
  }

  bool isGameCodeStep() {
    return _step == FirstViewStep.GAME_CODE;
  }

  void setGame(String value) {
    _gameCode = value;
  }

  void setPlayerName(String value) {
    _playerName = value;
  }







}


