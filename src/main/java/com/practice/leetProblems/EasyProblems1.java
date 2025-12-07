package com.practice.leetProblems;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class EasyProblems1 {
    public static void main(String[] args) {
        System.out.println(countOdds(3,7));
//        int[] myArray = {3,2,4};
        int[] myArray = {2,7,11,15};
        int[] result = twoSum(myArray, 9);
        for (int i : result) {
            System.out.println(i);
        }
        System.out.println(isPalindrome(121));
        System.out.println(isPalindrome(1221));
    }


    public static int countOdds(int low, int high) {
        long count = IntStream.rangeClosed(low, high).filter(i->i%2!=0).count();
        return (int)count;
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] arr = new int[2];
        for(int i =0;i<nums.length-1;i++){
            int a = nums[i];
            int b = nums[i+1];
            int c = a+b;
            if(c==target){
                arr[0] =i;
                arr[1]=i+1;
                break;
            }
        }
        return arr;
    }
    public static boolean isPalindrome(int x) {
        String intStr = String.valueOf(x);
        for(int i=0;i<intStr.length()/2;i++){
            if(intStr.charAt(i)!=intStr.charAt(intStr.length()-1-i)){
                return false;
            }
        }
        return true;
    }
}
