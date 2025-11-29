package com.practice.designPatterns.singleton;

import java.time.LocalDate;

public class LegacySingleton {
    private static LegacySingleton legacySingleton;
    private static volatile LegacySingleton instance;

    private LegacySingleton() {
    }

    public synchronized static LegacySingleton getLegacySingleton() {
        if (legacySingleton == null) {
            legacySingleton = new LegacySingleton();
        }
        return legacySingleton;
    }

    // Thread safe Singleton using double-checked locking
    // using volatile and synchronized block
    public static LegacySingleton getInstance() {
        if (instance == null) {
            synchronized (LegacySingleton.class) {
                if (instance == null) {
                    instance = new LegacySingleton();
                }
            }

        }
        return instance;
    }

    public void printDate() {
        System.out.println(LocalDate.now());
    }
}
