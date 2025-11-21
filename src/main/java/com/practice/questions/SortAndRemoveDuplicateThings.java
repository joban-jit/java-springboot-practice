package com.practice.questions;

import java.sql.Array;
import java.util.*;

public class SortAndRemoveDuplicateThings {

    public static void main(String[] args) {
        sortThings();
//        removeDuplicateThings();

    }

    private static void removeDuplicateThings() {
        List<Integer> list = new ArrayList<>(Arrays.asList(
                1, 12, 34, 2, 45, 2, 55, 56, 23, 12, 55
        ));

        // Using stream api - distinct() -  preserves order
        List<Integer> list1 = list.stream()
                .distinct()
                .toList();
        System.out.println(list1); //[1, 12, 34, 2, 45, 55, 56, 23]

        List<Integer> list2 = list.stream()
                .distinct()
                .sorted(
//                        Comparator.reverseOrder() // if you use this: then output is: [56, 55, 45, 34, 23, 12, 2, 1]
                )
                .toList();
        System.out.println(list2); //[1, 2, 12, 23, 34, 45, 55, 56]

        // Using LinkedHashSet - to preserve order
        List<Integer> list3 = new ArrayList<>(new LinkedHashSet<>(list));
        System.out.println(list3); //[1, 12, 34, 2, 45, 55, 56, 23]

        // to remove duplicate to existing list
        // here set.add(i) will return true if element exist
        HashSet<Integer> set1 = new HashSet<>();
        list.removeIf(i->!set1.add(i));
        System.out.println(list); //[1, 12, 34, 2, 45, 55, 56, 23]

    }

    private static void sortThings() {
        // For array
        Integer[] arr= {1, 12, 34, 2, 45, 2, 55, 56, 23};
        Arrays.sort(arr, Comparator.reverseOrder());
        System.out.println(Arrays.toString(arr));
        List<Integer> l1 = new ArrayList<>(Arrays.asList(1,12,34,2,45,2,55,56,23));



        //Sort a mutable list in place (ascending) - same list
//        Collections.sort(l1);
//        System.out.println(l1); //[1, 2, 2, 12, 23, 34, 45, 55, 56]
        // modern equivalent of above - same list
//        l1.sort(Comparator.naturalOrder());
//        System.out.println(l1); //[1, 2, 2, 12, 23, 34, 45, 55, 56]
        // sort a list in descending order
//        l1.sort(Comparator.reverseOrder()); //[56, 55, 45, 34, 23, 12, 2, 2, 1]
//        System.out.println(l1);

        // Sort without modifying the original list(Streams) - new list
//        List<Integer> sortedL1 = l1.stream().sorted().toList();
//
//        System.out.println(sortedL1); //[1, 2, 2, 12, 23, 34, 45, 55, 56]
        List<Integer> sortedL2 = l1.stream().sorted(Comparator.reverseOrder()).toList();

        System.out.println(sortedL2); //[56, 55, 45, 34, 23, 12, 2, 2, 1]

        // to know more on use of Comparator check: ComparatorPractice java class



    }
}
