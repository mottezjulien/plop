import 'package:flutter/material.dart';

import '../app-colors.dart';

class StatusColors {
  static const Color success = Color(0xFF00C851);
  static const Color warning = Color(0xFFFFBB57);
  static const Color error = Color(0xFFD75073);
  static const Color info = Color(0xFFFEFEFE);
}

enum DialogType {
  ERROR, SUCCESS, INFO, WARNING
}

class TopInfoDialog extends StatefulWidget {

  final DialogType dialogType;
  final String message;

  const TopInfoDialog({
    super.key,
    this.dialogType = DialogType.INFO,
    required this.message
  });

  @override
  _TopInfoDialog createState() => _TopInfoDialog();
}

class _TopInfoDialog extends State<TopInfoDialog> with SingleTickerProviderStateMixin {
  late AnimationController _controller;
  late Animation<Offset> _offsetAnimation;

  @override
  void initState() {
    super.initState();
    _controller = AnimationController(
      duration: const Duration(milliseconds: 500),
      reverseDuration: const Duration(milliseconds: 500),
      vsync: this,
    );
    _offsetAnimation = Tween<Offset>(
      begin: const Offset(0, -2.5),
      end: Offset.zero,
    ).animate(CurvedAnimation(
      parent: _controller,
      curve: Curves.easeInOut,
    ));

    _controller.forward();
    Future.delayed(const Duration(milliseconds: 2500),
            () => _controller.reverse());
  }

  @override
  void dispose() {
    _controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Positioned(
      top: 70.0,
      left: MediaQuery.of(context).size.width * 0.025,
      width: MediaQuery.of(context).size.width * 0.95,
      child: SlideTransition(
        position: _offsetAnimation,
        child: Material(
          color: Colors.transparent,
          child: Container(
            padding: const EdgeInsets.all(16.0),
            decoration: BoxDecoration(
              color: AppColors.black,
              borderRadius: BorderRadius.circular(12.0),
              boxShadow: [BoxShadow(
                color: AppColors.blackFade26,
                blurRadius: 10.0,
                offset: const Offset(0, 10),
              )],
            ),
            child: Row(
              children: [
                _buildIcon(),
                const SizedBox(width: 12.0),
                Expanded(
                  child: Text(widget.message,
                    style:  Theme.of(context).textTheme.titleSmall!
                        .copyWith(color: AppColors.white),
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }

  Icon _buildIcon() {
    final IconData iconData = {
      DialogType.SUCCESS: Icons.check_circle_outline_rounded,
      DialogType.ERROR: Icons.error_outline_rounded,
      DialogType.WARNING: Icons.warning_amber_outlined,
      DialogType.INFO: Icons.info_outline_rounded,
    }[widget.dialogType]!;

    final Color iconColor = {
      DialogType.SUCCESS: StatusColors.success,
      DialogType.WARNING: StatusColors.warning,
      DialogType.ERROR: StatusColors.error,
      DialogType.INFO: StatusColors.info,
    }[widget.dialogType]!;

    return Icon(iconData, color: iconColor, size: 30.0);
  }
}

//TODO j'en fais quoi ?
class DialogService {

  /*
  static void showTopInfoDialog(BuildContext context, String message) {
    showTopDialog(context, message);
  }

  static void showTopSuccessDialog(BuildContext context, String message) {
    showTopDialog(context, message, dialogType: InfoDialogType.SUCCESS);
  }

  static void showTopWarningDialog(BuildContext context, String message) {
    showTopDialog(context, message, dialogType: InfoDialogType.WARNING);
  }*/

  static void showTopErrorDialog(BuildContext context, String message) {
    showTopDialog(context, message, dialogType: DialogType.ERROR);
  }

  static void showTopDialog(
    BuildContext context,
    String message, {
    DialogType dialogType = DialogType.INFO,
  }) {
    _showOverlay(
      context,
      TopInfoDialog(dialogType: dialogType, message: message),
    );
  }

  static void _showOverlay(BuildContext context, Widget dialog) {
    final OverlayState overlay = Overlay.of(context);

    final OverlayEntry overlayEntry = OverlayEntry(builder: (BuildContext context) => dialog);
    overlay.insert(overlayEntry);

    Future.delayed(const Duration(seconds: 3), () {
      if (overlayEntry.mounted) {
        overlayEntry.remove();
      }
    });
  }
}
