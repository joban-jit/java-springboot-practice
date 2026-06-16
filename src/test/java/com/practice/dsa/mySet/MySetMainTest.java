package com.practice.dsa.mySet;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MySetMainTest {

    // ──────────────────────────────────────────────
    // removeDuplicates
    // ──────────────────────────────────────────────

    @Test
    void removeDuplicates_withDuplicates_returnsDistinctElements() {
        List<Integer> result = MySetMain.removeDuplicates(Arrays.asList(1, 2, 2, 3, 3, 3));
        assertEquals(3, result.size());
        assertTrue(result.containsAll(Arrays.asList(1, 2, 3)));
    }

    @Test
    void removeDuplicates_noDuplicates_returnsSameElements() {
        List<Integer> result = MySetMain.removeDuplicates(Arrays.asList(1, 2, 3));
        assertEquals(3, result.size());
        assertTrue(result.containsAll(Arrays.asList(1, 2, 3)));
    }

    @Test
    void removeDuplicates_emptyList_returnsEmptyList() {
        List<Integer> result = MySetMain.removeDuplicates(Arrays.asList());
        assertTrue(result.isEmpty());
    }

    // ──────────────────────────────────────────────
    // hasUniqueChars
    // ──────────────────────────────────────────────

    @Test
    void hasUniqueChars_allUnique_returnsTrue() {
        assertTrue(MySetMain.hasUniqueChars("abcde"));
    }

    @Test
    void hasUniqueChars_withRepeatedChar_returnsFalse() {
        assertFalse(MySetMain.hasUniqueChars("hello"));
    }

    @Test
    void hasUniqueChars_emptyString_returnsTrue() {
        assertTrue(MySetMain.hasUniqueChars(""));
    }

    // ──────────────────────────────────────────────
    // findPairs
    // ──────────────────────────────────────────────

    @Test
    void findPairs_withMatchingPairs_returnsPairs() {
        List<int[]> result = MySetMain.findPairs(new int[]{1, 2, 3}, new int[]{4, 5, 6}, 7);
        assertEquals(3, result.size());
        assertArrayEquals(new int[]{1, 6}, result.get(0));
        assertArrayEquals(new int[]{2, 5}, result.get(1));
        assertArrayEquals(new int[]{3, 4}, result.get(2));
    }

    @Test
    void findPairs_noMatchingPairs_returnsEmptyList() {
        List<int[]> result = MySetMain.findPairs(new int[]{1, 2}, new int[]{10, 20}, 7);
        assertTrue(result.isEmpty());
    }

    @Test
    void findPairs_emptyArrays_returnsEmptyList() {
        List<int[]> result = MySetMain.findPairs(new int[]{}, new int[]{}, 7);
        assertTrue(result.isEmpty());
    }

    // ──────────────────────────────────────────────
    // longestConsecutiveSequence
    // ──────────────────────────────────────────────

    @Test
    void longestConsecutiveSequence_typicalInput_returnsLongestStreak() {
        assertEquals(4, MySetMain.longestConsecutiveSequence(new int[]{100, 4, 200, 1, 3, 2}));
    }


    @Test
    void longestConsecutiveSequence_emptyArray_returnsZero() {
        assertEquals(0, MySetMain.longestConsecutiveSequence(new int[]{}));
    }

    @Test
    void longestConsecutiveSequence_singleElement_returnsOne() {
        assertEquals(1, MySetMain.longestConsecutiveSequence(new int[]{5}));
    }

    @Test
    void longestConsecutiveSequence_allSameElement_returnsOne() {
        assertEquals(1, MySetMain.longestConsecutiveSequence(new int[]{1, 1, 1, 1}));
    }
}
