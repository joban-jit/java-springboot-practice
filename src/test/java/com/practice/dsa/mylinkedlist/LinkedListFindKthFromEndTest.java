package com.practice.dsa.mylinkedlist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListFindKthFromEndTest {

    // Test 1: Empty list, k = 1
    @Test
    void findKthFromEnd_emptyList_returnsNull() {
        LinkedList list = new LinkedList(1);
        list.makeEmpty();
        assertNull(list.findKthFromEnd(1));
    }

    // Test 2: One element, k = 1 (last node)
    @Test
    void findKthFromEnd_oneElement_kEqualsOne_returnsTheOnlyNode() {
        LinkedList list = new LinkedList(10);
        LinkedList.Node result = list.findKthFromEnd(1);
        assertNotNull(result);
        assertEquals(10, result.value);
    }

    // Test 3: One element, k = 2 (beyond end)
    @Test
    void findKthFromEnd_oneElement_kBeyondLength_returnsNull() {
        LinkedList list = new LinkedList(20);
        assertNull(list.findKthFromEnd(2));
    }

    // Test 4: Multi-node list (1->2->3->4->5->6), k = 1 (last node)
    @Test
    void findKthFromEnd_multiNode_kEqualsOne_returnsLastNode() {
        LinkedList list = buildList(1, 6);
        LinkedList.Node result = list.findKthFromEnd(1);
        assertNotNull(result);
        assertEquals(6, result.value);
    }

    // Test 5: Multi-node list (1->2->3->4->5->6), k = 2 (second-to-last)
    @Test
    void findKthFromEnd_multiNode_kEqualsTwo_returnsSecondToLastNode() {
        LinkedList list = buildList(1, 6);
        LinkedList.Node result = list.findKthFromEnd(2);
        assertNotNull(result);
        assertEquals(5, result.value);
    }

    // Test 6: Multi-node list (1->2->3->4->5->6), k = 7 (k too large)
    @Test
    void findKthFromEnd_multiNode_kLargerThanLength_returnsNull() {
        LinkedList list = buildList(1, 6);
        assertNull(list.findKthFromEnd(7));
    }

    // Test 7: Multi-node list (1->2->3->4->5->6), k = 4 (middle node)
    @Test
    void findKthFromEnd_multiNode_kEqualsForur_returnsMiddleNode() {
        LinkedList list = buildList(1, 6);
        LinkedList.Node result = list.findKthFromEnd(4);
        assertNotNull(result);
        assertEquals(3, result.value);
    }

    private LinkedList buildList(int from, int to) {
        LinkedList list = new LinkedList(from);
        for (int i = from + 1; i <= to; i++) {
            list.append(i);
        }
        return list;
    }
}