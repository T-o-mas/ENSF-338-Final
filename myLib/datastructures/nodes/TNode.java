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

    public static void main(String[] args) {
        TNode node = new TNode();
        //node.print();

    }
}
