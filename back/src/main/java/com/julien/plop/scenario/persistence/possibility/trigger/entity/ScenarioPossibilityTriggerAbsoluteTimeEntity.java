package com.julien.plop.scenario.persistence.possibility.trigger.entity;

import com.julien.plop.scenario.PossibilityTrigger;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.Duration;

@Entity
@DiscriminatorValue(value = "ABSOLUTE_TIME")
public final class ScenarioPossibilityTriggerAbsoluteTimeEntity extends ScenarioPossibilityTriggerAbstractEntity {

    @Column(name = "absolute_time_in_minutes")
    private long minutes;

    public long getMinutes() {
        return minutes;
    }

    public void setMinutes(long absoluteTimeInMinute) {
        this.minutes = absoluteTimeInMinute;
    }

    public PossibilityTrigger toModel() {
        return new PossibilityTrigger.AbsoluteTime(new PossibilityTrigger.Id(id), Duration.ofMinutes(minutes));
    }

}
