
import 'dart:ui';

enum Language {
  en,
  fr;

 static Language byDefault() {
    return Language.fr;
  }

  Locale toLocale() {
    switch(this) {
      case Language.en:
        return const Locale('en');
      case Language.fr:
        return const Locale('fr');
    }
  }

}