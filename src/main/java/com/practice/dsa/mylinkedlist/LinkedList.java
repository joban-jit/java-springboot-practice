package com.practice.dsa.mylinkedlist;


import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

public class  LinkedList {

    @Getter
    private Node head;
    @Getter
    private Node tail;
    @Getter
    private int length;

    class Node{
        int value;
        Node next;
        Node(int value){
            this.value = value;
        }
    }

    public LinkedList(int value){
        Node newNode = new Node(value);
        this.head =  newNode;
        this.tail = newNode;
        this.length = 1;
    }


    public void printHead(){
        System.out.println("Head: "+this.head.value);
    }

    public void printTail(){
        System.out.println("Tail: "+this.tail.value);
    }

    public void printLength(){
        System.out.println("Length: "+this.length);
    }

    public void printList(){
        Node temp = this.head;
        if(temp==null){
            System.out.println("Nothing to print");
        }
        while(temp!=null){
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    public void printAll() {
        if (head == null) {
            System.out.println("Head: null");
            System.out.println("Tail: null");
            System.out.println("Length: 0");
        } else {
            System.out.println("Head: " + head.value);
            System.out.println("Tail: " + tail.value);
            System.out.println("Length: "+length);
        }
        System.out.println("\nLinked List:");
        if (head == null) {
            System.out.println("empty");
        } else {
            printList();
        }
    }

    public void makeEmpty() {
        head = null;
        tail = null;
        length = 0;
    }



    public void append(int value){
        Node newNode = new Node(value);
        if(this.length==0){
            this.head = newNode;
            this.tail = newNode;
        }else {
            this.tail.next = newNode;
            this.tail = newNode;
        }
        this.length++;
    }

    public Node removeLast(){
        if(length==0){
            return null;
        }
        Node pre = this.head;
        Node temp = this.head;
        while (temp.next != null) {
            pre = temp;
            temp = temp.next;
        }
        this.tail = pre;
        this.tail.next = null;
        this.length--;
        if(length==0){
            this.tail=null;
            this.head=null;
        }
        return temp;

    }

    public void prepend(int value){
        Node newNode = new Node(value);
        if(length==0){
            this.tail = newNode;
        }else{
            newNode.next = this.head;
        }
        this.head = newNode;
        length++;
    }

    public Node removeFirst(){
        if(length==0){
            return null;
        }
        Node temp = this.head;
        this.head = this.head.next;
        temp.next = null;
        length--;
        if(length==0){
            this.tail = null;
        }
        return temp;
    }

    public Node get(int index){
        if(index<0 || index>=this.length){
            return null;
        }
        Node temp = this.head;
        int i = 0;
        while(i<index){
            temp = temp.next;
            i++;
        }
        return temp;
    }

    public boolean set(int index, int value){
//        if(index<0 || index>=this.length){
//            return false;
//        }
//        Node temp = this.head;
//        int i = 0;
//        while(i<index){
//            temp = temp.next;
//            i++;
//        }
        // above code is same as in get(int index) method so we directly call that method
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
        if(index == 0){
            prepend(value);
            return true;
        }
        if(index == length){
            append(value);
            return true;
        }
        Node temp = get(index - 1);
        Node newNode = new Node(value);
        newNode.next = temp.next;
        temp.next = newNode;
        length++;
        return true;
    }

    public Node remove(int index){
        if(index < 0 || index >= this.length){
            return null;
        }
        if(index == 0){
            return removeFirst();
        }
        if(index == length-1){
            return removeLast();
        }
        Node prev = get(index - 1);
        Node temp = prev.next;
        prev.next = temp.next;
        temp.next = null;
        length--;
        return temp;

    }

    public void reverse(){

        Node temp = this.head;
        this.head = this.tail;
        this.tail = temp;

        Node before = null;
        Node after;
        int i = 0;
        while(i<length){
            after = temp.next;
            temp.next = before; // reversing the arrow
            before = temp;
            temp = after;
            i++;
        }
    }

    // Leet interview questions:

    public Node findMiddleNode(){
        // Initialize slow pointer to the head of the linked list
        Node slow = head;

        // Initialize fast pointer to the head of the linked list
        Node fast = head;

        // Traverse the linked list with two pointers: slow and fast
        // slow moves one node at a time, while fast moves two nodes at a time
        while (fast != null && fast.next != null) {
            // Move slow pointer to the next node
            slow = slow.next;

            // Move fast pointer to the next two nodes
            fast = fast.next.next;
        }

        // Return the Node object representing the middle node of the linked list
        return slow;
    }

    // Find loop
    public boolean hasLoop() {
        // Initialize slow pointer to the head of the linked list
        Node slow = head;

        // Initialize fast pointer to the head of the linked list
        Node fast = head;

        // Traverse the linked list with two pointers: slow and fast
        // slow moves one node at a time, while fast moves two nodes at a time
        while (fast != null && fast.next != null) {
            // Move slow pointer to the next node
            slow = slow.next;

            // Move fast pointer to the next two nodes
            fast = fast.next.next;

            // If slow pointer meets fast pointer, then there is a loop in the linked list
            if (slow == fast) {
                return true;
            }
        }

        // If the loop has not been detected after the traversal, then there is no loop in the linked list
        return false;
    }

    // in this we can't use length
    public Node findKthFromEnd(int k) {
        // concept: If two pointers move at the SAME speed, the distance between them never changes.
        //So if fast is kept k nodes ahead of slow,
        //then when fast reaches the end,
        //slow must be k nodes behind the end.
        if(k<=0){
            return null;
        }
        if(k==1){
            return this.tail;
        }
        Node slow = this.head;
        Node fast = this.head;
        for(int i = 0;i<k;i++){
            if(fast == null){
                return null;
            }
            fast = fast.next;
        }
        while(fast!=null){
            slow= slow.next;
            fast = fast.next;
        }
        return slow;

    }

    public void removeDuplicates(){
        Set<Integer> tempSet = new HashSet<>();
        Node currentNode = this.head;
        if(currentNode!=null) {
            tempSet.add(currentNode.value);
            while (currentNode.next != null) {
                Node temp = currentNode.next;
                if (!tempSet.add(temp.value)) {
                    currentNode.next = temp.next;
                    temp.next = null;
                    length--;
                }else {
                    currentNode = currentNode.next;
                }
            }
        }
    }

    public int binaryToDecimal(){
        Node currentNode = this.head;
        int num = 0;
        while(currentNode!=null){
            num = num*2+currentNode.value;
            currentNode = currentNode.next;
        }
        return num;
    }



}