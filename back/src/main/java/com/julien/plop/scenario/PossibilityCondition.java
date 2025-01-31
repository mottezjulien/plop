package com.julien.plop.scenario;

import com.julien.plop.board.model.BoardSpace;
import com.julien.plop.generic.BeforeOrAfter;

import java.time.Duration;

public sealed interface PossibilityCondition permits
        PossibilityCondition.InsideSpace,
        PossibilityCondition.OutsideSpace,
        PossibilityCondition.AbsoluteTime,
        PossibilityCondition.RelativeTimeAfterOtherTrigger,
        PossibilityCondition.InStep,
        PossibilityCondition.OtherCondition
{

    record Id(String value) {

    }

    record InsideSpace(Id id, BoardSpace.Id spaceId) implements PossibilityCondition {

    }

    record OutsideSpace(Id id, BoardSpace.Id spaceId) implements PossibilityCondition {

    }

    record RelativeTimeAfterOtherTrigger(Id id, Id otherTriggerId, Duration duration) implements PossibilityCondition {

    }

    record AbsoluteTime(Id id, Duration duration, BeforeOrAfter beforeOrAfter) implements PossibilityCondition {

    }

    record InStep(Id id, Scenario.Step.Id stepId) implements PossibilityCondition {

    }

    record OtherCondition(Id id, Id otherConditionId) implements PossibilityCondition {

    }


}