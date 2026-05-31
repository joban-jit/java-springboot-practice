package com.practice.dsa.mylinkedlist;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LinkedList.removeDuplicates()")
class LinkedListRemoveDuplicatesTest {

    private List<Integer> toList(LinkedList ll) {
        List<Integer> result = new ArrayList<>();
        LinkedList.Node current = ll.getHead();
        while (current != null) {
            result.add(current.value);
            current = current.next;
        }
        return result;
    }

    @Test
    @DisplayName("empty list -> stays empty")
    void emptyList_staysEmpty() {
        LinkedList list = new LinkedList(1);
        list.makeEmpty();
        list.removeDuplicates();
        assertNull(list.getHead());
        assertEquals(0, list.getLength());
    }

    @Test
    @DisplayName("single node -> unchanged")
    void singleNode_unchanged() {
        LinkedList list = new LinkedList(10);
        list.removeDuplicates();
        assertEquals(List.of(10), toList(list));
        assertEquals(1, list.getLength());
    }

    @Test
    @DisplayName("no duplicates -> unchanged")
    void noDuplicates_unchanged() {
        LinkedList list = new LinkedList(1);
        list.append(2);
        list.append(3);
        list.removeDuplicates();
        assertEquals(List.of(1, 2, 3), toList(list));
        assertEquals(3, list.getLength());
    }

    @Test
    @DisplayName("duplicates in middle -> removed")
    void duplicatesInMiddle_removed() {
        LinkedList list = new LinkedList(1);
        list.append(2);
        list.append(2);
        list.append(3);
        list.removeDuplicates();
        assertEquals(List.of(1, 2, 3), toList(list));
        assertEquals(3, list.getLength());
    }

    @Test
    @DisplayName("all duplicates -> single node remains")
    void allDuplicates_singleNodeRemains() {
        LinkedList list = new LinkedList(5);
        list.append(5);
        list.append(5);
        list.append(5);
        list.removeDuplicates();
        assertEquals(List.of(5), toList(list));
        assertEquals(1, list.getLength());
    }

    @Test
    @DisplayName("duplicates at start and end -> removed")
    void duplicatesAtStartAndEnd_removed() {
        LinkedList list = new LinkedList(1);
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(3);
        list.removeDuplicates();
        assertEquals(List.of(1, 2, 3), toList(list));
        assertEquals(3, list.getLength());
    }

    @Test
    @DisplayName("mixed duplicates throughout -> removed")
    void mixedDuplicates_removed() {
        LinkedList list = new LinkedList(1);
        list.append(2);
        list.append(1);
        list.append(3);
        list.append(2);
        list.append(4);
        list.removeDuplicates();
        assertEquals(List.of(1, 2, 3, 4), toList(list));
        assertEquals(4, list.getLength());
    }
}
