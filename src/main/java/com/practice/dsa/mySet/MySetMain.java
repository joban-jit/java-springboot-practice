package com.practice.dsa.mySet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MySetMain {

    public static void main(String[] args) {

    }

    public static List<Integer> removeDuplicates(List<Integer> myList) {
        HashSet<Integer> mySet = new HashSet<>(myList);
        return new ArrayList<>(mySet);
    }

    public static boolean hasUniqueChars(String string) {
        char[] charArray = string.toCharArray();
        HashSet<Character> characters = new HashSet<>();
        for (char c : charArray) {
            if (!characters.add(c)) {
                return false;
            }
        }
        return true;
    }

    public static List<int[]> findPairs(int[] arr1, int[] arr2, int target) {
        HashSet<Integer> hs2 = new HashSet<>();
        List<int[]> result = new ArrayList<>();
        for (int i2 : arr2) {
            hs2.add(i2);
        }
        for (int i = 0; i < arr1.length; i++) {
            int difference = target - arr1[i];
            if (!hs2.add(difference)) {
                result.add(new int[]{arr1[i], difference});
            }
        }
        return result;
    }

    public static int longestConsecutiveSequence(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestStreak = 0;

        for (int num : numSet) {
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}
