package com.practice.afterJava8;

public interface PrivateMethodInterface {
    int myMethod(int a, int b);
    default void method1(){
        common();
    }

    default void method2(){
        common();
    }

    private void common(){
        System.out.println("Common logic");
    }
}
