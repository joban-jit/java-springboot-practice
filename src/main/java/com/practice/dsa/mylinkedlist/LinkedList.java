package com.practice.dsa.mylinkedlist;


import lombok.ToString;


public class  LinkedList {

    private Node head;
    private Node tail;
    private int length;

    class Node{
        int value;
        Node next;
        Node(int value){
            this.value = value;
        }
        // toString method for Node class
        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    public LinkedList(int value){
        Node newNode = new Node(value);
        System.out.println(newNode);
        this.head = newNode;
        this.tail = newNode;
        this.length = 1;
    }

    public void printList(){
        Node temp = this.head;
        while(temp!=null){
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    public void getHead(){
        System.out.println("Head: " + head.value);
    }

    public void getTail(){
        System.out.println("Tail: " + tail.value);
    }

    public void getLength(){
        System.out.println("Length: " + length);
    }

    public void append(int value){
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
        }
        else{
            tail.next = newNode; // for last element to point to new element
        }
        tail = newNode; // moving the tail to newly added node
        length++;
    }

    public Node remove(int value){
            Node temp = this.head;
//            Node prev = this.head;
            while(temp.value!=value && temp.next!=null){

                temp = temp.next;
            }
            this.tail = temp;
            this.tail.next = null;
            this.length--;
            return temp;
    }




}
