

import 'package:flutter/material.dart';

class TextFieldGenericWidget extends StatelessWidget {

  final String label;

  final Function(String)? onChanged;

  const TextFieldGenericWidget({super.key,
    required this.label, this.onChanged});

  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        Text(label,
            style: Theme.of(context).textTheme.headlineMedium),
        const SizedBox(height: 16),
        TextField(
            decoration: InputDecoration(hintText: label),
            onChanged: (value) => onChanged != null
                ? onChanged!(value)
                : null
        )
      ],
    );
  }

}