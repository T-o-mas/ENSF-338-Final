package myLib.datastructures.linear;
import myLib.datastructures.nodes.*;

public class CSLL extends SLL { 

    public CSLL () {
        this.head = null;
        this.tail = null;
    }

    public CSLL(SNode newNode) {
        this.head = newNode;
        this.tail = newNode;
        this.head.setNextNode(this.tail);
        this.tail.setNextNode(this.head);
        this.size += 1;
    }

    public void InsertHead(SNode newNode) {
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
            this.head.setNextNode(this.tail);
            this.tail.setNextNode(this.head);
        }
        else {
            newNode.setNextNode(this.head);
            this.head = newNode;
            this.tail.setNextNode(this.head);
        }
        this.size += 1;
    }

    public void InsertTail(SNode newNode) {
        if (this.tail == null) {
            this.head = newNode;
            this.tail = newNode;
            this.head.setNextNode(this.tail);
            this.tail.setNextNode(this.head);
        }
        else {
            this.tail.setNextNode(newNode);
            this.tail = newNode;
            this.tail.setNextNode(this.head);
        }
        this.size += 1;
    }

    public SNode Search(SNode findNode) {
        if (this.head == null) {
            return null;
        }
        SNode current = this.head;
        do {
            if (current == findNode) {
                return current;
            }
            current = current.getNextNode();
        } while (current != this.head);
        return null;
    }

    public void DeleteHead() {
        if (this.head == this.tail) {
            this.head = null;
            this.tail = null;
        } 
        else {
            this.head = this.head.getNextNode();
            this.tail.setNextNode(this.head);
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
        this.tail.setNextNode(this.head);
        this.size -= 1;
    }

    public void Delete(SNode deleteNode) {
        if (this.head == deleteNode) {
            this.head = this.head.getNextNode();
            return;
        }
        SNode prevNode = null;
        SNode current = this.head;
        do {
            if (current == deleteNode) {
                prevNode.setNextNode(current.getNextNode());
            }
            if (current != null) {
                prevNode = current;
                current = current.getNextNode();
            }
        } while (current != this.head);
    }

    public void Sort() {
        if (this.head == null || this.head.getNextNode() == null) {
            return;
        }
    
        SNode sortedList = null;
        SNode current = this.head;
    
        do {
            SNode next = current.getNextNode();
            if (sortedList == null || current.getData() < sortedList.getData()) {
                current.setNextNode(sortedList);
                sortedList = current;
            } else {
                SNode temp = sortedList;
                while (temp.getNextNode() != null && current.getData() > temp.getNextNode().getData()) {
                    temp = temp.getNextNode();
                }
                current.setNextNode(temp.getNextNode());
                temp.setNextNode(current);
            }
            current = next;
        } while (current != this.head);
        this.head = sortedList;
        current = this.head;
        while (current != null) {
            if (current.getNextNode() != null) {
                current = current.getNextNode();
            }
            else {
                this.tail = current;
                this.tail.setNextNode(this.head);
                break;
            }
        }
    }

    public Boolean isSorted() {
        if (this.head == null || this.head.getNextNode() == null) {
            return true;
        }
        SNode current = this.head;
        while (current.getNextNode() != this.head) {
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
        do {
            System.out.println(String.valueOf(current.getData()));
            current = current.getNextNode();
        } while (current != this.head);
    }
}
