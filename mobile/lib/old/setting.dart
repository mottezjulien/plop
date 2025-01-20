
import 'package:geolocator/geolocator.dart';

class OldSettings {

  static String urlServer() {
    return 'https://8036-2a01-e0a-db5-bd20-b99b-7f65-81a0-f029.ngrok-free.app';
  }

  static OldSettings _self = OldSettings();

  static void clear() {
    _self = OldSettings();
  }

  static Future<void> load() async {
    Profile profile = await _loadProfile();
    _self._profile = profile;
  }

  static Future<Profile> _loadProfile() async {
    Profile _profile =  new Profile();
    LocationPermission permission = await Geolocator.checkPermission();
    if(permission.isValid()) {
      _profile.hasGeoValidation = true;
    } else if (permission == LocationPermission.denied) {
      permission = await Geolocator.requestPermission();
      if (permission.isValid()) {
        _profile.hasGeoValidation = true;
      }
    }
    return _profile;
  }

  Profile _profile = Profile();

  static Profile profile() {
    return _self._profile;
  }



}




class Profile {

  String name = '';

  bool hasGeoValidation = false;
  bool hasNotifValidation = false;

}

extension on LocationPermission {
  bool isValid() {
    return this == LocationPermission.whileInUse
        || this == LocationPermission.always;
  }
}
