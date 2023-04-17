package myLib.datastructures.linear;
import myLib.datastructures.nodes.*;

public class SLL {
    protected SNode head;
    protected SNode tail;
    protected int size = 0;

    public SLL() {
        this.head = null;
        this.tail = null;
    }

    public SLL(SNode startNode) {
        this.head = startNode;
        this.tail = startNode;
        this.size += 1;
    }

    public void InsertHead(SNode newNode) {
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        }
        else {
            newNode.setNextNode(this.head);
            this.head = newNode;
        }
        this.size += 1;
    }

    public void InsertTail(SNode newNode) {
        if (this.tail == null) {
            this.head = newNode;
            this.tail = newNode;
        }
        else {
            this.tail.setNextNode(newNode);
            this.tail = newNode;
        }
        this.size += 1;
    }

    public void Insert(SNode newNode, int position) {
        if (position > this.size) {
            System.out.println("The position you want is larger than the linked list");
        }
        else {
            SNode tempNode = this.head;
            SNode prevNode = new SNode();
            if (position == 0) {
                this.InsertHead(newNode);
            }
            for (int curPos = 1; curPos <= position; curPos++) {
                prevNode = tempNode;
                tempNode = tempNode.getNextNode();
            }
            if (position != 0) {
                prevNode.setNextNode(newNode);
                newNode.setNextNode(tempNode);
            }
            this.size += 1;
        }
    }

    public void SortedInsert(SNode newNode) {
        this.InsertHead(newNode);
        this.size += 1;

        if (!this.isSorted()) {
            this.Sort();
        }
    }

    public SNode Search(SNode findNode) {
        if (this.head == null) {
            return null;
        }
        SNode current = this.head;
        while (current != null) {
            if (current == findNode) {
                return current;
            }
            current = current.getNextNode();
        }
        return null;
    }

    public void DeleteHead() {
        if (this.head == this.tail) {
            this.head = null;
            this.tail = null;
        } 
        else {
            this.head = this.head.getNextNode();
        }
        this.size -= 1;
    }

    public void DeleteTail() {
        if (this.head == null || this.head.getNextNode() == null) {
            this.head = null;
            this.tail = null;
            this.size = 0;
            return;
        }
        SNode current = this.head;
        while (current.getNextNode() != this.tail) {
            current = current.getNextNode();
        }
        this.tail = current;
        this.tail.setNextNode(null);
        this.size -= 1;
    }

    public void Delete(SNode deleteNode) {
        if (this.head == null) {
            return;
        }

        if (this.head == this.tail) {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        if (this.head == deleteNode) {
            this.head = this.head.getNextNode();
            return;
        }
        
        SNode prevNode = null;
        SNode current = this.head;
        while (current != null) {
            if (current == deleteNode) {
                prevNode.setNextNode(current.getNextNode());
            }
            if (current != null) {
                prevNode = current;
                current = current.getNextNode();
            }
        }
    }

    public void Sort() {
        if (this.head == null || this.head.getNextNode() == null) {
            System.out.println("head is null or node after head is null");
            return;
        }

        SNode sortedList = null;
        SNode current = this.head;

        while (current != null) {
            SNode next = current.getNextNode();
            if (sortedList == null || current.getData() < sortedList.getData()) {
                current.setNextNode(sortedList);
                sortedList = current;
            } 
            else {
                SNode temp = sortedList;
                while (temp.getNextNode() != null && current.getData() > temp.getNextNode().getData()) {

                    temp = temp.getNextNode();
                }
                current.setNextNode(temp.getNextNode());
                temp.setNextNode(current);
            }
            current = next;
        }
        this.head = sortedList;
        current = this.head;
        while (current != null) {
            if (current.getNextNode() != null) {
                current = current.getNextNode();
            }
            else {
                this.tail = current;
                break;
            }
        }
    }

    public Boolean isSorted() {
        if (this.head == null || this.head.getNextNode() == null) {
            return true;
        }
        SNode current = this.head;
        while (current.getNextNode() != null) {
            if (current.getData() > current.getNextNode().getData()) {
                return false;
            }
            current = current.getNextNode();
        }
        return true;
    }

    public void Clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void Print() {
        if (this.head == null) {
            return;
        }
        SNode current = this.head;
        while (current != null) {
            System.out.println(String.valueOf(current.getData()));
            current = current.getNextNode();
        }
    }

    public SNode getHead() {
        return this.head;
    }

    public SNode getTail() {
        return this.tail;
    }
}