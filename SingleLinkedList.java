package singlelinkedlist;

import java.util.NoSuchElementException;

public class SingleLinkedList {

    private class Node {

        private int value;
        private Node next;

        public Node() {
        }

        public Node(int value) {
            this.value = value;
        }
    }
    private Node head;
    private Node tail;
    private int size;
    private int num;
    private int pos;
    private int data;

    public void addLast(int item) {
        var node = new Node(item);
        if (isEmpty()) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    public void addFirst(int item) {
        var node = new Node(item);
        if (isEmpty()) {
            head = tail = node;
        } else {
            node.next = head;
            head = node;
        }
        size++;
    }

    private boolean isEmpty() {
        return head == null;
    }

    public void removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (head == tail) {
            head = tail = null;
        } else {
            var second = head.next;
            head.next = null;
            head = second;
        }
        size--;
    }

    public void removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (head == tail) {
            head = tail = null;
        } else {
            var previous = getPrevious(tail);
            tail = previous;
            tail.next = null;
        }
        size--;
    }

    private Node getPrevious(Node node) {
        var current = head;
        while (current != null) {
            if (current.next == node) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public int size() {
        return size;
    }

    public void deleteNodeByValue(int valueToDelete) {
        Node currentNode = head;
        Node nextNode;
        if (head == null) { // the sll is empty
            return;
        }
        if (valueToDelete == head.value) {
            System.out.println("Deleted: " + head.value);
            nextNode = head.next;
            head = nextNode;
            size--;
            return;
        }
        do {
            nextNode = currentNode.next;
            if (nextNode.value == valueToDelete) {
                if (tail == head) { // the sll has only one single element
                    head = null;
                    tail = null;
                } else {
                    currentNode.next = nextNode.next;
                    if (head == nextNode) { //we're deleting the head
                        head = head.next;
                    }
                    if (tail == nextNode) { //we're deleting the tail
                        tail = currentNode;
                    }
                }
                break;
            }
            currentNode = nextNode;
        } while (currentNode != head);
        System.out.println("Deleted: " + nextNode.value);
        size--;
    }

    public void deleteNodeByPosition(int position) {
        int len = calcLen(head);
// Can only insert after 1st position
// Can't insert if position to insert is greater than size of Linked List
        if (position < 1 || position > len) {
            System.out.println("Can't delete\n");
        } else {
            if (position == 1) {
// head has to be deleted
                System.out.println("Deleted: " + head.value);
                head = head.next;
                size--;
                return;
            }
// required to traverse
            Node temp = head;
            Node previous = new Node();
// traverse to the nth node
            while (--position > 0) {
                previous = temp;
                temp = temp.next;
            }
// assigned next node of the previous node to nth node's next
            previous.next = temp.next;
            if (tail == temp) {
                tail = previous;
            }
            System.out.println("Deleted: " + temp.value);
            size--;
        }
    }

    public int calcLen(Node temp) {
        int len = 0;
        while (temp != null) {
            temp = temp.next;
            len++;
        }
        return len;
    }

    public void insertNodeByPosition(int pos, int data) {
        Node new_node = new Node(data);
        new_node.value = data;
        new_node.next = null;
// Invalid positions
        if (pos < 1 || pos > size + 1) {
            System.out.println("Invalid\n");
        } // inserting first node
        else if (pos == 1) {
            new_node.next = head;
            head = new_node;
            size++;
        } else {
            Node temp = head;
// traverse till the current (pos-1)th node
            while (--pos > 1) {
                temp = temp.next;
            }
            new_node.next = temp.next;
            temp.next = new_node;
            size++;
        }
    }

    public void insertAfter(int data1, int data2) {
        if (isEmpty()) {
            System.out.println("The list is empty");
            return;
        }
        if (head == tail) {
            System.out.println("here");
            Node insertNode = new Node(data2);
            head.next = insertNode;
            tail = insertNode;
            size++;
            return;
        }
        if (head.value == data2) {
        }
        Node insertNode = new Node(data2);
        Node temp = head;
        while (temp.value != data1) {
            temp = temp.next;
// System.out.println(temp.value);
        }
        insertNode.next = temp.next;
        temp.next = insertNode;
//tail = insertNode;
// tail.next = insertNode;
// tail = insertNode;
        size++;
    }

    public void insertBefore(int data1, int data2) {
        Node p = new Node();
        if (data1 == head.value) {
            Node insertNode = new Node(data2);
            insertNode.next = head;
            head = insertNode;
            size++;
            return;
        }
        if (head == tail) {
            Node insertNode = new Node(data2);
            insertNode.next = head;
            head = insertNode;
            size++;
            return;
        }
        Node curr = head;
        while (curr.value != data1) {
            p = curr;
            curr = curr.next;
        }
        Node insertNode = new Node(data2);
        insertNode.value = data2;
        p.next = insertNode;
        insertNode.next = curr;
        size = size + 1;
    }

    public void show() {
        Node temp;
        temp = head;
        while (temp != null) {
            System.out.print(temp.value + "-->");
            temp = temp.next;
        }
        System.out.print("null");
        System.out.print("\n");
        System.out.println("Size of list = " + size());
        headntail();
        System.out.println("");
    }

    public void headntail() {
        System.out.println("head ---> " + head.value);
        System.out.println("tail ---> " + tail.value);
    }

    public int indexOf(int item) {
        int index = 0;
        var current = head;
        while (current != null) {
            if (current.value == item) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    public static void main(String[] args) {
        SingleLinkedList sll = new SingleLinkedList();
        sll.addFirst(1);
        sll.show();
        sll.addLast(2);
        sll.show();
        sll.addLast(3);
        sll.show();
        sll.deleteNodeByPosition(3);
        sll.show();
        sll.deleteNodeByPosition(2);
        sll.show();
    }
}
