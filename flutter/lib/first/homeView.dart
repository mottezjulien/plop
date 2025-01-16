
import 'package:flutter/material.dart';

import './homeViewModel.dart';

class HomeView extends StatefulWidget {

  const HomeView({super.key});

  @override
  State<HomeView> createState() => _HomeViewState();

}

class _HomeViewState extends State<HomeView> {

  final HomePageViewModel _viewModel = HomePageViewModel();

  @override
  void initState() {
    super.initState();
    _viewModel.init().then((_) {
      setState(() {

      });
    });
  }

  @override
  void dispose() {
    super.dispose();
    _viewModel.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final ColorScheme colorScheme = Theme.of(context).colorScheme;
    return Scaffold(
        appBar: AppBar(
            backgroundColor: colorScheme.inversePrimary,
            title: const Text('Flutter Demo Home Page')
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              const Text('Hello:'),
              Text('Bonjour', style: Theme.of(context).textTheme.headlineMedium),
              Row(
                children: [
                  FloatingActionButton(
                    backgroundColor: colorScheme.inversePrimary,
                    tooltip: "Ajout d'un point",
                    onPressed: () {
                      _viewModel.addPoint();
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
        backgroundColor: _viewModel.hasGeoValidation
            ? colorScheme.inversePrimary
            : colorScheme.secondary,
        onPressed: () {

        },
        child: _viewModel.hasGeoValidation
            ? const Icon(Icons.person)
            : const Icon(Icons.person_off),
      ),
    );
  }
}