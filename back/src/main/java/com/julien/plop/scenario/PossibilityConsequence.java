package com.julien.plop.scenario;


public sealed interface PossibilityConsequence permits
        PossibilityConsequence.StepDone,
        PossibilityConsequence.GameOver,
        PossibilityConsequence.FireEvent, // TODO ???
        PossibilityConsequence.MetadataUpdate,
        PossibilityConsequence.AddObjet,
        PossibilityConsequence.RemoveObjet {

        record StepDone(Scenario.Step.Id stepId) implements PossibilityConsequence {

        }

        record GameOver() implements PossibilityConsequence {

        }

        record FireEvent() implements PossibilityConsequence {

        }

        record MetadataUpdate(String metadataId, float value) implements PossibilityConsequence {

        }

        record AddObjet(String objetId) implements PossibilityConsequence {

        }

        record RemoveObjet(String objetId) implements PossibilityConsequence {

        }

}