package com.practice.questions;

import com.sun.source.tree.Tree;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArrayThings {

    public static void main(String[] args) {

        //checkIfTwoArrayContainTheSameElements
        int[] a1 = {1, 2, 3, 2, 1};
        int[] a2 = {1, 2, 3};
        int[] a3 = {1, 4, 3, 2};
        int[] a4 = {1, 2, 3, 4};
        checkIfTwoArrayContainTheSameElements(a1, a2);
        checkIfTwoArrayContainTheSameElements(a4, a3);

        // get sum of all elements in an integer array in java
        int[] array = {1, 2, 3, 4, 5};
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
        findMissingNumberInAnNaturalNumbersArray();
        int[] num1 = {1, 2, 3, 3};
        System.out.println(hasDuplicate(num1));
        int[] num2 = {1, 2, 3, 4};
        System.out.println(hasDuplicate(num2));

//       Arrays.stream(twoSum(num1, 4)).forEach(System.out::println);
//        Arrays.stream(twoSum(num2, 7)).forEach(System.out::println);
        groupAnagrams(new String[]{"act", "pots", "tops", "cat", "stop", "hat"});
        Arrays.stream(topKFrequent(new int[]{1, 2, 2, 3, 3, 3}, 2)).forEach(System.out::println);
        Arrays.stream(topKFrequent(new int[]{7, 7}, 1)).forEach(System.out::println);
        System.out.println(maxProfit(new int[]{10, 1, 5, 6, 7, 1}));
        System.out.println(areBracketsValid("([{}])"));

    }

    private static void shuffleAnArray() {
        int[] array1 = {1, 2, 3, 4, 5, 6, 7};
        Random rand = new Random();
        for (int i = 0; i < array1.length; i++) {
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

    private static void secondLargestUsingSort(int[] arr) {
        int[] array = Arrays.stream(arr)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
        System.out.println(array[1]); // 2nd largest element
    }

    private static void secondLargestUsingSortManually(int[] arr) {
        int firstHighestValue = Integer.MIN_VALUE, secondHighestValue = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > firstHighestValue) {
                secondHighestValue = firstHighestValue;
                firstHighestValue = num;
            } else if (num > secondHighestValue && num != firstHighestValue) {
                // this num!=first condition is used to handle duplicates largest number
                secondHighestValue = num;
            }
        }
        System.out.println("First Largest: " + firstHighestValue);
        System.out.println("Second Largest: " + secondHighestValue);
    }

    private static void minMaxInArray() {
        int[] arr = {123, 234, 1121, 1, 432};
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().orElseThrow();
        System.out.println("Max " + max + " Min: " + min);
    }

    private static int getSumOfAllElementsInAnIntegerArray(int[] array) {
        return Arrays.stream(array)
                .sum();
    }

    private static void checkIfTwoArrayContainTheSameElements(int[] ar1, int[] ar2) {

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

    private static boolean hasDuplicate(int[] nums) {
//        Set<Integer> set1 = new HashSet<>();
//        for(int num : nums){
//            // If add() returns false, element already exists
//            if(!set1.add(num)){
//                return true;
//            }
//        }
//        return false;

        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        return false;
    }

    private static void findLargestInArray() {
        int[] arr = {10, 20, 4, 100, 99};
//        int largest = Integer.MIN_VALUE; // use this if you are not sure array is empty or not
        int largest = arr[0];
        for (int num : arr) {
            if (num > largest) {
                largest = num;
            }
        }
        System.out.println(largest);
    }

    private static void findMissingNumberInAnNaturalNumbersArray() {
        int[] nums = {1, 2, 3, 4, 6, 7, 8, 9, 10};
        int missingNumber = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 != nums[i]) {
                missingNumber = i + 1;
                break;
            }
        }

        System.out.println("Missing number is: " + missingNumber);


    }

    private static int[] twoSum(int[] nums, int target) {

        //create Map to store K -> V pairs, where K is compliment -> V is index
        Map<Integer, Integer> compliment = new HashMap<>();


        //For loop to iterate through the array once
        for (int i = 0; i < nums.length; i++) {
            //formula to find the difference
            int difference = target - nums[i];
            //check if difference exists in Hashmap, if so, return compliment + index (ie answer)
            if (compliment.containsKey(difference)) {
                return new int[]{compliment.get(difference), i};
            }
            //if compliment doesn't exist, add element value as key and its index as value
            compliment.put(nums[i], i);
        }
        return new int[]{}; //this will not be executed, as solution is guarenteed by PS.
    }

    private static List<List<String>> groupAnagrams(String[] strs) {


        Map<String, List<String>> res = new HashMap<>();
        for (String s : strs) {
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String sortedS = new String(charArray);
            res.putIfAbsent(sortedS, new ArrayList<>());
            res.get(sortedS).add(s);
        }

        return new ArrayList<>(res.values());

    }

    private static int[] topKFrequent(int[] nums, int k) {
//        return Arrays.stream(nums)
//                .boxed()
//                .collect(Collectors.groupingBy(
//                        i -> i,
//                        Collectors.counting()
//
//                ))
//                .entrySet()
//                .stream()
//                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
//                .limit(k)
//                .mapToInt(Map.Entry::getKey)
//                .toArray();

        // count frequency
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) {

//            freq.put(n, freq.getOrDefault(n,0)+1);
            // we can replace above with merge method
//            freq.merge(n, 1, (oldVal, newVal) -> oldVal + newVal); // newVal is always 1
            freq.merge(n, 1, Integer::sum);

        }
        // Min heap based on frequency
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(freq::get));

        // keep only top k elements
        for (int n : freq.keySet()) {
            pq.offer(n);
            if (pq.size() > k) { // to avoid having more elements in our queue than needed
                pq.poll(); // this one will remove the smallest elements which we don't need
            }
        }
        // Build result
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = pq.poll();
        }
        return result;


    }

    /*
    You are given an integer array prices where prices[i] is the price of NeetCoin on the ith day.
    You may choose a single day to buy one NeetCoin and choose a different day in the future to sell it.
    Return the maximum profit you can achieve. You may choose to not make any transactions, in which case the profit would be 0.
     */
    private static int maxProfit(int[] prices) {
        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;// or max in given constraint

        for (int price : prices) {
            // update minimum price seen so far
            if (price < minPrice) {
                minPrice = price;
            } else {
                // calculate profit if sold today
                int profit = price - minPrice;
                maxProfit = Math.max(maxProfit, profit);
            }
        }


        return maxProfit;

    }

    private static boolean areBracketsValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        // here in this map we are adding only closing brackets in key
        Map<Character, Character> closeToOpen = new HashMap<>();
        closeToOpen.put(')', '(');
        closeToOpen.put(']', '[');
        closeToOpen.put('}', '{');


        for(char c: s.toCharArray()){
            if(closeToOpen.containsKey(c)){ // as this map only contains closing brackets so using else statement : stack.push(c);
                // / in stack we are adding opening brackets
                if(!stack.isEmpty() && stack.peek()==closeToOpen.get(c)){
                    stack.pop();
                }else{
                    return false;
                }

            }else{
                stack.push(c);
            }
        }
        return stack.isEmpty();

    }

}

