
import 'dart:convert';

import 'package:plop/contexts/template/domain/template.dart';

import 'package:http/http.dart' as http;

import '../../../generic/redirect/http/headers.dart';
import '../../../generic/redirect/repository-exception.dart';
import '../../../generic/redirect/settings.dart';

class TemplateRepository {

  static const String path = '/templates';

  Future<Template?> findByCode({required String code}) async {
    String url = "${Settings.urlServer()}$path?code=$code";
    Uri uri = Uri.parse(url);
    final http.Response response = await http.get(uri,headers: Headers.withAuth());
    if(response.statusCode >= 404) {
      return null;
    }
    if(response.statusCode >= 400) {
      throw RepositoryException(response.statusCode, response.body);
    }
    return toModel(jsonDecode(response.body));
  }

  Template toModel(Map<String, dynamic> json) {
    return Template(
        id: json['id'],
        code: json['code'],
        label: json['label']
    );
  }

}