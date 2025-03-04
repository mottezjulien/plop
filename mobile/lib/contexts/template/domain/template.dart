
class Template {

  final String _id;
  final String _code;
  final String _label;

  Template({
    required String id,
    required String code,
    required String label
  }): _id = id, _code = code, _label = label;

  String get id => _id;

  String get code => _code;

  String get label => _label;

}