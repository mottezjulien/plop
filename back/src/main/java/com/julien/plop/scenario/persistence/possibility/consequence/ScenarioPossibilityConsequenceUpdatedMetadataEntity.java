package com.julien.plop.scenario.persistence.possibility.consequence;

import jakarta.persistence.Column;

public final class ScenarioPossibilityConsequenceUpdatedMetadataEntity
    extends ScenarioPossibilityConsequenceAbstractEntity {

    @Column(name = "updated_metadata_id")
    private String metadataId;

    @Column(name = "updated_metadata_value")
    private float value;

    public String getMetadataId() {
        return metadataId;
    }

    public void setMetadataId(String metadataId) {
        this.metadataId = metadataId;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
