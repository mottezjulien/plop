import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';
import 'package:get_storage/get_storage.dart';
import 'package:plop/generic/redirect/auth/auth-repository.dart';

import 'app.dart';
import 'generic/config/language.dart';
import 'generic/redirect/health/health_check_repository.dart';
import 'generic/redirect/settings.dart';

void main() async {

  WidgetsFlutterBinding.ensureInitialized();
  await EasyLocalization.ensureInitialized();

  await GetStorage.init();

  HealthCheckRepository healthCheckRepository = HealthCheckRepository();
  healthCheckRepository.check();

  await Settings.init();

  AuthRepository authRepository = AuthRepository();
  if(Settings.hasAuth()) {

  } else {
    authRepository.create();
  }

  runApp(
      EasyLocalization(
        //const [Locale('en'), Locale('fr')],
        supportedLocales: Language.values
            .map((language) => language.toLocale()).toList(),
        path: 'assets/translations',
        fallbackLocale: Language.byDefault().toLocale(),
        /*startLocale: Settings.hasPlayer()
            ? Locale(Settings.player.language.toLowerCase())
            : const Locale('fr'),*/
        startLocale: Settings.language().toLocale(),
        child: const App(),
      )
  );

}


