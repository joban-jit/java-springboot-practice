package com.practice.questions;

public class Palindrome {
    public static void main(String[] args) {
        String str = "madam";
        String reveresed = new StringBuilder(str).reverse().toString();
        boolean isPalindrome = str.equals(reveresed);
        System.out.println(isPalindrome);
    }
}
