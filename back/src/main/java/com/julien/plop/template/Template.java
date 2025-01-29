package com.julien.plop.template;

import com.julien.plop.board.model.Board;
import com.julien.plop.scenario.Scenario;

public record Template(Id id, String code, String label, String version,Scenario scenario, Board board) {

    public record Id(String value) {

    }

}
