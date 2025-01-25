
import 'package:flutter/material.dart';

import '../generic/form/text-field-with-label.dart';


class InitConfigTextFieldPlayerNameWidget extends StatelessWidget {

  final Function(String) onChanged;

  const InitConfigTextFieldPlayerNameWidget({super.key, required this.onChanged});

  @override
  Widget build(BuildContext context) {
    return TextFieldGenericWidget(
        label: 'plop player name',
        onChanged: onChanged
    );
  }

}
