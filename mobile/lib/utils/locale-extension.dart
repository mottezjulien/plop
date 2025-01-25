import 'dart:ui';

extension LocaleExtension on Locale {
  toLanguageLabel() {
    switch(languageCode) {
      case 'fr':
        return 'Français';
      case 'en':
        return 'English';
      default:
        return 'Unknown';
    }
  }
}