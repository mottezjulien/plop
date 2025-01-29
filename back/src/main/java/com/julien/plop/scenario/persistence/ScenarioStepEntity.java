package com.julien.plop.scenario.persistence;


import com.julien.plop.scenario.persistence.possibility.ScenarioPossibilityEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TEST1_SCENARIO_STEP")
public class ScenarioStepEntity {

    @Id
    @UuidGenerator
    private String id;

    private String label;

    @ManyToOne
    @JoinColumn(name = "scenario_id")
    private ScenarioEntity scenario;

    @OneToMany(mappedBy = "step")
    private Set<ScenarioTargetEntity> targets = new HashSet<>();

    @OneToMany(mappedBy = "step")
    private Set<ScenarioPossibilityEntity> possibilities = new HashSet<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ScenarioEntity getScenario() {
        return scenario;
    }

    public void setScenario(ScenarioEntity scenario) {
        this.scenario = scenario;
    }

    public Set<ScenarioTargetEntity> getTargets() {
        return targets;
    }

    public void setTargets(Set<ScenarioTargetEntity> targets) {
        this.targets = targets;
    }

    public Set<ScenarioPossibilityEntity> getPossibilities() {
        return possibilities;
    }

    public void setPossibilities(Set<ScenarioPossibilityEntity> possibilities) {
        this.possibilities = possibilities;
    }

}
