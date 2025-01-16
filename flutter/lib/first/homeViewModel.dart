import 'dart:async';
import 'dart:convert';

import 'package:geolocator/geolocator.dart';
import 'package:plop/first/setting.dart';

import 'package:http/http.dart' as http;

class HomePageViewModel {
  StreamSubscription<Position>? streamPosition;
  bool hasGeoValidation = false;
  Position? lastPosition;

  Future<void> init() async {
    await Settings.load(); //TODO Move to a View for Init
    if (Settings.profile().hasGeoValidation) {
      hasGeoValidation = true;
      startGeolocation();
    }
  }

  void dispose() {
    stopGeolocation();
  }

  void startGeolocation() {
    if (streamPosition != null) {
      stopGeolocation();
    }
    const LocationSettings locationSettings =
        LocationSettings(accuracy: LocationAccuracy.bestForNavigation);
    streamPosition =
        Geolocator.getPositionStream(locationSettings: locationSettings)
            .listen((position) {
      print(position.latitude);
      print(position.longitude);
      this.lastPosition = position;
    });
  }

  void stopGeolocation() {
    if (streamPosition != null) {
      streamPosition!.cancel();
      streamPosition = null;
    }
  }

  Future<void> addPoint() async {
    /*if(lastPosition != null) {
      final response = await http.post(
        Uri.parse(Settings.urlServer() + '/boards/'),
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
        },
        body: jsonEncode({
          'label': 'test',
          'point': {
            'lat': lastPosition!.latitude,
            'lng': lastPosition!.longitude
          }
        })
      );
      if (response.statusCode == 200) {
        //return Album.fromJson(jsonDecode(response.body) as Map<String, dynamic>);
      } else {
        throw Exception('Failed to load album');
      }
    }*/

    final response =
        await http.post(Uri.parse(Settings.urlServer() + '/boards/'),
            headers: {
              'Content-Type': 'application/json',
              'Accept': 'application/json',
            },
            body: jsonEncode({'gameId': 'acdc'}));
    if (response.statusCode == 200) {
      Board board = Board.fromJson(jsonDecode(response.body) as Map<String, dynamic>);
      print("board:${board.id}");
    } else {
      throw Exception('Failed to load album');
    }
  }
}

class Board {

  String id;

  Board(this.id);

  static Board fromJson(Map<String, dynamic> json) {
    return Board(json['id']);
  }


}
