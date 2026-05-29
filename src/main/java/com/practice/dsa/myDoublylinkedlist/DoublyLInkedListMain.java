package com.practice.dsa.myDoublylinkedlist;

public class DoublyLInkedListMain {
    public static void main(String[] args) {
        DoublyLinkedList myDoublyLinkedList = new DoublyLinkedList(7);
        myDoublyLinkedList.append(8);
        myDoublyLinkedList.append(9);
//        System.out.println("Removed node value: "+myDoublyLinkedList.removeLast().value);
        myDoublyLinkedList.prepend(6);
        myDoublyLinkedList.prepend(5);
        myDoublyLinkedList.prepend(4);
//        System.out.println("Removed node value: "+myDoublyLinkedList.removeFirst().value);
//        System.out.println(myDoublyLinkedList.get(1).value);
//        System.out.println(myDoublyLinkedList.set(1,50));
        myDoublyLinkedList.insert(4, 20);
        myDoublyLinkedList.printList();
        System.out.println("Removed node value: "+myDoublyLinkedList.remove(4).value);
        myDoublyLinkedList.printList();

    }
}
