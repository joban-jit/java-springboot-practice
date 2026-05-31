package com.practice.dsa.myStack;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortStackTest {

    private List<Integer> drainStack(MyStackWithArrayList<Integer> stack) {
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }

    // Test 1: Empty stack
    @Test
    void sortStack_emptyStack_remainsEmpty() {
        MyStackWithArrayList<Integer> stack = new MyStackWithArrayList<>();
        MyStackWithArrayListMain.sortStack(stack);
        assertTrue(stack.isEmpty());
    }

    // Test 2: Single element
    @Test
    void sortStack_singleElement_returnsUnchanged() {
        MyStackWithArrayList<Integer> stack = new MyStackWithArrayList<>();
        stack.push(5);
        MyStackWithArrayListMain.sortStack(stack);
        assertEquals(List.of(5), drainStack(stack));
    }

    // Test 3: Unsorted stack — top should be lowest
    @Test
    void sortStack_unsortedStack_sortsAscendingTopToBottom() {
        MyStackWithArrayList<Integer> stack = new MyStackWithArrayList<>();
        stack.push(3);
        stack.push(1);
        stack.push(4);
        stack.push(2);
        MyStackWithArrayListMain.sortStack(stack);
        assertEquals(List.of(1, 2, 3, 4), drainStack(stack));
    }

    // Test 4: Already sorted (smallest on top)
    @Test
    void sortStack_alreadySorted_remainsSorted() {
        MyStackWithArrayList<Integer> stack = new MyStackWithArrayList<>();
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        MyStackWithArrayListMain.sortStack(stack);
        assertEquals(List.of(1, 2, 3, 4), drainStack(stack));
    }

    // Test 5: Reverse sorted (largest on top)
    @Test
    void sortStack_reverseSorted_sortsAscendingTopToBottom() {
        MyStackWithArrayList<Integer> stack = new MyStackWithArrayList<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        MyStackWithArrayListMain.sortStack(stack);
        assertEquals(List.of(1, 2, 3, 4), drainStack(stack));
    }

    // Test 6: With duplicates
    @Test
    void sortStack_withDuplicates_sortsCorrectly() {
        MyStackWithArrayList<Integer> stack = new MyStackWithArrayList<>();
        stack.push(3);
        stack.push(1);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        MyStackWithArrayListMain.sortStack(stack);
        assertEquals(List.of(1, 1, 2, 3, 3), drainStack(stack));
    }

    // Test 7: With negatives
    @Test
    void sortStack_withNegatives_sortsCorrectly() {
        MyStackWithArrayList<Integer> stack = new MyStackWithArrayList<>();
        stack.push(-1);
        stack.push(3);
        stack.push(-5);
        stack.push(2);
        MyStackWithArrayListMain.sortStack(stack);
        assertEquals(List.of(-5, -1, 2, 3), drainStack(stack));
    }
}
