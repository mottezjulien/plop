package com.julien.plop;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Cache<Key, Obj> {

    private final Map<Key, WithDateTime<Obj>> cache = new HashMap<>();

    private final Duration duration;

    public Cache(Duration duration) {
        this.duration = duration;
    }

    public void put(Key key, Obj value) {
        LocalDateTime now = LocalDateTime.now();
        if(cache.containsKey(key)) {
            WithDateTime<Obj> valueObj = cache.get(key);
            if(!valueObj.isValidWith(duration)) {
                cache.put(key, new WithDateTime<>(value, now));
            }
        } else {
            cache.put(key, new WithDateTime<>(value, now));
        }
    }

    public Optional<Obj> get(Key key) {
        if(cache.containsKey(key)) {
            WithDateTime<Obj> value = cache.get(key);
            if(value.isValidWith(duration)) {
                return Optional.of(value.value());
            }
            cache.remove(key);
        }
        return Optional.empty();
    }

    public void clear(Key key) {
        cache.remove(key);
    }

}


record WithDateTime<Obj> (Obj value, LocalDateTime dateTime) {

    public boolean isValidWith(Duration duration) {
        return dateTime().plus(duration).isAfter(LocalDateTime.now());
    }

}