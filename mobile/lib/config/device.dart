
import 'dart:io';

import 'package:device_info_plus/device_info_plus.dart';

enum DevicePlatform {

  android, ios;

  static DevicePlatform found() {
    if (Platform.isAndroid) {
      return DevicePlatform.android;
    }
    if (Platform.isIOS) {
      return DevicePlatform.ios;
    }
    throw Exception();
  }
}

class Device {
  static Future<String> id() async {
    var deviceInfo = DeviceInfoPlugin();
    switch(DevicePlatform.found()) {
      case DevicePlatform.android:
        var androidInfo = await deviceInfo.androidInfo;
        return androidInfo.fingerprint;
      case DevicePlatform.ios:
        var iosInfo = await deviceInfo.iosInfo;
        return iosInfo.identifierForVendor!;
    }
  }
}


