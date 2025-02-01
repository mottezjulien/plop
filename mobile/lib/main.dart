import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';

import 'app.dart';
import 'config/settings.dart';

void main() async {

  WidgetsFlutterBinding.ensureInitialized();
  await EasyLocalization.ensureInitialized();

  await Settings.init();

  runApp(
      EasyLocalization(
        supportedLocales: const [Locale('en'), Locale('fr')],
        path: 'assets/translations',
        fallbackLocale: const Locale('en'),
        startLocale: Settings.hasPlayer()
            ? Settings.player.locale
            : const Locale('fr'),
        child: const App(),
      )
  );

}


