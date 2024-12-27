package com.julien.plop.board.domain;

import java.util.List;

public record BoardSpace(BoardSpaceId id, String label, int priority, List<BoardRect> rects) {

    public boolean contains(BoardPoint point) {
        return rects.stream()
                .anyMatch(rect -> rect.contains(point));
    }

}
