package io.collective;

public class Main {
    public static void main(String[] args) {
        SimpleAgedCache simpleAgedCache = new SimpleAgedCache();
        simpleAgedCache.put("aKey", "aValue", 2000);
        simpleAgedCache.put("anotherKey", "anotherValue", 4000);
        System.out.println(simpleAgedCache.isEmpty());
        simpleAgedCache.print();
        System.out.println(simpleAgedCache.size());
    }
}
