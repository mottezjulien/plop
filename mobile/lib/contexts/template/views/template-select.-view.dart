
import 'package:easy_localization/easy_localization.dart';

import 'package:flutter/material.dart';

import '../../../generic/redirect/dialog_services.dart';
import '../../../generic/redirect/generic/form/text-field-with-label.dart';

import '../../../generic/redirect/settings.dart';
import '../../../old/init-rename/views/init-floating-action-button.dart';
import '../data/template-repository.dart';
import '../domain/template.dart';
import 'language-select-widget.dart';

class SelectTemplateView extends StatefulWidget {

  const SelectTemplateView({super.key});

  @override
  State<SelectTemplateView> createState() => _SelectTemplateViewState();

}

class _SelectTemplateViewState extends State<SelectTemplateView> {

  final _ViewModel _viewModel = _ViewModel();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          backgroundColor: Theme.of(context).colorScheme.inversePrimary,
          title: Text('template.select.title'.tr())
        ),
        body: Center(
          child:
          Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                const LanguageSelectWidget(),
                if(_viewModel.isSelectedLanguage())
                  Column(
                    children: [
                      const SizedBox(height: 8),
                      TextFieldGenericWidget(
                          label: 'template.select.enter_player_name'.tr(),
                          onChanged: (name) => _viewModel.setPlayerName(name)
                      ),
                      const SizedBox(height: 8),
                      TextFieldGenericWidget(
                          label: 'template.select.enter_game_code'.tr(),
                          onChanged: (value) => _viewModel.setTemplateCode(value)
                      )
                    ],
                  )
              ]
          ),
        ),
        floatingActionButton: InitFloatingActionButton(
          onPressed: () async {
            await _viewModel.submit(context);
            setState(() { });
          },
        )
    );
  }
}

class _ViewModel {

  TemplateRepository templateRepository = TemplateRepository();

  bool _selectedLanguage = false;
  String _playerName = "";
  String _templateCode = "";

  submit(BuildContext context) {
    if(!_selectedLanguage) {
      _selectedLanguage = true;
    } else {
      if(_playerName.isEmpty) {
        DialogService.showTopErrorDialog(context, "pouettt :)");
      } else if(_templateCode.isEmpty) {
        DialogService.showTopErrorDialog(context, "pouettt 2 :)");
      } else {
        Template? template = templateRepository.findByCode(code: _templateCode);
        if(template == null) {
          DialogService.showTopErrorDialog(context, "pouettt 3 :)");
        } else {
          //Player
          Settings.setTemplate(template);
          Settings.setTemplate(template);
        }
      }
    }
  }

  bool isSelectedLanguage() {
    return _selectedLanguage;
  }

  void setPlayerName(String playerName) {
    _playerName = playerName;
  }

  void setTemplateCode(String templateCode) {
    _templateCode = templateCode;
  }

}