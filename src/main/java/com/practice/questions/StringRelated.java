package com.practice.questions;

import java.util.Arrays;
import java.util.stream.IntStream;

public class StringRelated {

    public static void main(String[] args) {

        String str = "Hello world";
//        reverseString(str);
        createStringOutOfCharArray();






    }

    private static void createStringOutOfCharArray(){
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
        for(int i = str.length()-1; i>=0; i--){
//            result = result + str.charAt(i);
            result+= str.charAt(i);
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

    }

}
