package com.julien.plop.template.domain;

import com.julien.plop.board.model.Board;
import com.julien.plop.scenario.Scenario;

import java.util.UUID;

public record Template(Id id, String code, String label, String version, Scenario scenario, Board board) {

    public record Id(String value) {

    }

    public static Id generateId() {
        return new Id(UUID.randomUUID().toString());
    }

}
