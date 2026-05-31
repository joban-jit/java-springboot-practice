package com.practice.dsa.myDoublylinkedlist;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DoublyLinkedList.reverse()")
class DoublyLinkedListReverseTest {

    private int[] forwardValues(DoublyLinkedList list) {
        if (list.getLength() == 0) return new int[0];
        int[] vals = new int[list.getLength()];
        DoublyLinkedList.Node cur = list.getHead();
        for (int i = 0; i < vals.length; i++) {
            vals[i] = cur.value;
            cur = cur.next;
        }
        return vals;
    }

    private int[] backwardValues(DoublyLinkedList list) {
        if (list.getLength() == 0) return new int[0];
        int[] vals = new int[list.getLength()];
        DoublyLinkedList.Node cur = list.getTail();
        for (int i = 0; i < vals.length; i++) {
            vals[i] = cur.value;
            cur = cur.prev;
        }
        return vals;
    }

    @Test
    @DisplayName("empty list -> head and tail remain null")
    void emptyList_noOp() {
        DoublyLinkedList list = new DoublyLinkedList(1);
        list.makeEmpty();
        list.reverse();
        assertNull(list.getHead());
        assertNull(list.getTail());
    }

    @Test
    @DisplayName("single node -> unchanged forward and backward")
    void singleNode_unchanged() {
        DoublyLinkedList list = new DoublyLinkedList(10);
        list.reverse();
        assertArrayEquals(new int[]{10}, forwardValues(list));
        assertArrayEquals(new int[]{10}, backwardValues(list));
    }

    @Test
    @DisplayName("multiple nodes before reverse -> forward and backward correct")
    void multipleNodes_beforeReverse_pointersCorrect() {
        DoublyLinkedList list = new DoublyLinkedList(1);
        list.append(2);
        list.append(3);
        list.append(4);
        list.append(5);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, forwardValues(list));
        assertArrayEquals(new int[]{5, 4, 3, 2, 1}, backwardValues(list));
    }

    @Test
    @DisplayName("even-length list reverse -> correct forward and backward")
    void evenLength_reverse_correctPointers() {
        DoublyLinkedList list = new DoublyLinkedList(1);
        list.append(2);
        list.append(3);
        list.append(4);
        list.reverse();
        assertArrayEquals(new int[]{4, 3, 2, 1}, forwardValues(list));
        assertArrayEquals(new int[]{1, 2, 3, 4}, backwardValues(list));
    }

    @Test
    @DisplayName("odd-length list reverse -> correct forward and backward")
    void oddLength_reverse_correctPointers() {
        DoublyLinkedList list = new DoublyLinkedList(1);
        list.append(2);
        list.append(3);
        list.append(4);
        list.append(5);
        list.reverse();
        assertArrayEquals(new int[]{5, 4, 3, 2, 1}, forwardValues(list));
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, backwardValues(list));
    }

    @Test
    @DisplayName("two nodes reverse -> head and tail swapped")
    void twoNodes_reverse_headTailSwapped() {
        DoublyLinkedList list = new DoublyLinkedList(1);
        list.append(2);
        list.reverse();
        assertArrayEquals(new int[]{2, 1}, forwardValues(list));
        assertArrayEquals(new int[]{1, 2}, backwardValues(list));
    }

    @Test
    @DisplayName("double reverse -> back to original order")
    void doubleReverse_restoresOriginal() {
        DoublyLinkedList list = new DoublyLinkedList(1);
        list.append(2);
        list.append(3);
        list.append(4);
        list.reverse();
        list.reverse();
        assertArrayEquals(new int[]{1, 2, 3, 4}, forwardValues(list));
        assertArrayEquals(new int[]{4, 3, 2, 1}, backwardValues(list));
    }
}
