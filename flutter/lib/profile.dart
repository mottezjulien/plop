

import 'package:geolocator/geolocator.dart';

class Settings {

  static Settings _self = Settings();

  static void clear() {
    _self = Settings();
  }

  static Future<void> load() async {
    Profile profile = await _loadProfile();
    _self._profile = profile;
  }

  static Future<Profile> _loadProfile() async {
    Profile _profile =  new Profile();
    bool serviceEnabled = await Geolocator.isLocationServiceEnabled();
    if (!serviceEnabled) {
      LocationPermission permission = await Geolocator.checkPermission();
      if(permission.isValid()) {
        _profile.hasGeoValidation = true;
      } else if (permission == LocationPermission.denied) {
          permission = await Geolocator.requestPermission();
          if (permission.isValid()) {
            _profile.hasGeoValidation = true;
          }
      }
    }
    return _profile;
  }

  Profile _profile = new Profile();

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
