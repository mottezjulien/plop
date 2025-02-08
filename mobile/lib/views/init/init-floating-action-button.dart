

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class InitFloatingActionButton extends StatefulWidget {

  final Function() onPressed;

  bool _loading = false;

  InitFloatingActionButton({
    super.key,
    required this.onPressed});

  @override
  State<StatefulWidget> createState() => _InitFloatingActionButtonState();

}

class _InitFloatingActionButtonState extends State<InitFloatingActionButton> {

  @override
  Widget build(BuildContext context) {
    to valuNotifer

    final ColorScheme colorScheme = Theme.of(context).colorScheme;
    return FloatingActionButton(
      backgroundColor: colorScheme.inversePrimary,
      onPressed: () async {
        if(!widget._loading) {
          setState(() => widget._loading = true);
          try {
            await widget.onPressed();
            setState(() => widget._loading = false);
          } catch (e) {
            setState(() => widget._loading = false);
          }
        }
        //await _viewModel.next(context);
      },
      child:
      widget._loading ?
        const CircularProgressIndicator() :
        const Icon(Icons.done),
    );
  }

}