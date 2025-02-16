package com.julien.plop;

import java.util.ArrayList;
import java.util.List;

public class History<Obj> {

    private final List<Obj> list = new ArrayList<>();

    public void add(Obj obj) {
        list.add(obj);
    }

    public Obj last() {
        return list.getLast();
    }

}
