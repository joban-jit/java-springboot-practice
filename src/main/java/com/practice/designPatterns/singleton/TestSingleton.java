package com.practice.designPatterns.singleton;

public class TestSingleton {
    public static void main(String[] args) {
        MySingleton mySingleton = MySingleton.INSTANCE;
        mySingleton.setPastDays(2);
        System.out.println(mySingleton.getPastDate());
        System.out.println(mySingleton.getFutureDate(2));

        LegacySingleton legacySingleton = LegacySingleton.getLegacySingleton();
        legacySingleton.printDate();

        SingletonUsingInnerClass singletonUsingInnerClass = SingletonUsingInnerClass.getInstance();
        singletonUsingInnerClass.printLocalDateTime();
    }
}
