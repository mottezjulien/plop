package com.julien.plop.i18n.domain;

import com.julien.plop.tools.StringTools;

import java.util.Map;

public record I18n(String description, Map<Language, String> values) {

    public String jsonValues() {
        return StringTools.toJson(values);
    }

}
