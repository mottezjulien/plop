
import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';
import 'package:plop/utils/locale-extension.dart';

class InitConfigSelectLanguageWidget extends StatelessWidget {

  const InitConfigSelectLanguageWidget({super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
        children: <Widget>[
            Text('first.select_language'.tr(),
                style: Theme.of(context).textTheme.headlineMedium),
            const SizedBox(height: 16),
            _SelectLanguageDropdownMenu(init: context.locale),
        ]
    );
  }
}


class _SelectLanguageDropdownMenu extends StatelessWidget {

  late final ValueNotifier<Locale> valueNotifier;

  _SelectLanguageDropdownMenu({required Locale init}){
    valueNotifier = ValueNotifier(init);
  }

  @override
  Widget build(BuildContext context) {
    return ValueListenableBuilder<Locale>(
      valueListenable: valueNotifier,
      builder: (BuildContext context, Locale? value, Widget? child) {
        return DropdownMenu<Locale>(
            initialSelection: value,
            onSelected: (Locale? value) {
              if (value == null) return;
              context.setLocale(value);
              valueNotifier.value = value;
            },
            dropdownMenuEntries: _menuEntries(context.supportedLocales));
      },
    );
  }

  List<DropdownMenuEntry<Locale>> _menuEntries(List<Locale> locales) {
    var menuEntries = locales.map<DropdownMenuEntry<Locale>>((Locale locale) {
      return DropdownMenuEntry<Locale>(value: locale, label: locale.toLanguageLabel());
    }).toList();
    menuEntries.sort((a, b) => a.label.compareTo(b.label));
    return menuEntries;
  }

}

