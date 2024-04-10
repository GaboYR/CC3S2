package io.collective;

import java.time.Clock;
import java.util.Map;
import java.util.HashMap;

public class SimpleAgedCache {
    private Map<String, String> cache;
    private Map<Map<String,String>, Long> cacheAge;
    public SimpleAgedCache() {
        cache = new HashMap<>();
        cacheAge = new HashMap<>();
    }
    public SimpleAgedCache(Clock clock) {
        cache = new HashMap<>();
        cacheAge = new HashMap<>();
        
    }
    public void put(String key, String value, long age) {
        cache.put(key, value);
        cacheAge.put(cache, age);
    }
    public String get(String key) {
        return cache.get(key);
        
    }
    public boolean isEmpty() {
        return cache.isEmpty();
    }
    public int size() {
        return cache.size();
    }
    public void print(){
        for (Map.Entry<String, String> entry : cache.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }
}
