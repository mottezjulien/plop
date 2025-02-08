package com.julien.plop;

import java.util.Locale;

public enum Language {
    FR, EN;

    public static Language valueOfSafe(String language) {
        try {
            return Language.valueOf(language.toUpperCase());
        } catch (IllegalArgumentException e) {
            return FR;
        }
    }
}
