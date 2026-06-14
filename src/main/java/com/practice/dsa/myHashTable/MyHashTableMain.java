package com.practice.dsa.myHashTable;

import java.util.*;

public class MyHashTableMain {
    public static void main(String[] args) {
        MyHashTable myHashTable = new MyHashTable();
        myHashTable.printTable();
        // printed the addresses only
        // 0:
        // 1:
        // 2:
        // 3:
        // 4:
        // 5:
        // 6:
        myHashTable.set("nails", 100);
        myHashTable.set("tile", 50);
        myHashTable.set("lumbar", 80);
        myHashTable.set("bolts", 200);
        myHashTable.set("screws", 140);

        myHashTable.printTable();
        System.out.println("Value: " + myHashTable.get("nails"));
        System.out.println("Value: " + myHashTable.get("bolts"));
        System.out.println("Value: " + myHashTable.get("lumbar"));
        System.out.println("Value: " + myHashTable.get("screws"));
        System.out.println("Value: " + myHashTable.get("tile"));

        System.out.println("keys: " + myHashTable.keys());


        int[] array1 = {1, 3, 5};
        int[] array2 = {2, 4, 5};

        System.out.println(itemInCommon(array1, array2));

    }

    public static boolean itemInCommonUisngHashMap(int[] array1, int[] array2) {
        HashMap hm = new HashMap<String, Boolean>();
        for (int i : array1) {
            hm.put(i, true);
        }
        for (int j : array2) {
            if (hm.get(j) != null) {
                return true;
            }
        }
        return false;
    }

    public static boolean itemInCommon(int[] array1, int[] array2) {
        HashSet hs = new HashSet<Integer>();
        for (int i : array1) {
            hs.add(i);
        }
        for (int j : array2) {
            if (hs.contains(j)) {
                return true;
            }
        }
        return false;
    }

    public static List<Integer> findDuplicatesUsingHashMap(int[] array) {
        List<Integer> duplicates = new ArrayList<>();
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i : array) {
            int count = hm.getOrDefault(i, 0);
            count++;
            hm.put(i, count);
        }
        for (Map.Entry<Integer, Integer> entrySet : hm.entrySet()) {
            if (entrySet.getValue() > 1) {
                duplicates.add(entrySet.getKey());
            }
        }
        return duplicates;
    }

    public static List<Integer> findDuplicatesUsingSet(int[] array) {
        Set<Integer> duplicates = new LinkedHashSet<>();
        Set<Integer> tempSet = new HashSet<>();
        for (int i : array) {
            boolean ableToAdd = tempSet.add(i);
            if (!ableToAdd) {
                duplicates.add(i);
            }
        }
        return new ArrayList<>(duplicates);
    }

    public static Character firstNonRepeatingChar(String s) {
        HashMap<Character, Integer> hm = new LinkedHashMap<>();
        char[] charArray = s.toCharArray();
        for (char c : charArray) {
//            Integer count = hm.getOrDefault(c, 0);
//            count++;
//            hm.put(c, count);

            hm.merge(c, 1, (existingValue, newValue) -> existingValue + newValue); // newValue would always be 1 as mentioned in 2nd argument of merge method

        }
        for (Map.Entry<Character, Integer> entrySet : hm.entrySet()) {
            if (entrySet.getValue() == 1) {
                return entrySet.getKey();
            }
        }
        return null;

    }

    public static List<List<String>> groupAnagrams(String[] strings) {
        HashMap<String, List<String>> hm = new HashMap<>();
        for (String s : strings) {
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String canonical = new String(charArray);


//            List<String> tempList = hm.get(canonical);
//            if (tempList == null) {
//                tempList = new ArrayList<>();
//            }
//            tempList.add(s);
//            hm.put(canonical, tempList);
//
            if (hm.containsKey(canonical)) {
                hm.get(canonical).add(s);
            } else {
                List<String> groupList = new ArrayList<>();
                groupList.add(s);
                hm.put(canonical, groupList);
            }
        }

//        List<List<String>> anagramList = new ArrayList<>();
//        for(Map.Entry<String, List<String>> entrySet : hm.entrySet()){
//            anagramList.add(entrySet.getValue());
//        }
//        return anagramList;
        return new ArrayList<>(hm.values());

    }

    public static int[] twoSum(int[] array, int target) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int key = array[i];
            int difference = target - key;
            if (hm.containsKey(difference)) {
                return new int[]{hm.get(difference), i};
            }
            hm.put(key, i);

        }
        return new int[]{};

    }

    public static int[] subarraySum(int[] nums, int target) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.put(0, -1);
        int currentSum = 0;
        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            if (hm.containsKey(currentSum - target)) {
                return new int[]{hm.get(currentSum - target) + 1, i};
            }
            hm.put(currentSum, i);
        }
        return new int[]{};
    }
}
