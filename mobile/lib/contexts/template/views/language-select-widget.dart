
import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';
import 'package:plop/generic/redirect/locale-extension.dart';

class LanguageSelectWidget extends StatelessWidget {

  const LanguageSelectWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
        children: <Widget>[
            Text('language.select'.tr(),
                style: Theme.of(context).textTheme.headlineMedium),
            const SizedBox(height: 16),
            _SelectLanguageDropdownMenu(),
        ]
    );
  }
}


class _SelectLanguageDropdownMenu extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return DropdownMenu<Locale>(
        initialSelection: context.locale,
        onSelected: (Locale? value) {
          if (value == null) return;
          context.setLocale(value);
        },
        dropdownMenuEntries: _menuEntries(context.supportedLocales));
  }

  List<DropdownMenuEntry<Locale>> _menuEntries(List<Locale> locales) {
    var menuEntries = locales.map<DropdownMenuEntry<Locale>>((Locale locale) {
      return DropdownMenuEntry<Locale>(value: locale, label: locale.toLanguageLabel());
    }).toList();
    menuEntries.sort((a, b) => a.label.compareTo(b.label));
    return menuEntries;
  }

}

