package com.julien.plop.scenario.persistence.possibility.trigger;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = "ABSOLUTE_TIME")
public final class ScenarioPossibilityTriggerAbsoluteTimeEntity extends ScenarioPossibilityTriggerAbstractEntity {

    @Column(name = "absolute_time_in_minute")
    private long minutes;

    public long getMinutes() {
        return minutes;
    }

    public void setMinutes(long absoluteTimeInMinute) {
        this.minutes = absoluteTimeInMinute;
    }

}
