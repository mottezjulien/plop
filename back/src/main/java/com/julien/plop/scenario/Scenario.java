package com.julien.plop.scenario;

import com.julien.plop.I18n;

import java.util.List;
import java.util.Optional;

public record Scenario(Id id, List<Step> steps) {

    public record Id(String value) {

    }

    public record Step(Id id, String label, List<Target> targets, List<Possibility> possibilities) {

        public record Id(String value) {

        }

    }

    public record Target(I18n label, Optional<I18n> desc, boolean optional) {

    }


}