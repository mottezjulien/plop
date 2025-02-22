package com.julien.plop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class CacheKeyList<Key, Value> {

    private final Map<Key, List<Value>> cache = new HashMap<>();

    public List<Value> get(Key game) {
        return cache.getOrDefault(game, new ArrayList<>());
    }

    public void put(Key key, List<Value> values) {
        cache.put(key, values);
    }

    public void add(Key key, Value value) {
        List<Value> values = get(key);
        values.add(value);
        cache.put(key, values);
    }

    public Optional<Key> findFirstIf(Predicate<Key> predicate) {
        return cache.keySet().stream()
                .filter(predicate)
                .findFirst();
    }

    public void clear() {
        cache.clear();
    }
}
