

class Auth {

  final String token;

  Auth({required this.token});



  //To Local Store

  Auth.fromJson(Map<String, dynamic> json) : token = json['token'];

  Map<String, dynamic> toJson() {
    return { 'token': token};
  }

}