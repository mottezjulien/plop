
import 'package:flutter/material.dart';

import 'icon.dart';

Future<String?> showDialogOnlyDone({
  required BuildContext context,
  required String title,
  required String content
}) {
  return showDialog<String>(
    context: context,
    builder: (BuildContext context) => AlertDialog(
      title: Text(title),
      content: Text(content),
      actions: <Widget>[
        TextButton(
          onPressed: () => Navigator.pop(context, 'OK'),
          child: IconUtils.submit,
        ),
      ],
    ),
  );
}