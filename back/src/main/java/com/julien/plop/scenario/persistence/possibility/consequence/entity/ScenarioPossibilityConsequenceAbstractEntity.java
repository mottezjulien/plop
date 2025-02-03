package com.julien.plop.scenario.persistence.possibility.consequence.entity;

import com.julien.plop.scenario.PossibilityConsequence;
import jakarta.persistence.*;

@Entity
@Table(name = "TEST1_SCENARIO_POSSIBILITY_CONSEQUENCE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public abstract class ScenarioPossibilityConsequenceAbstractEntity {

    @Id
    //@UuidGenerator
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PossibilityConsequence abstractToModel() {
        return switch (this) {
            case ScenarioPossibilityConsequenceAddObjectEntity addObject -> addObject.toModel();
            case ScenarioPossibilityConsequenceRemoveObjectEntity removeObject -> removeObject.toModel();
            case ScenarioPossibilityConsequenceUpdatedMetadataEntity metadataUpdate -> metadataUpdate.toModel();
            case ScenarioPossibilityConsequenceGameOverEntity gameOver -> gameOver.toModel();
            case ScenarioPossibilityConsequenceStartedStepEntity startedStep -> startedStep.toModel();
            case ScenarioPossibilityConsequenceEndedStepEntity endedStep -> endedStep.toModel();
            default -> throw new IllegalStateException("Unknown type");
        };
    }


    public static ScenarioPossibilityConsequenceAbstractEntity fromModel(PossibilityConsequence consequence) {
        return switch (consequence) {
            case PossibilityConsequence.AddObjet addObjet -> {
                ScenarioPossibilityConsequenceAddObjectEntity entity = new ScenarioPossibilityConsequenceAddObjectEntity();
                entity.setId(addObjet.id().value());
                entity.setObjetId(addObjet.objetId());
                yield entity;
            }
            case PossibilityConsequence.RemoveObjet removeObjet -> {
                ScenarioPossibilityConsequenceRemoveObjectEntity entity = new ScenarioPossibilityConsequenceRemoveObjectEntity();
                entity.setId(removeObjet.id().value());
                entity.setObjetId(removeObjet.objetId());
                yield entity;
            }
            case PossibilityConsequence.StartedStep startedStep -> {
                ScenarioPossibilityConsequenceStartedStepEntity entity = new ScenarioPossibilityConsequenceStartedStepEntity();
                entity.setId(startedStep.id().value());
                entity.setStepId(startedStep.stepId().value());
                yield entity;
            }
            case PossibilityConsequence.EndedStep endedStep -> {
                ScenarioPossibilityConsequenceEndedStepEntity entity = new ScenarioPossibilityConsequenceEndedStepEntity();
                entity.setId(endedStep.id().value());
                entity.setStepId(endedStep.stepId().value());
                yield entity;
            }
            case PossibilityConsequence.GameOver ignored -> new ScenarioPossibilityConsequenceGameOverEntity();
            case PossibilityConsequence.UpdatedMetadata updatedMetadata -> {
                ScenarioPossibilityConsequenceUpdatedMetadataEntity entity = new ScenarioPossibilityConsequenceUpdatedMetadataEntity();
                entity.setId(updatedMetadata.id().value());
                entity.setMetadataId(updatedMetadata.metadataId());
                entity.setValue(updatedMetadata.value());
                yield entity;
            }
        };
    }

}
