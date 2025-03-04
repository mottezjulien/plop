
import 'dart:async';

import 'package:flutter/material.dart';
import 'package:geolocator/geolocator.dart';

import '../data/game-repository.dart';
import '../domain/game.dart';

class GameView extends StatelessWidget {

  final GameRepository repository = GameRepository();
  StreamSubscription<Position>? streamPosition;
  Game? game;
  final ValueNotifier<Position?> positionValueNotifier = ValueNotifier(null);

  GameView({super.key});

  @override
  Widget build(BuildContext context) {

    if (streamPosition == null) {
      initStreamPosition(context);
    }

    return Scaffold(
      appBar: AppBar(
          backgroundColor: Theme
              .of(context)
              .colorScheme
              .inversePrimary,
          title: Text("Game Plop")
      ),
      body: Center(
        child:
        Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              ValueListenableBuilder<Position?>(
                valueListenable: positionValueNotifier,
                builder: (context, Position? current, child) {
                  if(game == null) {
                    return Text("Pas de game",
                        style: Theme
                            .of(context)
                            .textTheme
                            .headlineMedium);
                  }

                  if (current == null) {
                    return Text("Pas de position",
                        style: Theme
                            .of(context)
                            .textTheme
                            .headlineMedium);
                  }
                  return Text(
                      "Position: ${current.latitude}, ${current.longitude}",
                      style: Theme
                          .of(context)
                          .textTheme
                          .headlineMedium);
                },
              )
            ]
        ),
        //
      ),
      /*floatingActionButton: InitFloatingActionButton(
          onPressed: () => _viewModel.next(context),
        )*/
    );
  }



  void initStreamPosition(BuildContext context) {
    Geolocator.checkPermission()
        .then((permissionStatus) async {
      if(permissionStatus != LocationPermission.whileInUse
          || permissionStatus != LocationPermission.always) {
        await Geolocator.requestPermission();
      }
      const LocationSettings locationSettings = LocationSettings(accuracy: LocationAccuracy.bestForNavigation);
      this.streamPosition = Geolocator.getPositionStream(locationSettings: locationSettings)
          .listen((position) {
        print(position.latitude);
        print(position.longitude);
        positionValueNotifier.value = position;

        /*if(game.isPlaying()) {
          repository.move(Coord(lat: position.latitude, lng: position.longitude));
        }*/
      });
    });
  }

}