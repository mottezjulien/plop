import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';
import 'package:get_storage/get_storage.dart';

import 'app.dart';
import 'generic/redirect/health/health_check_repository.dart';
import 'generic/redirect/settings.dart';

void main() async {

  WidgetsFlutterBinding.ensureInitialized();
  await EasyLocalization.ensureInitialized();

  await GetStorage.init();

  HealthCheckRepository healthCheckRepository = HealthCheckRepository();
  healthCheckRepository.check();

  await Settings.init();

  runApp(
      EasyLocalization(
        supportedLocales: const [Locale('en'), Locale('fr')],
        path: 'assets/translations',
        fallbackLocale: const Locale('fr'),
        //startLocale: const Locale('fr'),
        startLocale: Settings.hasPlayer()
            ? Locale(Settings.player.language.toLowerCase())
            : const Locale('fr'),
        child: const App(),
      )
  );

}


