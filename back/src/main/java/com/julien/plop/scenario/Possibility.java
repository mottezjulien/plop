package com.julien.plop.scenario;

import com.julien.plop.generic.AndOrOr;
import com.julien.plop.tools.StringTools;

import java.util.List;

public record Possibility(
        Id id,
        PossibilityTrigger trigger,
        List<PossibilityCondition> conditions,
        AndOrOr conditionType,
        List<PossibilityConsequence> consequences) {

    public record Id(String value) {
        public Id() {
            this(StringTools.generate());
        }
    }

    public Possibility(PossibilityTrigger trigger, List<PossibilityCondition> conditions, AndOrOr conditionType, List<PossibilityConsequence> consequences) {
        this(new Id(), trigger, conditions, conditionType, consequences);
    }
}