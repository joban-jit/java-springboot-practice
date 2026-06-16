package com.practice.dsa.myHashTable;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyHashTableMainTest {

    // ──────────────────────────────────────────────
    // itemInCommonUsingHashMap
    // ──────────────────────────────────────────────

    @Test
    void itemInCommonUsingHashMap_commonElement_returnsTrue() {
        assertTrue(MyHashTableMain.itemInCommonUisngHashMap(new int[]{1, 3, 5}, new int[]{2, 4, 5}));
    }

    @Test
    void itemInCommonUsingHashMap_noCommonElement_returnsFalse() {
        assertFalse(MyHashTableMain.itemInCommonUisngHashMap(new int[]{1, 2, 3}, new int[]{4, 5, 6}));
    }

    @Test
    void itemInCommonUsingHashMap_emptyArrays_returnsFalse() {
        assertFalse(MyHashTableMain.itemInCommonUisngHashMap(new int[]{}, new int[]{}));
    }

    @Test
    void itemInCommonUsingHashMap_oneEmptyArray_returnsFalse() {
        assertFalse(MyHashTableMain.itemInCommonUisngHashMap(new int[]{1, 2}, new int[]{}));
    }

    // ──────────────────────────────────────────────
    // itemInCommon (HashSet)
    // ──────────────────────────────────────────────

    @Test
    void itemInCommon_commonElement_returnsTrue() {
        assertTrue(MyHashTableMain.itemInCommon(new int[]{1, 3, 5}, new int[]{2, 4, 5}));
    }

    @Test
    void itemInCommon_noCommonElement_returnsFalse() {
        assertFalse(MyHashTableMain.itemInCommon(new int[]{1, 2, 3}, new int[]{4, 5, 6}));
    }

    @Test
    void itemInCommon_emptyArrays_returnsFalse() {
        assertFalse(MyHashTableMain.itemInCommon(new int[]{}, new int[]{}));
    }

    @Test
    void itemInCommon_singleMatchingElement_returnsTrue() {
        assertTrue(MyHashTableMain.itemInCommon(new int[]{7}, new int[]{7}));
    }

    // ──────────────────────────────────────────────
    // findDuplicatesUsingHashMap
    // ──────────────────────────────────────────────

    @Test
    void findDuplicatesUsingHashMap_withDuplicates_returnsDuplicateValues() {
        List<Integer> result = MyHashTableMain.findDuplicatesUsingHashMap(new int[]{1, 2, 3, 2, 4, 3});
        List<Integer> sorted = result.stream().sorted().toList();
        assertEquals(List.of(2, 3), sorted);
    }

    @Test
    void findDuplicatesUsingHashMap_noDuplicates_returnsEmptyList() {
        assertTrue(MyHashTableMain.findDuplicatesUsingHashMap(new int[]{1, 2, 3}).isEmpty());
    }

    @Test
    void findDuplicatesUsingHashMap_emptyArray_returnsEmptyList() {
        assertTrue(MyHashTableMain.findDuplicatesUsingHashMap(new int[]{}).isEmpty());
    }

    @Test
    void findDuplicatesUsingHashMap_allSameElement_returnsOneEntry() {
        List<Integer> result = MyHashTableMain.findDuplicatesUsingHashMap(new int[]{5, 5, 5});
        assertEquals(List.of(5), result);
    }

    // ──────────────────────────────────────────────
    // findDuplicatesUsingSet
    // ──────────────────────────────────────────────

    @Test
    void findDuplicatesUsingSet_withDuplicates_returnsDuplicatesInOrder() {
        List<Integer> result = MyHashTableMain.findDuplicatesUsingSet(new int[]{1, 2, 3, 2, 1});
        assertEquals(List.of(2, 1), result);
    }

    @Test
    void findDuplicatesUsingSet_noDuplicates_returnsEmptyList() {
        assertTrue(MyHashTableMain.findDuplicatesUsingSet(new int[]{1, 2, 3}).isEmpty());
    }

    @Test
    void findDuplicatesUsingSet_emptyArray_returnsEmptyList() {
        assertTrue(MyHashTableMain.findDuplicatesUsingSet(new int[]{}).isEmpty());
    }

    @Test
    void findDuplicatesUsingSet_singleDuplicate_returnsOneEntry() {
        assertEquals(List.of(4), MyHashTableMain.findDuplicatesUsingSet(new int[]{4, 4}));
    }

    // ──────────────────────────────────────────────
    // firstNonRepeatingChar
    // ──────────────────────────────────────────────

    @Test
    void firstNonRepeatingChar_firstCharIsUnique_returnsFirstChar() {
        assertEquals('d', MyHashTableMain.firstNonRepeatingChar("dbbca"));
    }

    @Test
    void firstNonRepeatingChar_uniqueCharInMiddle_returnsIt() {
        assertEquals('l', MyHashTableMain.firstNonRepeatingChar("aabblcc"));
    }

    @Test
    void firstNonRepeatingChar_allRepeating_returnsNull() {
        assertNull(MyHashTableMain.firstNonRepeatingChar("aabb"));
    }

    @Test
    void firstNonRepeatingChar_singleChar_returnsThatChar() {
        assertEquals('z', MyHashTableMain.firstNonRepeatingChar("z"));
    }

    @Test
    void firstNonRepeatingChar_lastCharIsUnique_returnsIt() {
        assertEquals('z', MyHashTableMain.firstNonRepeatingChar("aabbz"));
    }

    // ──────────────────────────────────────────────
    // groupAnagrams
    // ──────────────────────────────────────────────

    @Test
    void groupAnagrams_typicalInput_groupsCorrectly() {
        String[] input = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> result = MyHashTableMain.groupAnagrams(input);
        assertEquals(3, result.size());
    }

    @Test
    void groupAnagrams_noAnagrams_eachInOwnGroup() {
        String[] input = {"abc", "def", "ghi"};
        List<List<String>> result = MyHashTableMain.groupAnagrams(input);
        assertEquals(3, result.size());
        result.forEach(group -> assertEquals(1, group.size()));
    }

    @Test
    void groupAnagrams_allSameAnagram_oneGroup() {
        String[] input = {"abc", "bca", "cab"};
        List<List<String>> result = MyHashTableMain.groupAnagrams(input);
        assertEquals(1, result.size());
        assertEquals(3, result.get(0).size());
    }

    @Test
    void groupAnagrams_emptyArray_returnsEmptyList() {
        assertTrue(MyHashTableMain.groupAnagrams(new String[]{}).isEmpty());
    }

    // ──────────────────────────────────────────────
    // twoSum
    // ──────────────────────────────────────────────

    @Test
    void twoSum_basicCase_returnsCorrectIndices() {
        assertArrayEquals(new int[]{0, 1}, MyHashTableMain.twoSum(new int[]{2, 7, 11, 15}, 9));
    }

    @Test
    void twoSum_answerAtEnd_returnsCorrectIndices() {
        assertArrayEquals(new int[]{1, 3}, MyHashTableMain.twoSum(new int[]{3, 2, 4, 7}, 9));
    }

    @Test
    void twoSum_withNegativeNumbers_returnsCorrectIndices() {
        assertArrayEquals(new int[]{0, 2}, MyHashTableMain.twoSum(new int[]{-3, 1, 3}, 0));
    }

    @Test
    void twoSum_noSolution_returnsEmptyArray() {
        assertArrayEquals(new int[]{}, MyHashTableMain.twoSum(new int[]{1, 2, 3}, 10));
    }

    // ──────────────────────────────────────────────
    // subarraySum
    // ──────────────────────────────────────────────

    @Test
    void subarraySum_subarrayInMiddle_returnsStartAndEndIndices() {
        // [1,2,3,4,5] — subarray [2,3,4] sums to 9, indices [1,3]
        assertArrayEquals(new int[]{1, 3}, MyHashTableMain.subarraySum(new int[]{1, 2, 3, 4, 5}, 9));
    }

    @Test
    void subarraySum_subarrayStartsAtZero_returnsCorrectIndices() {
        // [1,2,3] — subarray [1,2,3] sums to 6, indices [0,2]
        assertArrayEquals(new int[]{0, 2}, MyHashTableMain.subarraySum(new int[]{1, 2, 3}, 6));
    }

    @Test
    void subarraySum_singleElementEqualsTarget_returnsThatIndex() {
        assertArrayEquals(new int[]{2, 2}, MyHashTableMain.subarraySum(new int[]{1, 3, 5, 7}, 5));
    }

    @Test
    void subarraySum_noSubarrayFound_returnsEmptyArray() {
        assertArrayEquals(new int[]{}, MyHashTableMain.subarraySum(new int[]{1, 2, 3}, 100));
    }
}
