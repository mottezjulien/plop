package com.julien.plop.scenario;

import java.util.List;

public record Possibility(
        PossibilityAction action,
        PossibilityCondition condition,
        List<PossibilityConsequence> consequences) {

}