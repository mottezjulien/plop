

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class InitFloatingActionButton extends StatelessWidget {

  final Function() onPressed;
  final ValueNotifier<bool> isLoading = ValueNotifier(false);

  InitFloatingActionButton({
    super.key,
    required this.onPressed});

  @override
  Widget build(BuildContext context) {

    return ValueListenableBuilder<bool>(
        valueListenable: isLoading,
        builder: (context, isLoading, child) {
          return _toDisplay(context, isLoading, child);
        });
  }

  Widget _toDisplay(BuildContext context, bool isLoadingValue, Widget? child) {
    final ColorScheme colorScheme = Theme.of(context).colorScheme;
    return FloatingActionButton(
      backgroundColor: colorScheme.inversePrimary,
      onPressed: () async {
        if(!isLoadingValue) {
          isLoading.value = true;
          try {
            await onPressed();
            isLoading.value = false;
          } catch (e) {
            isLoading.value = false;
          }
        }
      },
      child: isLoadingValue ? const CircularProgressIndicator() :
      const Icon(Icons.done),
    );
  }


}