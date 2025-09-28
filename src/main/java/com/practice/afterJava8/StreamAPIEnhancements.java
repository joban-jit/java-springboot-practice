package com.practice.afterJava8;

import java.util.stream.Stream;

public class StreamAPIEnhancements {
    public static void main(String[] args) {
        takeWhileImpl();
    }

    private static void takeWhileImpl(){
        Stream.of(1,2,3,4,5)
                .takeWhile(n->n<4)
                .forEach(System.out::print);

        Stream.of(1,2,3,4,5)
                .filter(n->n<4)
                .forEach(System.out::print);
    }
}
