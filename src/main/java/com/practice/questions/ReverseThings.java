package com.practice.questions;

import java.util.*;
import java.util.stream.IntStream;

public class ReverseThings {
    public static void main(String[] args) {
        String str = "Hello";
//        reverseStringUsingStringBuilder(str);

//        manuallyReverseString(str);

        List<Integer> l1 = new ArrayList<>(Arrays.asList(1,12,34,2,45,2,55,56,23));
        reverseList(l1);


    }

    private static <T> void reverseList(List<Integer> list){
        // Using Stream and return new list:
        List<Integer> list1 =  IntStream.range(0, list.size())
                .mapToObj(i -> list.get(list.size() - 1 - i))
                .toList();
        System.out.println(list1);

        // Using Stream and linkedList
        LinkedList<Integer> list2 = list.stream().collect(
                LinkedList::new,
                LinkedList::addFirst,
                (l1, l2) -> l1.addAll(0, l2)
        );
        System.out.println(list2);
        // Using collection to reverse same list:- best way
        Collections.reverse(list);
        System.out.println(list);
    }

    private static void manuallyReverseString(String str) {
//        // without built-in reverse()
        String result = "";
        for(int i = str.length()-1; i>=0; i--){
//            result = result + str.charAt(i);
            result+= str.charAt(i);
        }

        System.out.println(result);

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


    }

    private static void reverseStringUsingStringBuilder(String str) {
        String reversed = new StringBuilder(str).reverse().toString();
        System.out.println(reversed);
    }
}
