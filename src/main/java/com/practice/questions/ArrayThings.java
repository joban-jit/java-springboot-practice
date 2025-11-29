package com.practice.questions;

import java.util.*;
import java.util.stream.Collectors;

public class ArrayThings {

    public static void main(String[] args) {

        //checkIfTwoArrayContainTheSameElements
        int[] a1 = {1,2,3,2,1};
        int[] a2 = {1,2,3};
        int[] a3 = {1,4,3,2};
        int[] a4 = {1,2,3,4};
        checkIfTwoArrayContainTheSameElements(a1, a2);
        checkIfTwoArrayContainTheSameElements(a4, a3);

        // get sum of all elements in an integer array in java
        int[] array = { 1, 2, 3, 4, 5 };
        int sum = getSumOfAllElementsInAnIntegerArray(array);
        System.out.println(sum);

        // to get min and max in an array
        minMaxInArray();

        //secondLargestUsingSort
        //        int[] arr = {10, 20, 49, 50, 99};
        int[] arr = {10, 20, 99, 4, 45, 99};
        secondLargestUsingSortManually(arr);
        secondLargestUsingSort(arr);

        // shuffle an array
        shuffleAnArray();

        // largest in array using for loop
        findLargestInArray();

    }

    private static void shuffleAnArray() {
        int[] array1 = { 1, 2, 3, 4, 5, 6, 7 };
        Random rand = new Random();
        for(int i=0;i<array1.length;i++){
            int randomIndex = rand.nextInt(array1.length);
            int temp = array1[randomIndex];
            array1[randomIndex] = array1[i];
            array1[i] = temp;
        }

        System.out.println(Arrays.toString(array1));

        // if we want to use stream then we need to create list
        List<Integer> list = Arrays.stream(array1).boxed().collect(Collectors.toList());
        Collections.shuffle(list);
        int[] shuffledArray = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println(Arrays.toString(shuffledArray));

    }

    private static void secondLargestUsingSort(int[] arr){
        int[] array = Arrays.stream(arr)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
        System.out.println(array[1]); // 2nd largest element
    }

    private static void secondLargestUsingSortManually(int[] arr) {
        int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
        for(int num : arr){
            if(num>first){
                second=first;
                first = num;
            }else if (num>second && num!=first){
                // this num!=first condition is used to handle duplicates largest number
                second = num;
            }
        }
        System.out.println("First Largest: "+first);
        System.out.println("Second Largest: "+second);
    }

    private static void minMaxInArray() {
        int[] arr = {123,234,1121,1,432};
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().orElseThrow();
        System.out.println("Max "+max+" Min: "+min);
    }

    private static int getSumOfAllElementsInAnIntegerArray(int[] array) {
        return Arrays.stream(array)
                .sum();
    }

    private static void checkIfTwoArrayContainTheSameElements(int[] ar1, int[] ar2){

        // Use Arrays.equals() - Checks order AND content
        // this doesn't sort or remove duplicate
        System.out.println(Arrays.equals(ar1, ar2));

        // Streams+sorting (recommended) - Works when Order does not matter and duplicates matter.
        boolean equals = Arrays.equals(
                Arrays.stream(ar1).sorted().toArray(),
                Arrays.stream(ar2).sorted().toArray()
        );
        System.out.println(equals);


        // Arrays contain same unique elements (duplicates ignored)
        equals = Arrays.equals(
                Arrays.stream(ar1).distinct().sorted().toArray(),
                Arrays.stream(ar2).distinct().sorted().toArray()
        );
        System.out.println(equals);

    }

    private static void findLargestInArray(){
        int[] arr = {10, 20, 4, 100, 99};
//        int largest = Integer.MIN_VALUE; // use this if you are not sure array is empty or not
        int largest = arr[0];
        for(int num : arr){
            if(num>largest){
                largest = num;
            }
        }
        System.out.println(largest);
    }

    }

