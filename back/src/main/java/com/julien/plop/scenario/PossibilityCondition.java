package com.julien.plop.scenario;

import com.julien.plop.space.domain.model.BoardSpace;

public sealed interface PossibilityCondition permits
        PossibilityCondition.No, PossibilityCondition.InSpace, PossibilityCondition.BetweenAbsoluteTime, PossibilityCondition.InStep {

    record No() implements PossibilityCondition {

    }

    record InSpace(BoardSpace.Id spaceId) implements PossibilityCondition {

    }

    record BetweenAbsoluteTime(String fromTime, String toTime) implements PossibilityCondition {

    }

    record InStep(Scenario.Step.Id stepId) implements PossibilityCondition {

    }

}