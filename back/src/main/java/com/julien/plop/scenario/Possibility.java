package com.julien.plop.scenario;

import com.julien.plop.generic.AndOrOr;

import java.util.List;

public record Possibility(
        PossibilityTrigger trigger,
        List<PossibilityCondition> conditions,
        AndOrOr conditionType,
        List<PossibilityConsequence> consequences) {

}