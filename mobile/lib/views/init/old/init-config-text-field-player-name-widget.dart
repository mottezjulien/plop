
import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';

import '../../generic/form/text-field-with-label.dart';


class InitConfigTextFieldPlayerNameWidget extends StatelessWidget {

  final Function(String) onChanged;

  const InitConfigTextFieldPlayerNameWidget({super.key, required this.onChanged});

  @override
  Widget build(BuildContext context) {
    return TextFieldGenericWidget(
        label: 'init.enter_player_name'.tr(),
        onChanged: onChanged
    );
  }

}
