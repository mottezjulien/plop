package com.julien.plop.board.domain;

public record BoardPoint(double lat, double lng) {

    public boolean isBetween(BoardPoint bottomLeft, BoardPoint topRight) {
        return bottomLeft.lat <= lat && lat <= topRight.lat
                && bottomLeft.lng <= lng && lng <= topRight.lng;
    }

}
