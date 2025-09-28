package com.practice.questions;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class SecondLargest{
    public static void main(String[] args) {
//        int[] arr = {10, 20, 49, 50, 99};
        int[] arr = {10, 20, 99, 4, 45, 99};
        raw(arr);
        usingSort(arr);
    }



    private static void usingSort(int[] arr){
        int[] array = Arrays.stream(arr)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
        System.out.println(array[1]);
    }

    private static void raw(int[] arr) {
        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
        for(int num : arr){
            if(num>first){
                second=first;
                first = num;
            }else if (num>second && num!=first){
                // this condition is used to handle duplicates largest number
                second = num;
            }
        }
        System.out.println("First Largest: "+first);
        System.out.println("Second Largest: "+second);
    }
}
