package com.practice.questions;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringRelated {

    public static void main(String[] args) {

        String str = "Hello world";
        reverseString(str);
//        createStringOutOfCharArray();
//        getCountOfDistinctCharactersInString();

//        palindromeCheck("abcba"); // true
//        palindromeCheck("abc"); // false
        System.out.println(anagramCheck("race", "card")); // false
        System.out.println(anagramCheck("listen", "silent")); // true

    }

    private static void createStringOutOfCharArray() {
        char[] chars = {'H', 'e', 'l', 'l', 'o'};
        String s1 = new String(chars);
        System.out.println(s1);
        String s2 = String.valueOf(chars);
        System.out.println(s2);
        String s3 = String.copyValueOf(chars);
        System.out.println(s3);
        // to convert only part of a char array
        String s4 = new String(chars, 2, 3);
        System.out.println(s4); //llo
    }


    private static void reverseString(String str) {
        // using string builder - preferred
        String reversed = new StringBuilder(str).reverse().toString();
        System.out.println(reversed);
//        // without built-in reverse()
        String result = "";
        for (int i = str.length() - 1; i >= 0; i--) {
//            result = result + str.charAt(i);
            result += str.charAt(i);
        }

        System.out.println(result);

        // using intstream for above
//        String string = IntStream.range(0 , str.length())
//                .map(i->str.length()-1-i)
//                .mapToObj(i -> str.charAt(i))
//                .collect(
//                        StringBuilder::new,
//                        StringBuilder::append,
//                        StringBuilder::append
//                ).toString();
//        System.out.println(string);

        // Using Arrays stream and reduce
        String reverseUsingArraysAndStream = Arrays.stream(str.split(""))
                .reduce("", (a, b) -> b + a);
        System.out.println(reverseUsingArraysAndStream);

        // using String.chars()
        String usingStringChars = str.chars()
                .mapToObj(c -> (char) c)
                .collect(
                        StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append
                )
                .reverse()
                .toString();
        System.out.println(usingStringChars);

        // reverse words in sentence
        String sentence = "My name is Harry";
        StringBuilder reversed_sb = new StringBuilder();
        String[] wordArray  = sentence.split(" ");
        for(int i= wordArray.length-1;i>=0;i--){
            reversed_sb.append(wordArray[i]).append(" ");
        }
        System.out.println(reversed_sb.toString().trim());



    }


    private static void getCountOfDistinctCharactersInString() {
        String str1 = "abcdABCDabcd";
        LinkedHashMap<Character, Long> charCounts = str1.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        c -> c, //this groups equal characters together.
                        LinkedHashMap::new, //map supplier: Store the grouping result inside a LinkedHashMap(preserves insertion order).
                        Collectors.counting() // This tells Java what to do with each group of characters: Count how many items are in each group
                ));
        System.out.println(charCounts);

        // case-insensitive distinct character count
        TreeMap<Character, Long> charCountsIgnoreCase = str1.toLowerCase().chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        c -> c, //this groups equal characters together.
                        TreeMap::new, //map supplier: Store the grouping result inside a TreeMap(use natural order: a->z, A-Z, 0-9). // by default it is HashMap
                        Collectors.counting() // This tells Java what to do with each group of characters: Count how many items are in each group
                ));
        System.out.println(charCountsIgnoreCase);

        // sorted by character frequency (ascending)
        // first create a map then sort the map using values(character count)
        Map<Character, Long> sortedMapWithCharacterFrequency = str1.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        c -> c, //this groups equal characters together.
                        Collectors.counting() // This tells Java what to do with each group of characters: Count how many items are in each group
                ))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getValue(),
                        (a, b) -> a,
                        () -> new LinkedHashMap<>()
                ));
        System.out.println(sortedMapWithCharacterFrequency);

        // regular loop (fastest) using merge function
        //merge(K key, V value, BiFunction<V,V,V> remappingFunction)
        // This means:
        //If the key does NOT exist in the map, insert it with the given value.
        //If the key already exists, combine the old and new value using the given remappingFunction.
        LinkedHashMap<Character, Integer> counts = new LinkedHashMap<>();
        for(char ch: str1.toCharArray()){
            counts.merge(ch, 1, (v1,v2)->v1+v2);
            // remember for same key initial values would always be 1 (what we have defined)
            // so v2 will always be 1
        }
        System.out.println(counts);

    }

    private static void palindromeCheck(String str){
        // madam
        //string == reverse(string)
        boolean isPalindromeCheck = true;
        for(int i = 0;i<str.length()/2;i++){
            if(str.charAt(i)!=str.charAt(str.length()-1-i)){
                isPalindromeCheck = false;
                break;
            }
        }
        System.out.println(isPalindromeCheck);
    }

    private static boolean anagramCheck(String str1, String str2){
        // race - care : same characters in both strings
        // Sorted or character frequency same → anagram
        if(str1.length()!=str2.length()){
            return false;
        }
        char[] c1 = str1.toCharArray();
        char[] c2 = str2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        return Arrays.equals(c1, c2);

    }

}
