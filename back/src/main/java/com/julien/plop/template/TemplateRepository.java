package com.julien.plop.template;

import com.julien.plop.space.domain.model.Board;
import com.julien.plop.scenario.Scenario;

import java.util.List;
import java.util.Optional;

public interface TemplateRepository {

    Optional<Template> findByCode(String code);

    class InMemory implements TemplateRepository {

        private final Scenario scenario = new Scenario(List.of());
        private final Board board = new Board(List.of());
        private final Template first = new Template(new Template.Id("firstId"), "first", "Mon premier jeu", scenario, board);

        @Override
        public Optional<Template> findByCode(String code) {
            if(code.equals(first.code())) {
                return Optional.of(first);
            }
            return Optional.empty();
        }
    }
}
