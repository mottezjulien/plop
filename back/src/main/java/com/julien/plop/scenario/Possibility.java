package com.julien.plop.scenario;

import com.julien.plop.generic.AndOrOr;

import java.util.List;

public record Possibility(
        Id id,
        PossibilityTrigger trigger,
        List<PossibilityCondition> conditions,
        AndOrOr conditionType,
        List<PossibilityConsequence> consequences) {

    public record Id(String value) {

    }

}