package com.practice.dsa.myStack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReverseTest {

    // Test 1: Normal word
    @Test
    void reverse_normalWord_returnsReversed() {
        assertEquals("olleh", MyStackWithArrayListMain.reverse("hello"));
    }

    // Test 2: Empty string
    @Test
    void reverse_emptyString_returnsEmpty() {
        assertEquals("", MyStackWithArrayListMain.reverse(""));
    }

    // Test 3: Single character
    @Test
    void reverse_singleCharacter_returnsUnchanged() {
        assertEquals("A", MyStackWithArrayListMain.reverse("A"));
    }

    // Test 4: Palindrome
    @Test
    void reverse_palindrome_returnsUnchanged() {
        assertEquals("madam", MyStackWithArrayListMain.reverse("madam"));
    }

    // Test 5: Spaces and symbols
    @Test
    void reverse_spacesAndSymbols_returnsReversed() {
        assertEquals("! cba", MyStackWithArrayListMain.reverse("abc !"));
    }
}
