package com.julien.plop.i18n.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.julien.plop.tools.StringTools;

import java.util.Map;

public record I18n(String description, Map<Language, String> values) {

    public String json() {
        return StringTools.toJson(this);
    }

}
