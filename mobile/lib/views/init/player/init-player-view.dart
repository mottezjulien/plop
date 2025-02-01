import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';

import '../../generic/form/text-field-with-label.dart';
import 'init-player-select-lang-widget.dart';
import 'init-player-view-model.dart';

class InitPlayerView extends StatefulWidget {

  const InitPlayerView({super.key});

  @override
  State<InitPlayerView> createState() => _InitPlayerViewState();

}

class _InitPlayerViewState extends State<InitPlayerView> {

  final InitPlayerViewModel _viewModel = InitPlayerViewModel();

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
                  TextFieldGenericWidget(
                      label: 'init.enter_player_name'.tr(),
                      onChanged: (name) => _viewModel.setPlayerName(name)
                  ),
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




