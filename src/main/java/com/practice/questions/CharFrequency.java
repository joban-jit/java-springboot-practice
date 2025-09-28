package com.practice.questions;

import java.util.Map;
import java.util.stream.Collectors;

public class CharFrequency {
    public static void main(String[] args) {
        String str = "programming";
//        String[] strArray = str.split(""); // this will create a lot of string objects
        Map<Character, Long> freq = str.chars()
                .mapToObj(i -> (char) i)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        System.out.println(freq);

    }
}
