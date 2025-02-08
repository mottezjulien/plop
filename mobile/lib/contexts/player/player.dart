
class Player {

  final String _id;
  final String _name;
  final String _language;

  Player({
    required String id,
    required String name,
    required String language
  }) : _id = id, _name = name, _language = language;

  String get id => _id;

  String get name => _name;

  String get language => _language;

}