
import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';

import '../generic/form/text-field-with-label.dart';


class InitConfigTextFieldGameCodeWidget extends StatelessWidget {

  final Function(String) onChanged;

  const InitConfigTextFieldGameCodeWidget({super.key, required this.onChanged});

  @override
  Widget build(BuildContext context) {
    return TextFieldGenericWidget(
        label: 'init.enter_game_code'.tr(),
        onChanged: onChanged
    );
  }

}
