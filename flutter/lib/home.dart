

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:plop/profile.dart';

class HomePage extends StatefulWidget {

  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();

}

class _HomePageState extends State<HomePage> {

  @override
  void initState() {
    super.initState();
    Settings.load().then((_) {
      setState(() {

      });
    });
  }

  @override
  Widget build(BuildContext context) {
    final ColorScheme colorScheme = Theme.of(context).colorScheme;
    return Scaffold(
        appBar: AppBar(
            backgroundColor: Theme.of(context).colorScheme.inversePrimary,
            title: Text('Flutter Demo Home Page')
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              const Text('Hello:'),
              Text('Bonjour', style: Theme.of(context).textTheme.headlineMedium),
              Row(
                children: [
                  FloatingActionButton.large(
                    backgroundColor: colorScheme.primary,
                    tooltip: "Ajout d'un point",
                    onPressed: () {

                    },
                    child: const Icon(Icons.add),
                  ),
                  FloatingActionButton.large(
                    backgroundColor: colorScheme.secondary,
                    tooltip: "Validation d'un point",
                    onPressed: () {

                    },
                    child: const Icon(Icons.check),
                  )
                ],
              )
            ],
          ),
        ),
      floatingActionButton: FloatingActionButton.large(
        backgroundColor: colorScheme.tertiary,
        onPressed: () {

        },
        child: const Icon(Icons.person_off),
      ),
    );
  }
}