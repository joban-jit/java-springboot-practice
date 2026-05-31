package com.practice.dsa.myDoublylinkedlist;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DoublyLinkedList.isPalindrome()")
class DoublyLinkedListIsPalindromeTest {

    @Test
    @DisplayName("empty list -> true")
    void emptyList_returnsTrue() {
        DoublyLinkedList list = new DoublyLinkedList(1);
        list.makeEmpty();
        assertTrue(list.isPalindrome());
    }

    @Test
    @DisplayName("single node -> true")
    void singleNode_returnsTrue() {
        DoublyLinkedList list = new DoublyLinkedList(10);
        assertTrue(list.isPalindrome());
    }

    @Test
    @DisplayName("two nodes, same value -> true")
    void twoNodes_palindrome_returnsTrue() {
        DoublyLinkedList list = new DoublyLinkedList(5);
        list.append(5);
        assertTrue(list.isPalindrome());
    }

    @Test
    @DisplayName("two nodes, different values -> false")
    void twoNodes_notPalindrome_returnsFalse() {
        DoublyLinkedList list = new DoublyLinkedList(5);
        list.append(7);
        assertFalse(list.isPalindrome());
    }

    @Test
    @DisplayName("odd-length palindrome (1,2,3,2,1) -> true")
    void oddLength_palindrome_returnsTrue() {
        DoublyLinkedList list = new DoublyLinkedList(1);
        list.append(2);
        list.append(3);
        list.append(2);
        list.append(1);
        assertTrue(list.isPalindrome());
    }

    @Test
    @DisplayName("even-length palindrome (1,2,2,1) -> true")
    void evenLength_palindrome_returnsTrue() {
        DoublyLinkedList list = new DoublyLinkedList(1);
        list.append(2);
        list.append(2);
        list.append(1);
        assertTrue(list.isPalindrome());
    }

    @Test
    @DisplayName("odd-length non-palindrome (1,2,3,4,1) -> false")
    void oddLength_notPalindrome_returnsFalse() {
        DoublyLinkedList list = new DoublyLinkedList(1);
        list.append(2);
        list.append(3);
        list.append(4);
        list.append(1);
        assertFalse(list.isPalindrome());
    }

    @Test
    @DisplayName("even-length non-palindrome (1,2,3,4) -> false")
    void evenLength_notPalindrome_returnsFalse() {
        DoublyLinkedList list = new DoublyLinkedList(1);
        list.append(2);
        list.append(3);
        list.append(4);
        assertFalse(list.isPalindrome());
    }
}
