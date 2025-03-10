package com.julien.plop.template.presenter;

import com.julien.plop.template.persistence.TemplateEntity;

public record TemplateResponseDTO(String id, String code, String label) {

    public static TemplateResponseDTO fromEntity(TemplateEntity entity) {
        return new TemplateResponseDTO(entity.getId(), entity.getCode(), entity.getLabel());
    }

}
