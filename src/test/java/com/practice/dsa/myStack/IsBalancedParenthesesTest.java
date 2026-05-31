package com.practice.dsa.myStack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IsBalancedParenthesesTest {

    // Test 1: Empty string
    @Test
    void isBalancedParentheses_emptyString_returnsTrue() {
        assertTrue(MyStackWithArrayListMain.isBalancedParentheses(""));
    }

    // Test 2: Single pair
    @Test
    void isBalancedParentheses_singlePair_returnsTrue() {
        assertTrue(MyStackWithArrayListMain.isBalancedParentheses("()"));
    }

    // Test 3: Missing open bracket
    @Test
    void isBalancedParentheses_missingOpen_returnsFalse() {
        assertFalse(MyStackWithArrayListMain.isBalancedParentheses(")"));
    }

    // Test 4: Missing close bracket
    @Test
    void isBalancedParentheses_missingClose_returnsFalse() {
        assertFalse(MyStackWithArrayListMain.isBalancedParentheses("("));
    }

    // Test 5: Multiple pairs
    @Test
    void isBalancedParentheses_multiplePairs_returnsTrue() {
        assertTrue(MyStackWithArrayListMain.isBalancedParentheses("()()"));
    }

    // Test 6: Nested balanced
    @Test
    void isBalancedParentheses_nestedBalanced_returnsTrue() {
        assertTrue(MyStackWithArrayListMain.isBalancedParentheses("(())"));
    }

    // Test 7: Nested unbalanced
    @Test
    void isBalancedParentheses_nestedUnbalanced_returnsFalse() {
        assertFalse(MyStackWithArrayListMain.isBalancedParentheses("(()"));
    }

    // Test 8: Complex balanced
    @Test
    void isBalancedParentheses_complexBalanced_returnsTrue() {
        assertTrue(MyStackWithArrayListMain.isBalancedParentheses("(()())()"));
    }

    // Test 9: Complex unbalanced
    @Test
    void isBalancedParentheses_complexUnbalanced_returnsFalse() {
        assertFalse(MyStackWithArrayListMain.isBalancedParentheses("())(()"));
    }
}
