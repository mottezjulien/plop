
import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';
import 'package:plop/utils/LocaleExtention.dart';

class FirstViewSelectLanguageWidget extends StatelessWidget {

  final Function onUpdate;

  const FirstViewSelectLanguageWidget({super.key, required this.onUpdate});

  @override
  Widget build(BuildContext context) {
    return Column(
        children: <Widget>[
            Text('first.select_language'.tr(),
                style: Theme.of(context).textTheme.headlineMedium),
            _SelectLanguageDropdownMenu(
              onUpdate: onUpdate,
            ),
        ]
    );
  }
}


class _SelectLanguageDropdownMenu extends StatelessWidget {

  final Function onUpdate;

  const _SelectLanguageDropdownMenu({required this.onUpdate});

  @override
  Widget build(BuildContext context) {
    return DropdownMenu<Locale>(
        initialSelection: context.locale,
        onSelected: (Locale? value) {
          if (value == null) return;
          context.setLocale(value);
          onUpdate();
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

