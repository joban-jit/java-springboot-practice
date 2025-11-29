package com.practice.questions;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K,V>  extends LinkedHashMap<K,V> {
    private final int capacity;

    public LRUCache(int capacity){
        super(capacity, 0.75f, true);
        // capacity: Allocate enough space to hold this many elements without resizing
        // loadFactor: when the map becomes 75% full, it grows automatically.
        // accessOrder: true: Every time you call get(key) or put(key, value), that entry gets moved to the end (most recent).
        // The oldest entry becomes the first one, so removeEldestEntry() can easily evict it.
        this.capacity = capacity;
    }


    @Override
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest){
        return size()>capacity;
    }

    public static void main(String[] args){
        LRUCache<Integer, String> cache = new LRUCache<>(3);
        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");
        System.out.println(cache);//{1=A, 2=B, 3=C}
        cache.get(1); // Access 1 -> most recent
        System.out.println(cache); //{2=B, 3=C, 1=A}
        cache.put(4, "D"); // Evicts the oldest entry (2, "B")
        System.out.println(cache); //{3=C, 1=A, 4=D}
    }
}
