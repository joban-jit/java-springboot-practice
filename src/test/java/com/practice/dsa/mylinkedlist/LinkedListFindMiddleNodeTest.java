package com.practice.dsa.mylinkedlist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListFindMiddleNodeTest {

    // Test 1: Empty list
    @Test
    void findMiddleNode_emptyList_returnsNull() {
        LinkedList list = new LinkedList(1);
        list.makeEmpty();
        assertNull(list.findMiddleNode());
    }

    // Test 2: One element
    @Test
    void findMiddleNode_oneElement_returnsTheOnlyNode() {
        LinkedList list = new LinkedList(1);
        LinkedList.Node middle = list.findMiddleNode();
        assertNotNull(middle);
        assertEquals(1, middle.value);
    }

    // Test 3: Two elements — even list, second node is "middle"
    @Test
    void findMiddleNode_twoElements_returnsSecondNode() {
        LinkedList list = new LinkedList(1);
        list.append(2);
        LinkedList.Node middle = list.findMiddleNode();
        assertNotNull(middle);
        assertEquals(2, middle.value);
    }

    // Test 4: Three elements — odd list
    @Test
    void findMiddleNode_threeElements_returnsCenterNode() {
        LinkedList list = new LinkedList(1);
        list.append(2);
        list.append(3);
        assertEquals(2, list.findMiddleNode().value);
    }

    // Test 5: Five elements — odd list
    @Test
    void findMiddleNode_fiveElements_returnsThirdNode() {
        LinkedList list = new LinkedList(1);
        list.append(2);
        list.append(3);
        list.append(4);
        list.append(5);
        assertEquals(3, list.findMiddleNode().value);
    }

    // Test 6: Six elements — even list, upper-middle is returned
    @Test
    void findMiddleNode_sixElements_returnsFourthNode() {
        LinkedList list = new LinkedList(1);
        list.append(2);
        list.append(3);
        list.append(4);
        list.append(5);
        list.append(6);
        assertEquals(4, list.findMiddleNode().value);
    }

    // Test 7: Eleven elements — larger odd list
    @Test
    void findMiddleNode_elevenElements_returnsSixthNode() {
        LinkedList list = new LinkedList(1);
        for (int i = 2; i <= 11; i++) {
            list.append(i);
        }
        assertEquals(6, list.findMiddleNode().value);
    }
}