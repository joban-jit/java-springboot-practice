package com.practice.dsa.myDoublylinkedlist;

import com.practice.dsa.mylinkedlist.LinkedList;
import lombok.Getter;

public class DoublyLinkedList {

    class Node{
        int value;
        Node next;
        Node prev;

        Node(int value){
            this.value = value;
        }
    }

    @Getter
    private Node head;
    @Getter
    private Node tail;
    @Getter
    private int length;

    public DoublyLinkedList(int value){
        Node newNode = new Node(value);
        this.head = newNode;
        this.tail = newNode;
        length = 1;
    }

    public void printList(){
        Node temp = head;
        System.out.println("------");
        if(length==0){
            System.out.println("List is empty");
        }else {
            System.out.println("Length: "+ length);
            System.out.println("Head: " + this.head.value);
            System.out.println("Tail: " + this.tail.value);
            while (temp != null) {
                System.out.println("Value: " + temp.value);
                temp = temp.next;
            }
        }
        System.out.println("------");
    }

    public void makeEmpty() {
        head = null;
        tail = null;
        length = 0;
    }


    public void append(int value){
        Node newNode = new Node(value);
        if(length==0){
            this.head = newNode;
            this.tail = newNode;
        }else{
        this.tail.next = newNode;
        newNode.prev = this.tail;
        this.tail = newNode;
        }
        this.length++;
    }



    public Node removeLast(){
        if(length==0){
            return null;
        }
        Node toBeRemoved = this.tail;
        if(length==1){
            this.head=null;
            this.tail = null;
        }else {
            this.tail = this.tail.prev;
            this.tail.next = null;
            toBeRemoved.prev = null;
        }
        this.length--;
        return toBeRemoved;
    }

    public void prepend(int value){
        Node newNode = new Node(value);
        if(length==0){
            this.head = newNode;
            this.tail = newNode;
        }else{
            this.head.prev = newNode;
            newNode.next = this.head;
            this.head = newNode;
        }
        this.length++;
    }

    public Node removeFirst(){
        if(length==0){
            return null;
        }
        Node toBeRemoved = this.head;
        if(length==1){
            this.head = null;
            this.tail = null;
        }else{
            this.head = this.head.next;
            this.head.prev = null;
            toBeRemoved.next=null;
        }
        this.length--;
        return toBeRemoved;
    }

    public Node get(int index){
        if(index<0 || index>=length){
            return null;
        }
        Node temp = this.head;
        if(index<length/2) {
            for(int i = 0;i<index;i++){
                temp = temp.next;
            }
        }else{
            temp = this.tail;
            for(int i=length-1;i>index;i--){
                temp = temp.prev;
            }
        }
        return temp;
    }

    public boolean set(int index, int value){
        Node temp = get(index);
        if(temp!=null){
            temp.value = value;
            return true;
        }
        return false;
    }

    public boolean insert(int index, int value){
        if(index < 0 || index > this.length){
            return false;
        }
        if(index==0){
            prepend(value);
            return true;
        }
        if(index==length){
            append(value);
            return true;
        }
        Node newNode = new Node(value);
        Node before = get(index-1);
        Node after = before.next;
        newNode.next = after;
        newNode.prev = before;
        before.next = newNode;
        after.prev = newNode;
        length++;
        return true;
    }

    public Node remove(int index) {
        if (index < 0 || index >= this.length) {
            return null;
        }
        if (index == 0) {
            return removeFirst();
        }
        if (index == length - 1) {
            return removeLast();
        }
        Node temp = get(index);
        Node before = temp.prev;
        Node after = temp.next;
        before.next = after;
        after.prev = before;
        temp.next = null;
        temp.prev = null;
        length--;
        return temp;

    }

    public boolean isPalindrome(){
        if (length != 0 && length != 1) {
            Node forward = this.head;
            Node backward = this.tail;
            for (int i = 0; i < length / 2; i++) {

                if (forward.value != backward.value) {
                    return false;
                }
                forward = forward.next;
                backward = backward.prev;
            }
        }
        return true;

    }

}
