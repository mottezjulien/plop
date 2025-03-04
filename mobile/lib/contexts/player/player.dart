
import 'package:plop/generic/config/language.dart';

class Player {

  final String _id;
  final String _name;
  final Language _language;

  Player({
    required String id,
    required String name,
    required Language language
  }) : _id = id, _name = name, _language = language;

  String get id => _id;

  String get name => _name;

  Language get language => _language;

}