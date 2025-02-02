package com.julien.plop.board.model;

import com.julien.plop.StringTools;

import java.util.List;

public record Board(Id id, List<BoardSpace> spaces) {

    public record Id(String value) {

        public Id() {
            this(StringTools.generate());
        }

    }

    public Board(List<BoardSpace> spaces) {
        this(new Id(), spaces);
    }

}
