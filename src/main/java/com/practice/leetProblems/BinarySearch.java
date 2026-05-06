package com.practice.leetProblems;

public class BinarySearch {
    public static void main(String[] args) {
        int[] myArr = {1, 3, 5, 7, 9, 11, 13};
        int index = binarySearch(myArr, 9);
        System.out.println("index: "+index);
        System.out.println("index: "+binarySearch(new int[]{2, 4, 6, 8, 10}, 6));

        System.out.println("first occurrence index: "+firstOccurrence(new int[]{1, 2, 2, 2, 3, 4}, 2));
        System.out.println("last occurrence index: "+lastOccurrence(new int[]{1, 2, 2, 2, 3, 4}, 2));
        System.out.println("Count of occurrence: "+countOfOccurrences(new int[]{1, 2, 2, 2, 3, 4}, 2));
    }

    public static int countOfOccurrences(int[] arr, int target){
        int firstOccurrenceIndex = firstOccurrence( arr, target);
        int lastOccurrenceIndex = lastOccurrence(arr, target);
        return (lastOccurrenceIndex-firstOccurrenceIndex)+1;
    }

    public static int firstOccurrence(int[] arr, int target){
        int left = 0;
        int right = arr.length -1;
        int result = -1;
        while(left<=right) {
            int mid = left+(right-left)/2;
            if(arr[mid]==target){
                result = mid;
                right = mid-1; // move left for first occurrence
            } else if (arr[mid]<target) {
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return result;
    }

    public static int lastOccurrence(int[] arr, int target){
        int left = 0;
        int right = arr.length -1;
        int result = -1;
        while(left<=right) {
            int mid = left+(right-left)/2;
            if(arr[mid]==target){
                result = mid;
                left = mid+1; // move left for first occurrence
            } else if (arr[mid]<target) {
                left = mid+1;
            }else{
                right = mid-1;
            }
        }
        return result;
    }

    public static int binarySearch(int[] arr, int target){
        int left = 0;
        int right = arr.length -1;
        while(left<=right) {
            int mid = left +(right - left) / 2;
            System.out.println(arr[mid]);
            if(target == arr[mid]){
                System.out.println(".......");
                return mid;
            }
            if (target > arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }


}
