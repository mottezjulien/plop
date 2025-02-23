
import 'dart:async';

import 'package:flutter/material.dart';
import 'package:geolocator/geolocator.dart';

import '../../contexts/game/game-repository.dart';

class GameView extends StatelessWidget {

  final GameRepository repository = GameRepository();
  StreamSubscription<Position>? streamPosition;
  //Position? lastPosition;
  ValueNotifier<Position?> positionValueNotifier = ValueNotifier(null);

  GameView({super.key});

  @override
  Widget build(BuildContext context) {

    if(streamPosition == null) {
      const LocationSettings locationSettings =
      LocationSettings(accuracy: LocationAccuracy.bestForNavigation);
      this.streamPosition = Geolocator.getPositionStream(locationSettings: locationSettings)
          .listen((position) {
        print(position.latitude);
        print(position.longitude);
        this.positionValueNotifier.value = position;
      });
    }

    return ValueListenableBuilder<Position?>(
        valueListenable: positionValueNotifier,
        builder: (context, Position? current, child) {
          if(current == null) {
            return Text("Pas de position");
          }
          return Text("Position: ${current.latitude}, ${current.longitude}");
        });
  }

}