package com.julien.plop;

import java.util.UUID;

public class StringTools {

    public static String generate() {
        return UUID.randomUUID().toString();
    }

}
