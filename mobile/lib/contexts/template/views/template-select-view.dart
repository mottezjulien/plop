
import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';

import '../../../generic/config/device.dart';
import '../../../generic/config/router.dart';
import '../../../generic/redirect/dialog_services.dart';
import '../../../generic/redirect/generic/form/text-field-with-label.dart';
import '../../../generic/redirect/settings.dart';
import '../../player/data/player-repository.dart';
import '../../player/player.dart';
import '../data/template-repository.dart';
import '../domain/template.dart';
import 'language-select-widget.dart';

class SelectTemplateView extends StatelessWidget {

  final _ViewModel _viewModel = _ViewModel();

  SelectTemplateView({super.key});


  //TODO manage init
  /*@override
  void initState() {
    super.initState();
    if(!Settings.hasAuth()) {
      showDialogOnlyDone(
          context: context,
          title: 'init.welcome_popup.title'.tr(),
          content: 'init.welcome_popup.content'.tr()
      );
    }
  }*/

  @override
  Widget build(BuildContext context) {
    final ColorScheme colorScheme = Theme.of(context).colorScheme;
    return Scaffold(
        appBar: AppBar(
          backgroundColor: colorScheme.inversePrimary,
          title: Text('template.select.title'.tr())
        ),
        body: Center(
          child:
          Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                const LanguageSelectWidget(),
                ValueListenableBuilder(
                    valueListenable: _viewModel.isSelectedLanguage,
                    builder: (context, value, child) {
                      if(value) {
                        return Column(
                          children: [
                            const SizedBox(height: 8),
                            TextFieldGenericWidget(
                                label: 'template.select.enter_player_name'.tr(),
                                onChanged: (name) => _viewModel.setPlayerName(name)
                            ),
                            const SizedBox(height: 8),
                            TextFieldGenericWidget(
                                label: 'template.select.enter_game_code'.tr(),
                                onChanged: (value) => _viewModel.setTemplateCode(value)
                            )
                          ],
                        );
                      }
                      return const SizedBox.shrink();
                })
              ]
          ),
        ),
        floatingActionButton: ValueListenableBuilder<bool>(
            valueListenable: _viewModel.isLoadingSubmit,
            builder: (context, isLoadingValue, child) {
              return FloatingActionButton(
                backgroundColor: colorScheme.inversePrimary,
                onPressed: () async {
                  if(!isLoadingValue) {
                    _viewModel.isLoadingSubmit.value = true;
                    try {
                      await _viewModel.submit(context);
                      _viewModel.isLoadingSubmit.value = false;
                    } catch (e) {
                      _viewModel.isLoadingSubmit.value = false;
                    }
                  }
                },
                child: isLoadingValue ? const CircularProgressIndicator() :
                const Icon(Icons.done),
              );
            })
    );
  }
}

class _ViewModel {

  final TemplateRepository templateRepository = TemplateRepository();
  final PlayerRepository playerRepository = PlayerRepository();

  String _playerName = "";
  String _templateCode = "";

  final ValueNotifier<bool> isSelectedLanguage = ValueNotifier(false);
  final ValueNotifier<bool> isLoadingSubmit = ValueNotifier(false);

  Future<void> submit(BuildContext context) async {
    if(!isSelectedLanguage.value) {
      isSelectedLanguage.value = true;
    } else {
      if(_playerName.isEmpty) {
        DialogService.showTopErrorDialog(context, "pouettt :)");
      } else if(_templateCode.isEmpty) {
        DialogService.showTopErrorDialog(context, "pouettt 2 :)");
      } else {
        Template? template = await templateRepository.findByCode(code: _templateCode);
        if(template == null) {
          DialogService.showTopErrorDialog(context, "pouettt 3 :)");
        } else {
          try {
            //Player
            Player player = await playerRepository.create(
                name: _playerName,
                deviceId: await Device.id(),
                locale: context.locale
            );
            Settings.template = template;
            Settings.player = player;

            context.pushNamed(AppRouter.nameGameMenu);
          } catch (e) {
            DialogService.showTopErrorDialog(context, "pouettt 4 :)");
          }
        }
      }
    }
  }

  void setPlayerName(String playerName) {
    _playerName = playerName;
  }

  void setTemplateCode(String templateCode) {
    _templateCode = templateCode;
  }

}