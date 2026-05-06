package com.practice.dsa.mylinkedlist;

public class LinkedListMain {
    public static void main(String[] args) {
        LinkedList myLinkedList = new LinkedList(10);
        myLinkedList.getHead();
        myLinkedList.getTail();
        myLinkedList.getLength();
        myLinkedList.printList();
        myLinkedList.append(20);
        myLinkedList.getHead();
        myLinkedList.getTail();
        myLinkedList.getLength();
        myLinkedList.printList();
        LinkedList.Node remove = myLinkedList.remove(20);
        System.out.println(remove);
        myLinkedList.printList();

    }
}
