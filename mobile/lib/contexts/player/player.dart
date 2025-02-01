
import 'dart:ui';


class Player {

  final String _id;
  final String _name;
  final Locale _locale;

  Player({
    required String id,
    required String name,
    required Locale locale
  }) : _id = id, _name = name, _locale = locale;

  String get id => _id;

  Locale get locale => _locale;

}