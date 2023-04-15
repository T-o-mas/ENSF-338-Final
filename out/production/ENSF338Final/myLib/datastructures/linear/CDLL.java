package myLib.datastructures.linear;
import myLib.datastructures.nodes.*;

public class CDLL extends DLL {
    
    public CDLL () {
        this.head = null;
        this.tail = null;
    }

    public CDLL(DNode newNode) {
        this.head = newNode;
        this.tail = newNode;
        this.head.setNextNode(this.tail);
        this.head.setPrevNode(this.tail);
        this.tail.setNextNode(this.head);
        this.tail.setPrevNode(this.head);
        this.size = 1;
    }

    public void InsertHead(DNode newNode) {
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
            this.head.setNextNode(this.tail);
            this.head.setPrevNode(this.tail);
            this.tail.setNextNode(this.head);
            this.tail.setPrevNode(this.head);
        }
        else {
            this.head.setPrevNode(newNode);
            newNode.setNextNode(this.head);
            this.head = newNode;
            this.head.setPrevNode(this.tail);
            this.tail.setNextNode(this.head);
        }
        this.size += 1;
    }

    public void InsertTail(DNode newNode) {
        if (this.tail == null) {
            this.head = newNode;
            this.tail = newNode;
            this.head.setNextNode(this.tail);
            this.head.setPrevNode(this.tail);
            this.tail.setNextNode(this.head);
            this.tail.setPrevNode(this.head);
        }
        else {
            this.tail.setNextNode(newNode);
            newNode.setPrevNode(this.tail);
            this.tail = newNode;
            this.tail.setNextNode(this.head);
            this.head.setPrevNode(this.tail);
        }
        this.size += 1;
    }

    public DNode Search(DNode findNode) {
        if (this.head == null) {
            return null;
        }
        DNode current = this.head;
        do {
            if (current == findNode) {
                return current;
            }
            current = current.getNextNode();
        } while (current != this.head);
        return null;
    }

    public void DeleteHead() {
        this.head = this.head.getNextNode();
        this.head.setPrevNode(this.tail);
        this.tail.setNextNode(this.head);
        this.size -= 1;
    }

    public void DeleteTail() {
        this.tail = this.tail.getPrevNode();
        this.tail.setNextNode(this.head);
        this.head.setPrevNode(this.tail);
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
        do {
            if (current == deleteNode) {
                prevNode.setNextNode(current.getNextNode());
                current.getNextNode().setPrevNode(prevNode);
            }
            if (current != null) {
                prevNode = current;
                current = current.getNextNode();
            }
        } while (current != this.head); 
    }

    public void Sort() {
        if (this.head == null || this.head.getNextNode() == this.head) {
            return;
        }
        
        DNode sortedList = null;
        DNode current = this.head;
    
        for(int i = 0; i < this.size; i++) {
            DNode next = current.getNextNode();
            int curVal = current.getData();
            if (sortedList == null || curVal <= sortedList.getData()){
                current.setNextNode(sortedList);
                if (sortedList != null) {
                    sortedList.setPrevNode(current);
                }
                sortedList = current;
            }
            else {
                DNode tempPrev = sortedList;
                DNode tempSorted = sortedList.getNextNode();
                while (tempSorted != null && tempSorted != sortedList && curVal > tempSorted.getData()) {
                    tempPrev = tempSorted;
                    tempSorted = tempSorted.getNextNode();
                }
                current.setNextNode(tempSorted);
                if (tempSorted != null) {
                    tempSorted.setPrevNode(current);
                }
                tempPrev.setNextNode(current);
                current.setPrevNode(tempPrev);
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
        this.head.setPrevNode(this.tail);
        this.tail.setNextNode(this.head);
    }
    
    

    public Boolean isSorted() {
        if (this.head == null || this.head.getNextNode() == null) {
            return true;
        }
        DNode current = this.head;
        do {
            if (current.getData() > current.getNextNode().getData()) {
                return false;
            }
            current = current.getNextNode();
        } while (current.getNextNode() != this.head);
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
        DNode current = this.head;
        do {
            System.out.println(String.valueOf(current.getData()));
            current = current.getNextNode();
        } while (current != this.head);
    }

    
    public static void main(String[] args) {
        CDLL list = new CDLL(new DNode(5));
        list.InsertTail(new DNode(3));
        list.InsertTail(new DNode(8));
        list.InsertTail(new DNode(1));
        list.SortedInsert(new DNode(4));

        System.out.println("Original list:");
        list.Print();

        list.Sort();

        System.out.println("Sorted list:");
        list.Print();
    }
}
