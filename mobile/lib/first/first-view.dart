

import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';

class FirstView extends StatefulWidget {

  @override
  State<FirstView> createState() => _FirstViewState();

}

class _FirstViewState extends State<FirstView> {



  @override
  Widget build(BuildContext context) {

    /**/
    List<Locale> locales = context.supportedLocales;
    Locale locale = context.locale;

    print("locales:$locales");
    print("locale:$locale");

  final ColorScheme colorScheme = Theme.of(context).colorScheme;
    return Scaffold(
        appBar: AppBar(
            backgroundColor: colorScheme.inversePrimary,
            title: const Text('Plop')
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Text("plop".tr(), style: Theme.of(context).textTheme.headlineMedium),
              DropdownMenu<Locale>(
                initialSelection: context.locale,
                onSelected: (Locale? value) {
                  /*if(value == null) return;
                  index = languages.indexOf(value);
                  context.setLocale(locales[index]);
                  setState(() {
                  });*/
                },
                dropdownMenuEntries: locales.map<DropdownMenuEntry<Locale>>((Locale locale) {
                  return DropdownMenuEntry<Locale>(value: locale, label: locale.toLanguageTag()); //TODO
                }).toList()
              )
            ],
          ),
        )
    );

  }

}