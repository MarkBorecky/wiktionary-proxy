package fi.maro.wiktionaryproxy.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MultiKeyMap<K, V> {

    private Map<K, Map<K, Map<K, V>>> map;

    public MultiKeyMap() {
        this.map = new HashMap<>();
    }

    public void put(K key1, K key2, K key3, V value) {
        map.get(key1).get(key2).put(key3, value);

    }

    public Set<K> keySet1() {
        return map.keySet();
    }

    public Set<K> keySet2(K lang) {
        return map.get(lang).keySet();
    }

    public Set<K> keySet3(K lang, K paragraph) {
        return map.get(lang).get(paragraph).keySet();
    }

    public V get(K key1, K key2, K key3) {
        return map.get(key1).get(key2).get(key3);
    }
}
