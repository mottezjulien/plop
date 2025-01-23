package com.julien.plop.scenario;

import java.util.ArrayList;
import java.util.List;

public record Scenario(List<Step> steps) {

    public List<Step> generateSteps() {
        return new ArrayList<>(steps);
    }

    public record Step(Id id, String label, List<Target> targets, List<Possibility> possibilities) {

        public record Id(String value) {

        }

    }

    public record Target(String label, String desc, boolean optional) {

    }


}