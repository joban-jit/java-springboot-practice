package com.practice.questions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SmallProblems {
    public static void main(String[] args) {
//        swapTwoNumbersWithoutThird();
//        checkVowelIsPresent();
//        checkIfListContainsOnlyOddNumber();
//        removeSpacesFromString();
//        printDateInSpecificFormat();
//        mergeTwoLists();
        sortHashMapByValue();


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

    private static void printDateInSpecificFormat(){
        String pattern1 = "MM-dd-yyyy";
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern(pattern1);
        System.out.println(LocalDate.now().format(dateTimeFormatter1));

        String pattern2 = "yyyy/MM/dd HH:mm:ss";
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern(pattern2);
        System.out.println(LocalDateTime.now().format(dateTimeFormatter2));
    }

    private static void mergeTwoLists(){
        List<Integer> l1 = new ArrayList<>(Arrays.asList(1, 2, 3, 5));
        List<Integer> l2 = new ArrayList<>(Arrays.asList( 8, 13, 21, 34));
        l1.addAll(l2);
        System.out.println(l1);

    }

    private static void sortHashMapByValue(){
        Map<String, Integer> scores = new HashMap<>();
        scores.put("David", 95);
        scores.put("Jane", 80);
        scores.put("Mary", 97);
        scores.put("Lisa", 78);
        scores.put("Dino", 65);
        System.out.println(scores);
        // By sorting the values and using LinkedHashMap to preserve the insertion order
        LinkedHashMap<String, Integer> sortedByValueLinkedHashMap = scores.entrySet()
                .stream()
//                .sorted((e1, e2) -> e1.getValue().compareTo(e2.getValue())) // valid
//                .sorted(Comparator.comparing(e->e.getValue())) //valid
//                .sorted(Comparator.comparing(Map.Entry::getValue)) // valid
                .sorted(Map.Entry.comparingByValue()) // valid - inbuilt comparator (interface static method)
                .collect(Collectors.toMap(
                        e -> e.getKey(), // key mapper
                        e -> e.getValue(), // value mapper
                        (a, b) -> a, // merge function: if duplicate keys appears - if two keys class, keep the first one
                        () -> new LinkedHashMap<>() // map supplier: which type of map should be created
                ));

        System.out.println(sortedByValueLinkedHashMap);


    }


}
