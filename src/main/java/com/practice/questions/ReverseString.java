package com.practice.questions;

import java.util.stream.IntStream;

public class ReverseString {
    public static void main(String[] args) {
        String str = "Hello";
        String reversed = new StringBuilder(str).reverse().toString();
        System.out.println(reversed);

        // without built-in reverse()
        String result = "";
        for(int i = str.length()-1;i>=0;i--){
//            result = result + str.charAt(i);
            result+=str.charAt(i);
        }

        System.out.println(result);


    }
}
