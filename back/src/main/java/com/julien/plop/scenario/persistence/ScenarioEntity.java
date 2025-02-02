package com.julien.plop.scenario.persistence;

import com.julien.plop.scenario.Scenario;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TEST1_SCENARIO")
public class ScenarioEntity {

    @Id
    //@UuidGenerator
    private String id;

    private String label;

    @OneToMany(mappedBy = "scenario")
    private Set<ScenarioStepEntity> steps = new HashSet<>();

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

    public Set<ScenarioStepEntity> getSteps() {
        return steps;
    }

    public void setSteps(Set<ScenarioStepEntity> steps) {
        this.steps = steps;
    }

    public Scenario toModel() {
        return new Scenario(new Scenario.Id(id), label,
                steps.stream().map(ScenarioStepEntity::toModel).toList());
    }
}
