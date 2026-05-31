package com.practice.dsa.mylinkedlist;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LinkedList.binaryToDecimal()")
class LinkedListBinaryToDecimalTest {

    @Test
    @DisplayName("empty list -> 0")
    void emptyList_returnsZero() {
        LinkedList list = new LinkedList(1);
        list.makeEmpty();
        assertEquals(0, list.binaryToDecimal());
    }

    @Test
    @DisplayName("single node (0) -> 0")
    void singleNode_zero_returnsZero() {
        LinkedList list = new LinkedList(0);
        assertEquals(0, list.binaryToDecimal());
    }

    @Test
    @DisplayName("single node (1) -> 1")
    void singleNode_one_returnsOne() {
        LinkedList list = new LinkedList(1);
        assertEquals(1, list.binaryToDecimal());
    }

    @Test
    @DisplayName("101 -> 5")
    void multiNode_101_returnsFive() {
        LinkedList list = new LinkedList(1);
        list.append(0);
        list.append(1);
        assertEquals(5, list.binaryToDecimal());
    }

    @Test
    @DisplayName("1111 -> 15")
    void multiNode_1111_returnsFifteen() {
        LinkedList list = new LinkedList(1);
        list.append(1);
        list.append(1);
        list.append(1);
        assertEquals(15, list.binaryToDecimal());
    }

    @Test
    @DisplayName("10010 -> 18")
    void multiNode_10010_returnsEighteen() {
        LinkedList list = new LinkedList(1);
        list.append(0);
        list.append(0);
        list.append(1);
        list.append(0);
        assertEquals(18, list.binaryToDecimal());
    }
}
