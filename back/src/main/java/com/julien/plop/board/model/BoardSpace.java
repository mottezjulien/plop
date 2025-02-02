package com.julien.plop.board.model;

import com.julien.plop.StringTools;

import java.util.List;

public record BoardSpace(Id id, String label, int priority, List<Rect> rects) {

    public record Id(String value) {

        public Id() {
            this(StringTools.generate());
        }

    }

    public record Rect(Point bottomLeft, Point topRight) {

    }

    public record Point(float lat, float lng) {

    }

    public BoardSpace(String label, int priority, List<Rect> rects) {
        this(new Id(), label, priority, rects);
    }
}
