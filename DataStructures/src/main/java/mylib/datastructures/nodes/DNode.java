package main.java.mylib.datastructures.nodes;

public class DNode {
    private DNode prev;
    private DNode next;
    private int data;
    
    public DNode() {
    }

    public DNode(int toStore) {
        this.data = toStore;
    }

    public DNode getPrevNode() {
        return this.prev;
    }

    public DNode getNextNode() {
        return this.next;
    }

    public int getData() {
        return this.data;
    }

    public void setPrevNode(DNode newNode) {
        this.prev = newNode;
    }

    public void setNextNode(DNode newNode) {
        this.next = newNode;
    }

    public void setData(int newData) {
        this.data = newData;
    }
}
