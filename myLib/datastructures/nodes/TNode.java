package myLib.datastructures.nodes;

public class TNode {
    private int data;
    private TNode left, right, parent;
    private int balance;

    public TNode(){
        data = 0;
        balance = 0;
        left = null;
        right = null;
        parent = null;
    }

    public TNode(int data, int balance, TNode P, TNode L, TNode R){
        this.data = data;
        this.balance = balance;
        this.parent = P;
        this.left = L;
        this.right = R;
    }

    public void setData(int data){
        this.data = data;
    }
    public void setBalance(int balance){
        this.balance = balance;
    }

    public void setLeft(TNode left) {
        this.left = left;
    }

    public void setRight(TNode right) {
        this.right = right;
    }

    public void setParent(TNode parent) {
        this.parent = parent;
    }

    public int getData(){
        return data;
    }
    public int getBalance(){
        return balance;
    }
    public TNode getLeft(){
        return left;
    }

    public TNode getRight() {
        return right;
    }

    public TNode getParent() {
        return parent;
    }
    public void print(){
        System.out.println("The data: " + getData());
        System.out.println("The balance: " + getBalance());
        System.out.println("The left: " + getLeft());
        System.out.println("The right: " + getRight());
        System.out.println("The parent: " + getParent());
    }
    public String toString(int data){
        return Integer.toString(data);
    }

    public static void main(String[] args) {
        System.out.println("Testing TNode() Constructor through getters");
        TNode node = new TNode();
        System.out.println("Data expected: 0, acutual: " + node.getData());
        System.out.println("Balance expected: 0, actual: " + node.getBalance());
        System.out.println("Left expected: null, actual: " + node.getLeft());
        System.out.println("Right expected: null, actual: " + node.getRight());
        System.out.println("Parent expected: null, actual: " + node.getParent());
        System.out.println();
        System.out.println("Testing setters");
        TNode node1 = new TNode();
        node.setData(2);
        node.setBalance(-1);
        node.setLeft(node1);
        node.setRight(node1);
        node.setParent(node1);
        System.out.println("Data expected: 2, acutual: " + node.getData());
        System.out.println("Balance expected: -1, actual: " + node.getBalance());
        System.out.println("Left expected: " + node1 + ", actual: " + node.getLeft());
        System.out.println("Right expected: " + node1 + ", actual: " + node.getRight());
        System.out.println("Parent expected: " + node1 + ", actual: " + node.getParent());
        System.out.println();
        System.out.println("Testing TNode(int data, int balance, TNode P, TNode L, TNode R) Constructor");
        TNode node2 = new TNode(3, 2, node, node1, node);
        System.out.println("Data expected: 3, acutual: " + node2.getData());
        System.out.println("Balance expected: 2, actual: " + node2.getBalance());
        System.out.println("Left expected: " + node1 + ", actual: " + node2.getLeft());
        System.out.println("Right expected: " + node + ", actual: " + node2.getRight());
        System.out.println("Parent expected: " + node + ", actual: " + node2.getParent());
        System.out.println();
        System.out.println("Testing Print Method");
        node.print();
        System.out.println();
        node1.print();
        System.out.println();
        System.out.println("Testing toString Method");
        int data = node2.getData();
        String strData = node1.toString(data);
        System.out.println(strData);
    }
}
