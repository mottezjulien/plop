package com.julien.plop.scenario;

import com.julien.plop.i18n.domain.I18n;
import com.julien.plop.tools.StringTools;

import java.util.List;
import java.util.Optional;

public record Scenario(Id id, String label, List<Step> steps) {

    public record Id(String value) {

        public Id() {
            this(StringTools.generate());
        }

    }

    public record Step(Id id, Optional<I18n> label, List<Target> targets, List<Possibility> possibilities) {

        public record Id(String value) {
            public Id() {
                this(StringTools.generate());
            }
        }

    }

    public record Target(Optional<I18n> label, Optional<I18n> desc, boolean optional) {

    }

    public Scenario(String label, List<Step> steps) {
        this(new Id(), label, steps);
    }

}