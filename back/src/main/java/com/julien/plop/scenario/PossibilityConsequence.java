package com.julien.plop.scenario;


public sealed interface PossibilityConsequence permits PossibilityConsequence.StepDone, PossibilityConsequence.GameWin, PossibilityConsequence.GameOver {

        record StepDone(Scenario.Step.Id stepId) implements PossibilityConsequence {

        }

        record GameWin() implements PossibilityConsequence {

        }

        record GameOver() implements PossibilityConsequence {

        }

}