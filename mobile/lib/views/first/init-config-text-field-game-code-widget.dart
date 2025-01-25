
import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';


class InitConfigTextFieldGameCodeWidget extends StatelessWidget {

  final Function(String) onChanged;

  const InitConfigTextFieldGameCodeWidget({super.key, required this.onChanged});

  @override
  Widget build(BuildContext context) {
    return Column(
        children: <Widget>[
          Text('first.tap_game'.tr(),
              style: Theme.of(context).textTheme.headlineMedium),
          const SizedBox(height: 16),
          TextField(
              decoration: const InputDecoration(
                hintText: 'Enter a search term',
              ),
              onChanged: (value) {
                onChanged(value);
              }
          )
        ],
      );
  }

}
