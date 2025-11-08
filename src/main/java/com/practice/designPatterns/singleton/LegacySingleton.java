package com.practice.designPatterns.singleton;

import java.time.LocalDate;

public class LegacySingleton {
    private static LegacySingleton legacySingleton;
    private LegacySingleton(){}

    public synchronized static  LegacySingleton getLegacySingleton() {
        if(legacySingleton==null){
            legacySingleton = new LegacySingleton();
        }
        return legacySingleton;
    }

    public void printDate(){
        System.out.println(LocalDate.now());
    }
}
