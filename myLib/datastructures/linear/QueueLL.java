package myLib.datastructures.linear;
import myLib.datastructures.nodes.*;

public class QueueLL extends SLL {
    


    public QueueLL() {
        this.head = null;
        this.tail = null;
    }

    public QueueLL(SNode startNode) {
        this.head = startNode;
        this.tail = startNode;
        this.size += 1;
    }

    @Override
    public void InsertHead(SNode newNode) {}

    public void Enqueue(SNode newNode) {
        super.InsertTail(newNode);
    }

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
    public void Sort() {
        super.Sort();
    }

    @Override
    public void Clear() {
        super.Clear();
    }

    @Override
    public void Print() {
        super.Print();
    }

    public SNode Dequeue() {
        if (this.head == null) {
            return null;
        }
        else {
            SNode temp = this.head;
            this.head = temp.getNextNode();
            if (this.head ==null) {
                this.tail = null;
            }
            this.size -= 1;
            return temp;
        }
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