package com.practice.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BinarySearch {
    public static void main(String[] args) {

        int[] nums = {2, 4, 6, 8, 10, 12, 14}; // sorted array
        int i = Arrays.binarySearch(nums, 6);
        System.out.println(i);

        List<Integer> l1 = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(l1);

        System.out.println(customBinarySearch(nums,12));
//        System.out.println(binarySearch(nums,12));
    }

    private static int customBinarySearch(int[] arr, int key){
        int left = 0, right = arr.length-1;

        while(left<=right){
            int mid = left+(right - left)/2;
            if(arr[mid]==key){
                return mid;// found
            }else if(arr[mid]<key){
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return -1; // not found
    }

    // not standard
//    public static int binarySearch(int arr[], int key) {
//        int low = 0, high = arr.length-1;
//        int mid = (low + high) / 2;
//
//        while (low <= high) {
//            if (arr[mid] < key) {
//                low = mid + 1;
//            } else if (arr[mid] == key) {
//                return mid;
//            } else {
//                high = mid - 1;
//            }
//            mid = (low + high) / 2;
//        }
//
//        if (low > high) {
//            return -1;
//        }
//
//        return -1;
//    }


}
