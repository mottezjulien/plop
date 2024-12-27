package com.julien.plop.board.domain;

public record BoardRect(BoardPoint bottomLeft, BoardPoint topRight) {

    public boolean contains(BoardPoint point) {
        return point.isBetween(bottomLeft, topRight);
    }
}
