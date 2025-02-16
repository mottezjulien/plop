
import 'dart:async';

import 'package:flutter/material.dart';
import 'package:geolocator/geolocator.dart';

class GameView extends StatelessWidget {

  GameRepository repository = GameRepository() //Name ??

  const GameView({super.key});

  @override
  Widget build(BuildContext context) {
    StreamSubscription<Position> streamPosition = Geolocator.getPositionStream(locationSettings: locationSettings)
            .listen((position) {
          //print(position.latitude);
          //print(position.longitude);
          this.lastPosition = position;
        });


  }


}