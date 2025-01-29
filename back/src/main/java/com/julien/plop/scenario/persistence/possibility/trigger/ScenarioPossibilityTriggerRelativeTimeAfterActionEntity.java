package com.julien.plop.scenario.persistence.possibility.trigger;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "RELATIVE_TIME_AFTER_ACTION")
public final class ScenarioPossibilityTriggerRelativeTimeAfterActionEntity extends ScenarioPossibilityTriggerAbstractEntity {

    @Column(name = "relative_time_after_action_in_minute")
    private int minutes;

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int absoluteTimeInMinute) {
        this.minutes = absoluteTimeInMinute;
    }

}
