
import 'package:easy_localization/easy_localization.dart';
import 'package:flutter/material.dart';

class InitAppBar extends AppBar {

  InitAppBar(BuildContext context): super(
      backgroundColor: Theme.of(context).colorScheme.inversePrimary,
      title: Text('init.title'.tr())
  );

}