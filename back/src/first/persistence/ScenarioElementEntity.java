package com.julien.plop.scenario.persistence;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Table(name = "TEST1_SCENARIO_ELEMENT")
public class ScenarioElementEntity {

    @Id
    //@UuidGenerator
    private String id;

    private String label;

    @ManyToOne
    @JoinColumn(name = "scenario_id")
    private ScenarioEntity scenario;


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
}
