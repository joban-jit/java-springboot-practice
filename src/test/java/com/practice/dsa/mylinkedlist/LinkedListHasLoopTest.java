package com.practice.dsa.mylinkedlist;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LinkedList.hasLoop()")
class LinkedListHasLoopTest {

    @Test
    @DisplayName("empty list -> false")
    void emptyList_returnsFalse() {
        LinkedList list = new LinkedList(1);
        list.makeEmpty();
        assertFalse(list.hasLoop());
    }

    @Test
    @DisplayName("single node, no loop -> false")
    void singleNode_noLoop_returnsFalse() {
        LinkedList list = new LinkedList(1);
        assertFalse(list.hasLoop());
    }

    @Test
    @DisplayName("single node loops to itself -> true")
    void singleNode_selfLoop_returnsTrue() {
        LinkedList list = new LinkedList(1);
        list.getHead().next = list.getHead();
        assertTrue(list.hasLoop());
    }

    @Test
    @DisplayName("multi-node list, no loop -> false")
    void multiNode_noLoop_returnsFalse() {
        LinkedList list = new LinkedList(1);
        list.append(2);
        list.append(3);
        list.append(4);
        assertFalse(list.hasLoop());
    }

    @Test
    @DisplayName("multi-node list, tail loops back to head -> true")
    void multiNode_tailLoopsToHead_returnsTrue() {
        LinkedList list = new LinkedList(1);
        list.append(2);
        list.append(3);
        list.append(4);
        list.getTail().next = list.getHead();
        assertTrue(list.hasLoop());
    }

    @Test
    @DisplayName("multi-node list, tail loops to middle node -> true")
    void multiNode_tailLoopsToMiddle_returnsTrue() {
        LinkedList list = new LinkedList(1);
        list.append(2);
        list.append(3);
        list.append(4);
        LinkedList.Node node3 = list.getHead().next.next;  // value = 3
        list.getTail().next = node3;
        assertTrue(list.hasLoop());
    }
}