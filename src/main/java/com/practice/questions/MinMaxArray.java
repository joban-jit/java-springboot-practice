package com.practice.questions;

import java.util.Arrays;

public class MinMaxArray {
    public static void main(String[] args) {
        int[] arr = {123,234,1121,1,432};
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().orElseThrow();
        System.out.println("Max "+max+" Min: "+min);
    }
}
