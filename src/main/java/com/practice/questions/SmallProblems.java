package com.practice.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class SmallProblems {
    public static void main(String[] args) {
//        swapTwoNumbersWithoutThird();
//        checkVowelIsPresent();
//        checkIfListContainsOnlyOddNumber();
        removeSpacesFromString();


    }

    private static void removeSpacesFromString() {
        String s= "Hi how are you";
        String withoutWhiteSpaces = s.chars().mapToObj(i -> (char) i)
                .filter(c -> !Character.isWhitespace(c))
                .collect(
                        StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append
                ).toString();
        System.out.println(withoutWhiteSpaces);


    }

    private static void checkIfListContainsOnlyOddNumber() {
        // modern way
        List<Integer> l1 = List.of(1, 1, 2, 3, 5, 8, 13, 21, 34);
//        l1 = List.of(1, 1, 3, 5, 13, 21);
        boolean isListContainsOnlyOdd = l1.stream().allMatch(i -> i % 2 != 0);
        System.out.println(isListContainsOnlyOdd);

    }

    private static void checkVowelIsPresent() {
        String s1 = "Hello";
        System.out.println(s1.toLowerCase().matches(".*[aieou].*")); // true
        String s2 = "TV";
        System.out.println(s2.toLowerCase().matches(".*[aieou].*")); // false
    }

    private static void swapTwoNumbersWithoutThird() {
        int a = 10;
        int b = 20;
        a = a+b;
        b = a-b;
        a = a-b;
        System.out.println("a: "+a+", b: "+b); //a: 20, b: 10
    }
}
