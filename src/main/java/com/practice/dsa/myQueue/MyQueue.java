package com.practice.dsa.myQueue;

import com.practice.dsa.myDoublylinkedlist.DoublyLinkedList;
import com.practice.dsa.myStack.MyStack;

public class MyQueue {

    class Node{
        int value;
        Node next;
        Node(int value){
            this.value = value;
        }
    }

    private Node first;
    private Node last;
    private int length;

    public MyQueue(int value){
        Node temp = new Node(value);
        this.first = temp;
        this.last = temp;
        length++;
    }

    public void printQueue(){
        Node temp = first;
        System.out.println("------");
        if(length==0){
            System.out.println("Queue is empty");
        }else {
            System.out.println("Length: "+ length);
            System.out.println("First: " + this.first.value);
            System.out.println("Last: " + this.last.value);
            while (temp != null) {
                System.out.println("Value: " + temp.value);
                temp = temp.next;
            }
        }
        System.out.println("------");
    }

    public void enqueue(int value){
        Node newNode = new Node(value);
        if(length!=0) {
            last.next = newNode;
        }else{
            first = newNode;
        }
        last = newNode;
        length++;
    }

    public Node dequeue(){
        if(length==0){
            return null;
        }
        Node temp = first;
        if(length==1){
            first = null;
            last = null;
        }else {
            first = first.next;
            temp.next = null;
        }

        length--;
        return temp;
    }

     
}
