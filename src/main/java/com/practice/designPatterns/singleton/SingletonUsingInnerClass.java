package com.practice.designPatterns.singleton;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public class SingletonUsingInnerClass {
    private SingletonUsingInnerClass(){};

    private static class SingletonHelper{
        private static final SingletonUsingInnerClass INSTANCE = new SingletonUsingInnerClass();
    }

    public static SingletonUsingInnerClass getInstance(){
        return SingletonHelper.INSTANCE;
    }

    public void printLocalDateTime(){
        System.out.println(LocalDateTime.now());
    }
}
