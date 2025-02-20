package com.julien.plop.tools;

import java.util.ArrayList;
import java.util.List;

public class ListTools {

    public static <Elt> List<Elt> diffOthersInMore(List<Elt> list, List<Elt> others) {
        List<Elt> result = new ArrayList<>();
        for (Elt elt : others) {
            if (!list.contains(elt)) {
                result.add(elt);
            }
        }
        return result;
    }

}
