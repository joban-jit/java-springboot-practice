package com.practice.questions;

public class LargestInteger {
    public static void main(String[] args) {
        int[] arr = {10, 20, 4, 100, 99};
        int first = Integer.MIN_VALUE;
        for(int num : arr){
            if(num>first){
                first = num;
            }
        }
        System.out.println(first);
    }
}
