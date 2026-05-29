package com.practice.dsa.mylinkedlist;

public class LinkedListQuestions {

    public static void main(String[] args) {

        // Leet code questions:

        // Test 1: Empty list
        System.out.println("Test 1: Empty List");
        LinkedList myLinkedList = new LinkedList(1);
        myLinkedList.makeEmpty();  // Make list empty
        System.out.println("Expected: null");
        System.out.println("Actual: " +
                (myLinkedList.findMiddleNode() == null
                        ? "null"
                        : myLinkedList.findMiddleNode().value));
        System.out.println();

        // Test 2: One element
        System.out.println("Test 2: One Element");
        myLinkedList = new LinkedList(1);
        System.out.println("1");
        System.out.println("Expected Middle Node: 1");
        System.out.println("Actual Middle Node: " +
                (myLinkedList.findMiddleNode() == null
                        ? "null"
                        : myLinkedList.findMiddleNode().value));
        System.out.println();

        // Test 3: Two elements (smallest even list)
        System.out.println("Test 3: Two Elements");
        myLinkedList = new LinkedList(1);
        myLinkedList.append(2);
        System.out.println("1 -> 2");
        System.out.println("Expected Middle Node: 2");
        System.out.println("Actual Middle Node: " +
                (myLinkedList.findMiddleNode() == null
                        ? "null"
                        : myLinkedList.findMiddleNode().value));
        System.out.println();

        // Test 4: Three elements (smallest odd list)
        System.out.println("Test 4: Three Elements");
        myLinkedList = new LinkedList(1);
        myLinkedList.append(2);
        myLinkedList.append(3);
        System.out.println("1 -> 2 -> 3");
        System.out.println("Expected Middle Node: 2");
        System.out.println("Actual Middle Node: " +
                (myLinkedList.findMiddleNode() == null
                        ? "null"
                        : myLinkedList.findMiddleNode().value));
        System.out.println();

        // Test 5: Odd number of elements (5 nodes)
        System.out.println("Test 5: Odd Number of Elements");
        myLinkedList = new LinkedList(1);
        myLinkedList.append(2);
        myLinkedList.append(3);
        myLinkedList.append(4);
        myLinkedList.append(5);
        System.out.println("1 -> 2 -> 3 -> 4 -> 5");
        System.out.println("Expected Middle Node: 3");
        System.out.println("Actual Middle Node: " +
                (myLinkedList.findMiddleNode() == null
                        ? "null"
                        : myLinkedList.findMiddleNode().value));
        System.out.println();

        // Test 6: Even number of elements (6 nodes)
        System.out.println("Test 6: Even Number of Elements");
        myLinkedList.append(6);
        System.out.println("1 -> 2 -> 3 -> 4 -> 5 -> 6");
        System.out.println("Expected Middle Node: 4");
        System.out.println("Actual Middle Node: " +
                (myLinkedList.findMiddleNode() == null
                        ? "null"
                        : myLinkedList.findMiddleNode().value));
        System.out.println();

        // Test 7: Larger list (11 nodes)
        System.out.println("Test 7: Larger List (11 Nodes)");
        myLinkedList = new LinkedList(1);
        for (int i = 2; i <= 11; i++) {
            myLinkedList.append(i);
        }
        System.out.println("1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8 -> 9 -> 10 -> 11");
        System.out.println("Expected Middle Node: 6");
        System.out.println("Actual Middle Node: " +
                (myLinkedList.findMiddleNode() == null
                        ? "null"
                        : myLinkedList.findMiddleNode().value));
        System.out.println();


        // Has Loop
        // Test 1: Empty list
        System.out.println("Test 1: Empty List");
        myLinkedList = new LinkedList(1);
        myLinkedList.makeEmpty();  // Make list empty
        System.out.println("Expected: false");
        System.out.println("Actual: " + myLinkedList.hasLoop());
        System.out.println();

        // Test 2: One node, no loop
        System.out.println("Test 2: One Node (No Loop)");
        myLinkedList = new LinkedList(1);
        System.out.println("Expected: false");
        System.out.println("Actual: " + myLinkedList.hasLoop());
        System.out.println();

        // Test 3: One node, with loop to itself
        System.out.println("Test 3: One Node (Loop to Itself)");
        myLinkedList = new LinkedList(1);
        myLinkedList.getHead().next = myLinkedList.getHead();  // Create loop
        System.out.println("Expected: true");
        System.out.println("Actual: " + myLinkedList.hasLoop());
        System.out.println();

        // Test 4: Multi-node, no loop
        System.out.println("Test 4: Multi-Node (No Loop)");
        myLinkedList = new LinkedList(1);
        myLinkedList.append(2);
        myLinkedList.append(3);
        myLinkedList.append(4);
        System.out.println("1 -> 2 -> 3 -> 4");
        System.out.println("Expected: false");
        System.out.println("Actual: " + myLinkedList.hasLoop());
        System.out.println();

        // Test 5: Multi-node, loop back to head
        System.out.println("Test 5: Multi-Node (Loop to Head)");
        myLinkedList = new LinkedList(1);
        myLinkedList.append(2);
        myLinkedList.append(3);
        myLinkedList.append(4);
        myLinkedList.getTail().next = myLinkedList.getHead();  // Loop to head
        System.out.println("1 -> 2 -> 3 -> 4 -> (loops back to 1)");
        System.out.println("Expected: true");
        System.out.println("Actual: " + myLinkedList.hasLoop());
        System.out.println();

        // Test 6: Multi-node, loop in the middle
        System.out.println("Test 6: Multi-Node (Loop to Middle Node)");
        myLinkedList = new LinkedList(1);
        myLinkedList.append(2);
        myLinkedList.append(3);
        myLinkedList.append(4);
        LinkedList.Node middle = myLinkedList.getHead().next.next;  // Node 3
        myLinkedList.getTail().next = middle;  // Tail loops to 3
        System.out.println("1 -> 2 -> 3 -> 4 -> (loops back to 3)");
        System.out.println("Expected: true");
        System.out.println("Actual: " + myLinkedList.hasLoop());
        System.out.println();

        // find kth node from end
        // Test 1: Empty list (k = 1)
        System.out.println("Test 1: Empty List (k = 1)");
        myLinkedList = new LinkedList(1);
        myLinkedList.makeEmpty();
        LinkedList.Node result = myLinkedList.findKthFromEnd(1);
        System.out.println("Expected: null");
        System.out.println("Actual: " + (result == null ? "null" : result.value));
        System.out.println();

        // Test 2: One element, k = 1 (last node)
        System.out.println("Test 2: One Element (k = 1)");
        myLinkedList = new LinkedList(10);
        result = myLinkedList.findKthFromEnd(1);
        System.out.println("10");
        System.out.println("Expected: 10");
        System.out.println("Actual: " + (result == null ? "null" : result.value));
        System.out.println();

        // Test 3: One element, k = 2 (beyond end)
        System.out.println("Test 3: One Element (k = 2, Beyond End)");
        myLinkedList = new LinkedList(20);
        result = myLinkedList.findKthFromEnd(2);
        System.out.println("20");
        System.out.println("Expected: null");
        System.out.println("Actual: " + (result == null ? "null" : result.value));
        System.out.println();

        // Prepare a multi-node list
        myLinkedList = new LinkedList(1);
        for (int i = 2; i <= 6; i++) {
            myLinkedList.append(i);
        }
        // List: 1 -> 2 -> 3 -> 4 -> 5 -> 6

        // Test 4: k = 1 (last node)
        System.out.println("Test 4: Multi-Node (k = 1)");
        result = myLinkedList.findKthFromEnd(1);
        System.out.println("1 -> 2 -> 3 -> 4 -> 5 -> 6");
        System.out.println("Expected: 6");
        System.out.println("Actual: " + (result == null ? "null" : result.value));
        System.out.println();

        // Test 5: k = 2 (second-to-last node)
        System.out.println("Test 5: Multi-Node (k = 2)");
        result = myLinkedList.findKthFromEnd(2);
        System.out.println("1 -> 2 -> 3 -> 4 -> 5 -> 6");
        System.out.println("Expected: 5");
        System.out.println("Actual: " + (result == null ? "null" : result.value));
        System.out.println();

        // Test 6: k = 7 (k too large, length=6)
        System.out.println("Test 6: Multi-Node (k = 7, Too Large)");
        result = myLinkedList.findKthFromEnd(7);
        System.out.println("1 -> 2 -> 3 -> 4 -> 5 -> 6");
        System.out.println("Expected: null");
        System.out.println("Actual: " + (result == null ? "null" : result.value));
        System.out.println();

        // Test 7: k = 4 (middle node)
        System.out.println("Test 7: Multi-Node (k = 4)");
        result = myLinkedList.findKthFromEnd(4);
        System.out.println("1 -> 2 -> 3 -> 4 -> 5 -> 6");
        System.out.println("Expected: 3");
        System.out.println("Actual: " + (result == null ? "null" : result.value));
        System.out.println();

    }


}
