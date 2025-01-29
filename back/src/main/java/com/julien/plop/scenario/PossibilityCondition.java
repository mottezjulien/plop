package com.julien.plop.scenario;

import com.julien.plop.board.model.BoardSpace;
import com.julien.plop.generic.BeforeOrAfter;

import java.time.Duration;

public sealed interface PossibilityCondition permits
        PossibilityCondition.InSpace, PossibilityCondition.OutSpace,
        PossibilityCondition.AbsoluteTime,
        PossibilityCondition.InStep,
        PossibilityCondition.OtherCondition
{

    record Id(String value) {

    }

    record InSpace(Id id, BoardSpace.Id spaceId) implements PossibilityCondition {

    }

    record OutSpace(Id id, BoardSpace.Id spaceId) implements PossibilityCondition {

    }

    record AbsoluteTime(Id id, Duration duration, BeforeOrAfter beforeOrAfter) implements PossibilityCondition {

    }

    record InStep(Id id, Scenario.Step.Id stepId) implements PossibilityCondition {

    }

    record OtherCondition(Id id, Id otherConditionId) implements PossibilityCondition {

    }


}