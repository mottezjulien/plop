import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';

class FirstView extends StatefulWidget {
  @override
  State<FirstView> createState() => _FirstViewState();
}

class _FirstViewState extends State<FirstView> {

  final FirstViewModel _viewModel = new FirstViewModel();

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
                    Text('first.select_language'.tr(),
                        style: Theme.of(context).textTheme.headlineMedium),
                  if(_viewModel.isFirstStep())
                    SelectLanguageDropdownMenu(
                      onUpdate: () {
                        setState(() {});
                      },
                    ),
                  if(_viewModel.isSecondStep())
                    Text('first.tap_game'.tr(),
                        style: Theme.of(context).textTheme.headlineMedium),
                  if(_viewModel.isSecondStep())
                    TextField(
                      decoration: const InputDecoration(
                        hintText: 'Enter a search term',
                      ),
                      onChanged: (value) {
                        _viewModel.setGame(value);
                      }
                    ),
                ]
              ),
            //
        ),
    floatingActionButton: FloatingActionButton(
      backgroundColor: colorScheme.inversePrimary,
      onPressed: () {
        if(_viewModel.isFirstStep())
          showDialog<String>(
            context: context,
            builder: (BuildContext context) => AlertDialog(
              title: Text('first.welcome_popup.title'.tr()),
              content: Text('first.welcome_popup.content'.tr()),
              actions: <Widget>[
                TextButton(
                  onPressed: () => Navigator.pop(context, 'OK'),
                  child: const Icon(Icons.done),
                ),
              ],
            ),
          );
        _viewModel.next();
        setState(() { });
      },
      child: const Icon(Icons.done),
    )

    );
  }


}

class SelectLanguageDropdownMenu extends StatelessWidget {

  final Function onUpdate;

  const SelectLanguageDropdownMenu({super.key, required this.onUpdate});

  @override
  Widget build(BuildContext context) {
    final List<Locale> locales = context.supportedLocales;
    return DropdownMenu<Locale>(
        initialSelection: context.locale,
        onSelected: (Locale? value) {
          if (value == null) return;
          context.setLocale(value);
          onUpdate();
        },
        dropdownMenuEntries:
        locales.map<DropdownMenuEntry<Locale>>((Locale locale) {
          return DropdownMenuEntry<Locale>(
              value: locale, label: locale.toLanguageTag());
        }).toList());
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

}

