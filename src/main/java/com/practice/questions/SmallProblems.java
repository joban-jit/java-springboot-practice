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
        sumOfDigitsOfGivenNumber(3754132);
        countVowels();


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

    private static void countVowels(){
        String s1 = "Hello";
        int count =0;
        char[] charArray = s1.toLowerCase().toCharArray();
        for(char ch:charArray){
            if("aieou".indexOf(ch)!=-1){
                count++;
            }
        }
        System.out.println(count);
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

    private static void sumOfDigitsOfGivenNumber(int n){

        // below approach is not good
        // Splitting string into an array and converting back to numbers is heavier than necessary.
        // String.split("") creates extra objects and is less efficient than using math

        String str = String.valueOf(n);
        String[] stringDigitArray = str.split("");
        int sum = Arrays.stream(stringDigitArray)
                .mapToInt(s -> Integer.parseInt(s))
                .sum();
        System.out.println(sum);//25

        // if we want to still use streams, this is slightly better way
        // .map(c -> c - '0')
        //

        int sum1 = String.valueOf(n) // let's say n = 1234
                .chars()// IntStream of ASCII values of each character e.g. 49 50 51 52
                .map(c -> c - '0')//This converts each ASCII code to the actual digit.
                // '0' has ASCII value 48 so by subtracting 48 we get actual digit
                // 49 − 48 = 1, 50 − 48 =2, 51 − 48=3, 52 − 48=4
                .sum();

        System.out.println(sum1); // 25


        // best way - using arithmetic
        int sum2 = 0;
        while(n>0){
            sum2 = sum2+n%10; //n % 10 gives the last digit of the number.
            n = n/10; //Since n is an integer, dividing by 10 drops the decimal part:
        }
        System.out.println(sum2); //25


    }


}
