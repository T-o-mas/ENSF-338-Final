package myLib.datastructures.linear;
import myLib.datastructures.nodes.*;

public class DLL {
    protected DNode head;
    protected DNode tail;
    protected int size = 0;

    public DLL() {
        this.head = null;
        this.tail = null;
    }

    public DLL(DNode startNode) {
        this.head = startNode;
        this.tail = startNode;
        this.size += 1;
    }

    public void InsertHead(DNode newNode) {
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        }
        else {
            this.head.setPrevNode(newNode);
            newNode.setNextNode(this.head);
            this.head = newNode;
        }
        this.size += 1;
    }

    public void InsertTail(DNode newNode) {
        if (this.tail == null) {
            this.head = newNode;
            this.tail = newNode;
        }
        else {
            newNode.setPrevNode(this.tail);
            this.tail.setNextNode(newNode);
            this.tail = newNode;
        }
        this.size += 1;
    }

    public void Insert(DNode newNode, int position) {
        if (position > this.size) {
            System.out.println("The position you want is larger than the linked list");
        }

        else {
            DNode tempNode = this.head;
            DNode prevNode = null;
            if (position == 0) {
                this.InsertHead(newNode);
            }
            else {
                for (int curPos = 1; curPos <= position; curPos++) {
                    prevNode = tempNode;
                    tempNode = tempNode.getNextNode();
                }
                prevNode.setNextNode(newNode);
                newNode.setPrevNode(prevNode);
                newNode.setNextNode(tempNode);
                if (tempNode != null) {
                    tempNode.setPrevNode(newNode);
                }
                else {
                    this.tail = newNode;
                }
                this.size += 1;
            }
        }
    }

    public void SortedInsert(DNode newNode) {
        this.InsertHead(newNode);

        if (this.isSorted() == false) {
            this.Sort();
        }
    }

    public DNode Search(DNode findNode) {
        if (this.head == null) {
            return null;
        }
        DNode current = this.head;
        while (current != null) {
            if (current == findNode) {
                return current;
            }
            current = current.getNextNode();
        }
        return null;
    }

    public void DeleteHead() {
        this.head = this.head.getNextNode();
        this.head.setPrevNode(null);
        this.size -= 1;
    }

    public void DeleteTail() {
        this.tail = this.tail.getPrevNode();
        this.tail.setNextNode(null);
        this.size -= 1;
    }

    public void Delete(DNode deleteNode) {
        if (this.head == deleteNode) {
            this.head = this.head.getNextNode();
            this.head.setPrevNode(null);
            return;
        }
        DNode prevNode = null;
        DNode current = this.head;
        while (current != null) {
            if (current == deleteNode) {
                prevNode.setNextNode(current.getNextNode());
                current.getNextNode().setPrevNode(prevNode);
            }
            if (current != null) {
                prevNode = current;
                current = current.getNextNode();
            }
        }
    }

    public void Sort() {
        if (this.head == null || this.head.getNextNode() == null) {
            return;
        }

        DNode sortedList = null;
        DNode current = this.head;


        while (current != null) {
            DNode next = current.getNextNode();
            if (sortedList == null || current.getData() < sortedList.getData()) {
                current.setNextNode(sortedList);
                sortedList = current;
            } else {
                DNode temp = sortedList;
                while (temp.getNextNode() != null && current.getData() > temp.getNextNode().getData()) {
                    temp = temp.getNextNode();
                }
                current.setNextNode(temp.getNextNode());
                temp.setNextNode(current);
            }
            current = next;
        }
        this.head = sortedList;
        DNode prev = null;
        current = this.head;
        while (current != null) {
            if (current.getNextNode() != null) {
                prev = current;
                current = current.getNextNode();
                current.setPrevNode(prev);
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
        DNode current = this.head;
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
        DNode current = this.head;
        while (current != null) {
            System.out.println(String.valueOf(current.getData()));
            current = current.getNextNode();
        }
    }

    
    public static void main(String[] args) {
        DLL list = new DLL(new DNode(5));
        list.InsertTail(new DNode(3));
        list.InsertTail(new DNode(8));
        list.InsertTail(new DNode(1));
        list.SortedInsert(new DNode(4));

        // System.out.println("Original list:");
        // list.Print();

        // list.Sort();

        System.out.println("Sorted list:");
        list.Print();
    }

    public DNode getHead() {
        return this.head;
    }

    public DNode getTail() {
        return this.tail;
    }
}
