package com.practice.leetProblems;

import java.util.Arrays;
import java.util.List;

public class TwoPointers {

    public static void main(String[] args) {
        System.out.println("hasPairWithSum: "+hasPairWithSum(new int[]{1, 2, 3, 4, 6, 8, 9}, 11));
        System.out.println(removeDuplicates(new int[]{1,1,1,2,2,3,4,4}));
        System.out.println(isPalindrome("racecar"));
        reverseString("racecar");
        moveZeros(new int[]{0,1,0,3,12});
        System.out.println(isPalindromeWithAlphaNumericAndCaseInsensitiveWithTwoPointer("Was it a car or a cat I saw?"));
    }

    public static boolean hasPairWithSum(int[] sortedArray, int target){
        int left = 0, right = sortedArray.length -1;
        while(left<right){
            int sum = sortedArray[left] + sortedArray[right];
            if(sum==target){
                System.out.println("Pair is ("+sortedArray[left]+","+sortedArray[right]+")");
                return true;
            }else if( sum<target){
                left++;
            }else {
                right++;
            }
        }
        return false;
    }

    public static int removeDuplicates(int[] sortedNums){
//arr = [1,1,2,2,3,4,4]
//Output = [1,2,3,4,_ ,_ ,_]


        if(sortedNums.length==0){
            return 0;
        }
        int i = 0;
        for(int j=1;j<sortedNums.length;j++){
            if(sortedNums[i]!=sortedNums[j]){
                i++;
                sortedNums[i]=sortedNums[j];
            }
        }
        // create a new array of correct size
//        int[] unique = new int[i+1];
//        for(int k = 0;k<=i;k++){
//            unique[k]=sortedNums[k];
//        }
        // above replaced with System.arraycopy(sortedNums, 0, unique, 0, i + 1);
        // above replaced with
        int[] unique = Arrays.copyOf(sortedNums, i+1);
        
        Arrays.stream(unique).forEach(System.out::println);
        System.out.println("--");
        return i+1; // count of unique elements

//        int i = 0; // slow pointer
//        for(int j = 1;j<sortedNums.length;j++){
//            if(sortedNums[i]!=sortedNums[j]){
//                i++;
//
//            }
//        }
//        return i;
    }

    public static boolean isPalindrome(String s){
//        for(int i=0;i<s.length();i++){
//            if(s.charAt(i)!=s.charAt(s.length()-1-i)){
//                return false;
//            }
//        }
        // using while loop
        int left = 0, right = s.length()-1;
        while(left<right){
            if(s.charAt(left)!=s.charAt(right)){
                return false;
            }
            left++;
            right--;
        }

        return true;
    }



    private static boolean isPalindromeWithAlphaNumericAndCaseInsensitiveWithTwoPointer(String s){
        int left = 0, right = s.length() -1;
        while(left<right){
            while(left<right && !Character.isLetterOrDigit(s.charAt(left))){
                left++;
            }
            while(left<right && !Character.isLetterOrDigit(s.charAt(right))){
                right--;
            }
            if(Character.toLowerCase(s.charAt(left))!= Character.toLowerCase(s.charAt(right))){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void reverseString(String s){
//        char[] charArray = s.toCharArray();
//        StringBuilder reversedStr = new StringBuilder();
//        for(int i=charArray.length-1;i>=0;i--){
//            reversedStr.append(charArray[i]);
//        }
//        System.out.println(reversedStr);
        char[] charArray = s.toCharArray();
        int left = 0, right=s.length()-1;
        while(left<right){
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;
            left++;
            right--;
        }
        String s1 = new String(charArray);
        System.out.println(s1);
    }

    public static void moveZeros(int[] numArray){
        //[0,1,0,3,12] → [1,3,12,0,0]
        int index = 0;
        for(int num: numArray){
            if(num!=0){
                numArray[index++]=num;
            }
        }
        while(index<numArray.length) {
            numArray[index++] = 0;
        }
        String arrayStr = Arrays.toString(numArray);
        System.out.println(arrayStr);
    }



    
}
