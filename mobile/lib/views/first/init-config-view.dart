import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';

import '../../config/settings.dart';
import '../../contexts/game/game-repository.dart';
import 'init-config-select-lang-widget.dart';
import 'init-config-text-field-game-code-widget.dart';

class InitConfigView extends StatefulWidget {
  @override
  State<InitConfigView> createState() => _InitConfigViewState();
}

class _InitConfigViewState extends State<InitConfigView> {

  final InitConfigViewModel _viewModel = new InitConfigViewModel();

  @override
  Widget build(BuildContext context) {
    final ColorScheme colorScheme = Theme.of(context).colorScheme;
    return Scaffold(
        appBar: AppBar(
            backgroundColor: colorScheme.inversePrimary,
            title: Text('first.title'.tr())),
        body: Center(
          child:
          Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                if(_viewModel.isFirstStep())
                  const InitConfigSelectLanguageWidget(),
                if(_viewModel.isSecondStep())
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
  FIRST_LANGUAGE, SECOND_GAME
}

class InitConfigViewModel {

  final GameRepository repository = GameRepository();

  FirstViewStep _step = FirstViewStep.FIRST_LANGUAGE;

  String _game = "";

  Future<void> next(BuildContext context) async {

    /*
    en 1 rajouter l'étape de récupération du nom
    en 2 créer un player (appel en back)
    en 3 stocker localement les données (locale, nom, player id) et réutiliser au démarrage de l'application'

     */

    if(isFirstStep()) {
      await showDialogOnlyDone(
        context: context,
        title: 'first.welcome_popup.title'.tr(),
        content: 'first.welcome_popup.content'.tr(),
      );
      _step = FirstViewStep.SECOND_GAME;
    } else  {
      GameResponse game = await repository.generate(getGame());
      print(game.label);
    }
  }


  Future<String?> showDialogOnlyDone({
    required BuildContext context,
    required String title,
    required String content}) {
    print("showDialogOnlyDone");
    return showDialog<String>(
      context: context,
      builder: (BuildContext context) => AlertDialog(
        title: Text(title),
        content: Text(content),
        actions: <Widget>[
          TextButton(
            onPressed: () => Navigator.pop(context, 'OK'),
            child: const Icon(Icons.done),
          ),
        ],
      ),
    );
  }

  bool isFirstStep() {
    return _step == FirstViewStep.FIRST_LANGUAGE;
  }

  bool isSecondStep() {
    return _step == FirstViewStep.SECOND_GAME;
  }

  void setGame(String value) {
    _game = value;
  }

  String getGame() {
    return _game;
  }

  void displayedWelcomePopup() {
    Settings.set(Settings.welcomePopup, true);
  }

  bool needsWelcomePopup() {
    return isSecondStep() && !Settings.get(Settings.welcomePopup);
  }

}


