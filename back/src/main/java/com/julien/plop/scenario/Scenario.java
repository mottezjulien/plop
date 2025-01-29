package com.julien.plop.scenario;

import java.util.List;

public record Scenario(Id id, List<Step> steps) {

    public record Id(String value) {

    }

    public record Step(Id id, String label, List<Target> targets, List<Possibility> possibilities) {

        public record Id(String value) {

        }

    }

    public record Target(String label, String desc, boolean optional) {

    }


}