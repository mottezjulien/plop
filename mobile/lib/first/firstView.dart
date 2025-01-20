import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';

import '../config/settings.dart';
import 'firstViewGameCodeTextFieldWidget.dart';
import 'firstViewSelectLanguageWidget.dart';

class FirstView extends StatefulWidget {
  @override
  State<FirstView> createState() => _FirstViewState();
}

class _FirstViewState extends State<FirstView> {

  final FirstViewModel _viewModel = new FirstViewModel();

  @override
  Widget build(BuildContext context) {
    _showDialogs(context: context);

    final ColorScheme colorScheme = Theme
        .of(context)
        .colorScheme;
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
                  FirstViewSelectLanguageWidget(
                    onUpdate: () {
                      setState(() {});
                    },
                  ),
                if(_viewModel.isSecondStep())
                  FirstViewGameCodeTextFieldWidget(onChanged: (value) {
                    _viewModel.setGame(value);
                  }),
              ]
          ),
          //
        ),
        floatingActionButton: FloatingActionButton(
          backgroundColor: colorScheme.inversePrimary,
          onPressed: () {
            _viewModel.next();
            setState(() {});
          },
          child: const Icon(Icons.done),
        )

    );
  }

  void _showDialogs({required BuildContext context}) {
    if (_viewModel.needsWelcomePopup()) {
      showDialogOnlyDone(
        context: context,
        title: 'first.welcome_popup.title'.tr(),
        content: 'first.welcome_popup.content'.tr(),
      );
      _viewModel.displayedWelcomePopup();
    }
  }

  void showDialogOnlyDone({
    required BuildContext context,
    required String title,
    required String content}) {
    showDialog<String>(
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

}


enum FirstViewStep {
  FIRST_LANGUAGE, SECOND_GAME
}

class FirstViewModel {

  FirstViewStep _step = FirstViewStep.FIRST_LANGUAGE;

  String _game = "";

  void next() {
    if(isFirstStep()) {
      _step = FirstViewStep.SECOND_GAME;
    } else  {
      print(getGame());
    }

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


