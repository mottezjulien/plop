package com.julien.plop.scenario.persistence.possibility.condition.entity;


import com.julien.plop.generic.BeforeOrAfter;
import com.julien.plop.scenario.PossibilityCondition;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.Duration;

@Entity
@DiscriminatorValue("ABSOLUTE_TIME")
public final class ScenarioPossibilityConditionAbsoluteTimeEntity
        extends ScenarioPossibilityConditionAbstractEntity {

    @Column(name = "absolute_time_in_minutes")
    private int minutes;

    @Enumerated(EnumType.STRING)
    @Column(name = "absolute_time_type")
    private BeforeOrAfter type;

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public BeforeOrAfter getType() {
        return type;
    }

    public void setType(BeforeOrAfter type) {
        this.type = type;
    }

    public PossibilityCondition toModel() {
        return new PossibilityCondition.AbsoluteTime(new PossibilityCondition.Id(id), Duration.ofMinutes(minutes), type);
    }
}
