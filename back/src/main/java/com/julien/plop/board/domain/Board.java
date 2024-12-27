package com.julien.plop.board.domain;

import java.util.List;
import java.util.stream.Stream;

public record Board(BoardId id, List<BoardSpace> spaces) {

    public Stream<BoardSpace> findSpaces(BoardPoint point) {
        return spaces.stream()
                .filter(space -> space.contains(point));
    }

}
