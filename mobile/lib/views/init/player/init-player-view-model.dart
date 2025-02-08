

import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import '../../../config/device.dart';
import '../../../config/router.dart';
import '../../../config/settings.dart';
import '../../../contexts/player/player-repository.dart';
import '../../../contexts/player/player.dart';
import '../../../utils/dialog.dart';

enum InitPlayerStep {
  language, name
}

class InitPlayerViewModel {

  final PlayerRepository playerRepository = PlayerRepository();

  InitPlayerStep _step = InitPlayerStep.language;

  String _playerName = "";

  Future<void> next(BuildContext context) async {
    switch(_step) {
      case InitPlayerStep.language:
        await showDialogOnlyDone(
          context: context,
          title: 'init.welcome_popup.title'.tr(),
          content: 'init.welcome_popup.content'.tr(),
        );
        _step = InitPlayerStep.name;
        break;
      case InitPlayerStep.name:
        Player player = await playerRepository.create(
            locale: context.locale,
            name: _playerName,
            deviceId: await Device.id());
        Settings.setPlayer(player);

        context.pushNamed(MyRouter.initGameViewName);
        break;
    }
  }

  bool isSelectLanguageStep() {
    return _step == InitPlayerStep.language;
  }

  bool isPlayerNameStep() {
    return _step == InitPlayerStep.name;
  }

  void setPlayerName(String value) {
    _playerName = value;
  }

}