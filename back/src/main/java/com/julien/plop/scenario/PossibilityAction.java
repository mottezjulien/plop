package com.julien.plop.scenario;

import com.julien.plop.space.domain.model.BoardSpace;

public sealed interface PossibilityAction permits
        PossibilityAction.InSpace, PossibilityAction.OutSpace, PossibilityAction.AbsoluteTime, PossibilityAction.RelativeTimeAfterAction {

    record Id(String value) {

    }

    record InSpace(PossibilityAction.Id actionId, BoardSpace.Id spaceId) implements PossibilityAction {

    }

    record OutSpace(PossibilityAction.Id actionId, BoardSpace.Id spaceId) implements PossibilityAction {

    }

    record AbsoluteTime(PossibilityAction.Id actionId, String time) implements PossibilityAction {

    }

    record RelativeTimeAfterAction(PossibilityAction.Id actionId, PossibilityAction.Id from, String time) implements PossibilityAction {

    }
}