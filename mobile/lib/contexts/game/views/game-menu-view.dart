
import 'package:flutter/material.dart';

class GameMenuView extends StatelessWidget {

  //final GameRepository repository = GameRepository();

  const GameMenuView({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
          backgroundColor: Theme.of(context).colorScheme.inversePrimary,
          title: Text("Game Menu :)")
      ),
      body: Center(
        child:Column(
          children: [
            TextButton(
            /*style: TextButton.styleFrom(
            shape: RoundedRectangleBorder(
              borderRadius: const BorderRadius.all(Radius.circular(8)),
              side: BorderSide(color: colorScheme.primary, width: 5)),
            ),*/
            onPressed: () {},
            child: const Text('Démarrer :)'))
          ]
        )
      )
    );
  }

}