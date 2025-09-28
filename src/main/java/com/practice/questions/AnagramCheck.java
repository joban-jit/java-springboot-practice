package com.practice.questions;

import java.util.Arrays;

public class AnagramCheck {
    public static void main(String[] args) {
        String s1 = "listen";
        String s2 = "silent";
        // // false because array comparison uses reference equality
//        boolean isAnagram = s1.length() == s2.length() && s1.chars().sorted().toArray().equals(s2.chars().sorted().toArray());
//        System.out.println(isAnagram);
        boolean isAnagram = Arrays.equals(s1.chars().sorted().toArray(), s2.chars().sorted().toArray());
        System.out.println(isAnagram);
    }
}
