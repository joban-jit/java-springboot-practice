package com.practice.questions;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1};
        for(int i=0;i<arr.length-1;i++){
            for(int j =0;j<arr.length-1-i;j++){
//                The inner loop goes up to arr.length - i - 1. As more elements get sorted at the end of the array on each pass, you don’t need to check them again—they’re already in place
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
