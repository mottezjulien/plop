package com.julien.plop.template;

import com.julien.plop.game.Game;
import com.julien.plop.space.domain.model.Board;
import com.julien.plop.scenario.Scenario;

public record Template(Id id, String code, String label, Scenario scenario, Board board) {

    public record Id(String value) {

    }

    public Game generate() {
        return new Game(Game.Id.generate(), this.id, code, label, generateScenario(), generateBoard());
    }

    private Scenario generateScenario() {
        return new Scenario(scenario.generateSteps());
    }

    private Board generateBoard() {
        return new Board(board.spaces());
    }

}
