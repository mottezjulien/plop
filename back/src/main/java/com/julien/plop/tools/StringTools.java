package com.julien.plop.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

public class StringTools {

    public static boolean isFilled(String str) {
        return str != null && !str.isBlank();
    }

    public static String generate() {
        return UUID.randomUUID().toString();
    }

    public static String toJson(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static <Obj> Obj fromJson(String str, Class<Obj> clazz) throws JsonProcessingException {
        return new ObjectMapper().readValue(str, clazz);
    }

}
