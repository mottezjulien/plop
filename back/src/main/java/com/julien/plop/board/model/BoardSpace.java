package com.julien.plop.board.model;

import java.util.List;

public record BoardSpace(Id id, String label, int priority, List<Rect> rects) {

    public record Id(String value) {

    }

    public record Rect(Point bottomLeft, Point topRight) {

    }

    public record Point(double lat, double lng) {

    }

}
