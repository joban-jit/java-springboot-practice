package com.practice.dsa.mylinkedlist;

public class LinkedListMain {
    public static void main(String[] args) {
        LinkedList myLinkedList = new LinkedList(1);
        myLinkedList.printHead();
        myLinkedList.printTail();
        myLinkedList.printLength();
        myLinkedList.printList();
        System.out.println("appending");
        myLinkedList.append(2);
        myLinkedList.append(3);
        myLinkedList.printList();
        System.out.println("removing last");
        LinkedList.Node removeLastNode = myLinkedList.removeLast();
        System.out.println("removed value: "+removeLastNode.value);
        myLinkedList.printList();
        System.out.println("prepending");
        myLinkedList.prepend(0);
        myLinkedList.printList();

        System.out.println("Removing first");
        LinkedList.Node removeFirstNode = myLinkedList.removeFirst();
        System.out.println(removeFirstNode.value);
        myLinkedList.printList();
        LinkedList newLL = new LinkedList(10);
        LinkedList.Node newRemovedFirstNode = newLL.removeFirst();
        System.out.println(newRemovedFirstNode.value);
        newLL.printList();
        System.out.println("getting value based on index");
        System.out.println(myLinkedList.get(0).value);
        System.out.println(myLinkedList.get(1).value);
        System.out.println("Updated value: "+myLinkedList.set(1, 10));
        System.out.println(myLinkedList.get(1).value);
        System.out.println("Inserted new Node: "+myLinkedList.insert(1, 20));
        myLinkedList.printList();
        System.out.println("Inserted new Node: "+myLinkedList.insert(0, 5));
        System.out.println("Inserted new Node: "+myLinkedList.insert(5, 50));
        System.out.println("printing list");
        myLinkedList.printList();
        System.out.println("Removed node: "+myLinkedList.remove(2).value);
        System.out.println("Removed node: "+myLinkedList.remove(0).value);
        myLinkedList.printList();
        System.out.println("reversing");
        myLinkedList.reverse();
        myLinkedList.printList();



    }
}
