package com.julien.plop.scenario.persistence.possibility.condition;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("RELATIVE_TIME_AFTER_TRIGGER")
public final class ScenarioPossibilityConditionRelativeTimeAfterTriggerEntity
        extends ScenarioPossibilityConditionAbstractEntity {

    @Column(name = "relative_time_after_trigger_in_minutes")
    private int minutes;

    @Column(name = "relative_time_after_trigger_id")
    private String triggerId;

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }


    public String getTriggerId() {
        return triggerId;
    }

    public void setTriggerId(String triggerId) {
        this.triggerId = triggerId;
    }
}
