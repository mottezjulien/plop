package com.julien.plop.scenario.persistence.possibility.condition;


import com.julien.plop.generic.BeforeOrAfter;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

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

}
