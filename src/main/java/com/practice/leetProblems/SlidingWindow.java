package com.practice.leetProblems;

public class SlidingWindow {
    public static void main(String[] args) {
        int maxSumOfSubArray = maxSumOfSubArray(new int[]{2, 1, 5, 1, 3, 2}, 3);
        System.out.println(maxSumOfSubArray);
    }

    public static int maxSumOfSubArray(int[] arr, int k){
        //Maximum Sum Subarray of Size K
        //arr = [2, 1, 5, 1, 3, 2]
        //k = 3

        //[2,1,5] = 8
        //[1,5,1] = 7
        //[5,1,3] = 9 - Answer
        //[1,3,2] = 6
        int windowSum = 0;
        int maxSum  =0;
        // First window
        for(int i=0;i<k;i++){
            windowSum+=arr[i];
        }

        maxSum  = windowSum;

        // slide window
        for(int i=k;i<arr.length;i++){
            windowSum += arr[i]; // add next
            windowSum = windowSum-arr[i-k];
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;

    }

    public static int minSubArrayLen(int target, int[] nums){
        //Smallest Subarray with Sum ≥ Target
        //arr = [2,3,1,2,4,3]
        //target = 7
        //[2,3,1,2] = 8 (length 4)
        //[3,1,2,4] = 10 (length 4)
        //[2,4,3] = 9 (length 3)
        //[4,3] = 7 (length 2)  ← answer
        int left = 0;
        int sum = 0;
        int minLength = nums.length;
        for(int right=0;right<nums.length;right++){
            sum = sum+nums[right];
            // When window becomes valid (sum ≥ target):
            //Update answer
            //Shrink window from left
            //Try to make it smaller
            while(sum>=target){
                minLength = Math.min(minLength, right-left+1);
                sum = sum - nums[left];
                left++;
            }
        }

        return -1;
    }
}
