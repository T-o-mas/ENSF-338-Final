package myLib.datastructures.linear;
import myLib.datastructures.nodes.*;

public class StackLL extends SLL {


    public StackLL() {
        this.head = null;
        this.tail = null;
    }

    public StackLL(SNode startNode) {
        this.head = startNode;
        this.tail = startNode;
        this.size += 1;
    }

    public void push(SNode newNode) {
        super.InsertHead(newNode);
    }

    @Override
    public void InsertTail(SNode newNode) {}

    @Override
    public void Insert(SNode newNode, int position) {}

    @Override
    public void SortedInsert(SNode newNode) {}

    public int search(SNode findNode) {
        int position = 0;
        SNode current = this.head;
        while (current != null) {
            if (current == findNode) {
                return position;
            }
            current = current.getNextNode();
            position += 1;
        }
        return -1;
    }

    @Override
    public void DeleteHead() {}

    @Override
    public void DeleteTail() {}

    @Override
    public void Delete(SNode deleteNode) {}

    @Override
    public void Sort() {}

    @Override
    public void Clear() {
        super.Clear();
    }

    @Override
    public void Print() {
        super.Print();
    }

    public SNode pop() {
        SNode temp = this.head;
        super.Delete(temp);
        return temp;
    }

    public SNode peek() {
        return this.head;
    }

    public Boolean Empty() {
        if (this.head == null) {
            return true;
        }
        return false;
    }
}
