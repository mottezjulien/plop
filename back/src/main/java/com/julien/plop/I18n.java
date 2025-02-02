package com.julien.plop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public record I18n(String description, Map<Language, String> values) {

    public String json() {
        try {
            return new ObjectMapper().writeValueAsString(values);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }

}
