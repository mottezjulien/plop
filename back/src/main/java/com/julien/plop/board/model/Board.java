package com.julien.plop.board.model;

import java.util.List;

public record Board(Id id, List<BoardSpace> spaces) {

    public record Id(String value) {

    }

}
