package com.julien.plop.i18n.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.julien.plop.i18n.domain.I18n;
import com.julien.plop.i18n.domain.Language;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Table(name = "TEST1_I18N_SHORT_KEY")
public class I18nEntity {

    @Id
    //@UuidGenerator
    private String id;

    private String description;

    @Column(name = "json_values", columnDefinition = "TEXT")
    private String jsonValues;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJsonValues() {
        return jsonValues;
    }

    public void setJsonValues(String jsonValues) {
        this.jsonValues = jsonValues;
    }

    public I18n toModel() {
        try {
            Map<String, String> result = new ObjectMapper().readValue(jsonValues, Map.class);
            return new I18n(description, result.entrySet().stream()
                    .map(entry -> new AbstractMap.SimpleEntry<>(Language.valueOf(entry.getKey().toUpperCase()), entry.getValue())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        } catch (JsonProcessingException e) {
            return new I18n(description, new HashMap<>());
        }
    }
}
