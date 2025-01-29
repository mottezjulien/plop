package com.julien.plop.scenario;

import com.julien.plop.board.model.BoardSpace;

import java.time.Duration;

public sealed interface PossibilityTrigger permits
        PossibilityTrigger.InsideSpace,
        PossibilityTrigger.OutsideSpace,
        PossibilityTrigger.AbsoluteTime,
        PossibilityTrigger.RelativeTimeAfterTrigger {

    record Id(String value) {

    }

    record InsideSpace(Id id, BoardSpace.Id spaceId) implements PossibilityTrigger {

    }

    record OutsideSpace(Id id, BoardSpace.Id spaceId) implements PossibilityTrigger {

    }

    record AbsoluteTime(Id id, Duration duration) implements PossibilityTrigger {

    }

    record RelativeTimeAfterTrigger(Id id, Id otherTriggerId, Duration duration) implements PossibilityTrigger {

    }

}