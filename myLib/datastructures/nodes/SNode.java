package myLib.datastructures.nodes;

public class SNode {
    private SNode next;
    private int data;

    
    public SNode() {
    }

    public SNode(int toStore) {
        this.data = toStore;
    }

    public SNode getNextNode() {
        return this.next;
    }

    public int getData() {
        return this.data;
    }

    public void setNextNode(SNode newNode) {
        this.next = newNode;
    }

    public void setData(int newData) {
        this.data = newData;
    }
}
