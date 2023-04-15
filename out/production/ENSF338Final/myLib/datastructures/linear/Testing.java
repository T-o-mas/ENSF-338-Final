package myLib.datastructures.linear;
import myLib.datastructures.nodes.*;

import static org.junit.Assert.*;
import org.junit.*;

public class Testing {
    @Test
    public void testSLL() {
        SLL empty = new SLL();
        assertEquals("The constructor did not create a null object", null, empty.getHead());

        SLL list = new SLL(new SNode(3));
        assertEquals("The constructor did not create with the correct value", 3, list.getHead().getData());
        
        list.InsertTail(new SNode(5));
        assertEquals("Something went wrong with InsertTail", 3, list.getHead().getData());
        assertEquals("Something went wrong with InsertTail", 5, list.getTail().getData());

        list.InsertHead(new SNode(8));
        assertEquals("Something went wrong with InsertHead", 8, list.getHead().getData());
        assertEquals("Something went wrong with InsertHead", 3, list.getHead().getNextNode().getData());
        assertEquals("Something went wrong with InsertHead", 5, list.getTail().getData());

        SNode willDelete = new SNode(4);
        list.SortedInsert(willDelete);
        assertEquals("Something went wrong with SortedInsert", 3, list.getHead().getData());
        assertEquals("Something went wrong with SortedInsert", 4, list.getHead().getNextNode().getData());
        assertEquals("Something went wrong with SortedInsert", 5, list.getHead().getNextNode().getNextNode().getData());
        assertEquals("Something went wrong with SortedInsert", 8, list.getTail().getData());

        list.Insert(new SNode(1), 3);
        assertEquals("Something went wrong with Insert", 3, list.getHead().getData());
        assertEquals("Something went wrong with Insert", 4, list.getHead().getNextNode().getData());
        assertEquals("Something went wrong with Insert", 5, list.getHead().getNextNode().getNextNode().getData());
        assertEquals("Something went wrong with Insert", 1, list.getHead().getNextNode().getNextNode().getNextNode().getData());
        assertEquals("Something went wrong with Insert", 8, list.getTail().getData());

        list.Sort();
        assertEquals("Something went wrong with Sort", 1, list.getHead().getData());
        assertEquals("Something went wrong with Sort", 3, list.getHead().getNextNode().getData());
        assertEquals("Something went wrong with Sort", 4, list.getHead().getNextNode().getNextNode().getData());
        assertEquals("Something went wrong with Sort", 5, list.getHead().getNextNode().getNextNode().getNextNode().getData());
        assertEquals("Something went wrong with Sort", 8, list.getTail().getData());

        SNode findNode = new SNode(5);
        SLL newSLL = new SLL(findNode);

        assertEquals("Did not find", findNode, newSLL.Search(findNode));
        assertEquals("Found but should not", null, newSLL.Search(new SNode(1)));

        list.DeleteHead();
        assertEquals("Something went wrong with DeleteHead", 3, list.getHead().getData());
        assertEquals("Something went wrong with DeleteHead", 4, list.getHead().getNextNode().getData());
        assertEquals("Something went wrong with DeleteHead", 5, list.getHead().getNextNode().getNextNode().getData());
        assertEquals("Something went wrong with DeleteHead", 8, list.getTail().getData());

        list.DeleteTail();
        assertEquals("Something went wrong with DeleteTail", 3, list.getHead().getData());
        assertEquals("Something went wrong with DeleteTail", 4, list.getHead().getNextNode().getData());
        assertEquals("Something went wrong with DeleteTail", 5, list.getTail().getData());

        list.Delete(willDelete);
        assertEquals("Something went wrong with Delete", 3, list.getHead().getData());
        assertEquals("Something went wrong with Delete", 5, list.getTail().getData());

        list.Clear();
        assertEquals("Clear did not clear", null, list.getHead());

        SLL newList = new SLL();
        SNode notStored = new SNode(1);
        newList.Insert(notStored, 1);
        assertEquals(null, newList.Search(notStored));

        SNode emptyStore = new SNode(2);
        newList.Insert(emptyStore, 0);
        assertEquals(emptyStore, newList.Search(emptyStore));

        SNode headStore = new SNode(3);
        newList.Insert(headStore, 0);
        assertEquals(headStore, newList.Search(headStore));

        SNode tailStore = new SNode(4);
        newList.Insert(tailStore, 2);
        assertEquals(tailStore, newList.Search(tailStore));
    }

    @Test
    public void testCSLL() {
        CSLL empty = new CSLL();
        assertEquals("The constructor did not create a null object", null, empty.getHead());

        CSLL list = new CSLL(new SNode(7));
        assertEquals("Constructor did not create node", 7, list.getHead().getData());
        assertEquals("List does not loop", 7, list.getHead().getNextNode().getData());

        SNode willDelete = new SNode(5);
        list.InsertTail(willDelete);
        assertEquals("Something went wrong with InsertTail", 7, list.getHead().getData());
        assertEquals("Something went wrong with InsertTail", 5, list.getTail().getData());
        assertEquals("Does not loop correctly", 7, list.getTail().getNextNode().getData());

        list.InsertHead(new SNode(8));
        assertEquals("Something went wrong with InsertHead", 8, list.getHead().getData());
        assertEquals("Something went wrong with InsertHead", 7, list.getHead().getNextNode().getData());
        assertEquals("Something went wrong with InsertHead", 5, list.getTail().getData());
        assertEquals("Does not loop correctly", 8, list.getTail().getNextNode().getData());

        list.SortedInsert(new SNode(4));
        assertEquals("Something went wrong with SortedInsert", 4, list.getHead().getData());
        assertEquals("Something went wrong with SortedInsert", 5, list.getHead().getNextNode().getData());
        assertEquals("Something went wrong with SortedInsert", 7, list.getHead().getNextNode().getNextNode().getData());
        assertEquals("Something went wrong with SortedInsert", 8, list.getTail().getData());
        assertEquals("Does not loop correctly", 4, list.getTail().getNextNode().getData());

        list.Insert(new SNode(2), 2);
        assertEquals("Something went wrong with Insert", 4, list.getHead().getData());
        assertEquals("Something went wrong with Insert", 5, list.getHead().getNextNode().getData());
        assertEquals("Something went wrong with Insert", 2, list.getHead().getNextNode().getNextNode().getData());
        assertEquals("Something went wrong with Insert", 7, list.getHead().getNextNode().getNextNode().getNextNode().getData());
        assertEquals("Something went wrong with Insert", 8, list.getTail().getData());
        assertEquals("Does not loop correctly", 4, list.getTail().getNextNode().getData());

        list.Sort();
        assertEquals("Something went wrong with Sort", 2, list.getHead().getData());
        assertEquals("Something went wrong with Sort", 4, list.getHead().getNextNode().getData());
        assertEquals("Something went wrong with Sort", 5, list.getHead().getNextNode().getNextNode().getData());
        assertEquals("Something went wrong with Sort", 7, list.getHead().getNextNode().getNextNode().getNextNode().getData());
        assertEquals("Something went wrong with Sort", 8, list.getTail().getData());
        assertEquals("Does not loop correctly", 2, list.getTail().getNextNode().getData());

        SNode findNode = new SNode(5);
        CSLL newCSLL = new CSLL(findNode);

        assertEquals("Did not find", findNode, newCSLL.Search(findNode));
        assertEquals("Found but should not", null, newCSLL.Search(new SNode(1)));

        list.DeleteHead();
        assertEquals("Something went wrong with DeleteHead", 4, list.getHead().getData());
        assertEquals("Something went wrong with DeleteHead", 5, list.getHead().getNextNode().getData());
        assertEquals("Something went wrong with DeleteHead", 7, list.getHead().getNextNode().getNextNode().getData());
        assertEquals("Something went wrong with DeleteHead", 8, list.getTail().getData());
        assertEquals("Does not loop correctly", 4, list.getTail().getNextNode().getData());

        list.DeleteTail();
        assertEquals("Something went wrong with DeleteTail", 4, list.getHead().getData());
        assertEquals("Something went wrong with DeleteTail", 5, list.getHead().getNextNode().getData());
        assertEquals("Something went wrong with DeleteTail", 7, list.getTail().getData());
        assertEquals("Does not loop correctly", 4, list.getTail().getNextNode().getData());

        list.Delete(willDelete);
        assertEquals("Something went wrong with Delete", 4, list.getHead().getData());
        assertEquals("Something went wrong with Delete", 7, list.getTail().getData());
        assertEquals("Does not loop correctly", 4, list.getTail().getNextNode().getData());

        list.Clear();
        assertEquals("Clear did not clear", null, list.getHead());

        CSLL newList = new CSLL();
        SNode notStored = new SNode(1);
        newList.Insert(notStored, 1);
        assertEquals(null, newList.Search(notStored));

        SNode emptyStore = new SNode(2);
        newList.Insert(emptyStore, 0);
        assertEquals(emptyStore, newList.Search(emptyStore));

        SNode headStore = new SNode(3);
        newList.Insert(headStore, 0);
        assertEquals(headStore, newList.Search(headStore));

        SNode tailStore = new SNode(4);
        newList.Insert(tailStore, 2);
        assertEquals(tailStore, newList.Search(tailStore));
    }

    @Test
    public void testDLL() {
        DLL empty = new DLL();
        assertEquals(null, empty.getHead());

        DLL list = new DLL(new DNode(7));
        assertEquals(7, list.getHead().getData());
        assertEquals(7, list.getTail().getData());

        DNode willDelete = new DNode(5);
        list.InsertTail(willDelete);
        assertEquals(7, list.getHead().getData());
        assertEquals(5, list.getHead().getNextNode().getData());
        assertEquals(5, list.getTail().getData());
        assertEquals(7, list.getTail().getPrevNode().getData());

        list.InsertHead(new DNode(8));
        assertEquals(8, list.getHead().getData());
        assertEquals(7, list.getHead().getNextNode().getData());
        assertEquals(5, list.getHead().getNextNode().getNextNode().getData());
        assertEquals(5, list.getTail().getData());
        assertEquals(7, list.getTail().getPrevNode().getData());
        assertEquals(8, list.getTail().getPrevNode().getPrevNode().getData());

        list.SortedInsert(new DNode(4));
        assertEquals(4, list.getHead().getData());
        assertEquals(5, list.getHead().getNextNode().getData());
        assertEquals(7, list.getHead().getNextNode().getNextNode().getData());
        assertEquals(8, list.getHead().getNextNode().getNextNode().getNextNode().getData());
        assertEquals(8, list.getTail().getData());
        assertEquals(7, list.getTail().getPrevNode().getData());
        assertEquals(5, list.getTail().getPrevNode().getPrevNode().getData());
        assertEquals(4, list.getTail().getPrevNode().getPrevNode().getPrevNode().getData());

        list.Insert(new DNode(2), 2);
        assertEquals(4, list.getHead().getData());
        assertEquals(5, list.getHead().getNextNode().getData());
        assertEquals(2, list.getHead().getNextNode().getNextNode().getData());
        assertEquals(7, list.getHead().getNextNode().getNextNode().getNextNode().getData());
        assertEquals(8, list.getHead().getNextNode().getNextNode().getNextNode().getNextNode().getData());
        assertEquals(8, list.getTail().getData());
        assertEquals(7, list.getTail().getPrevNode().getData());
        assertEquals(2, list.getTail().getPrevNode().getPrevNode().getData());
        assertEquals(5, list.getTail().getPrevNode().getPrevNode().getPrevNode().getData());
        assertEquals(4, list.getTail().getPrevNode().getPrevNode().getPrevNode().getPrevNode().getData());

        list.Sort();
        assertEquals(2, list.getHead().getData());
        assertEquals(4, list.getHead().getNextNode().getData());
        assertEquals(5, list.getHead().getNextNode().getNextNode().getData());
        assertEquals(7, list.getHead().getNextNode().getNextNode().getNextNode().getData());
        assertEquals(8, list.getHead().getNextNode().getNextNode().getNextNode().getNextNode().getData());
        assertEquals(8, list.getTail().getData());
        assertEquals(7, list.getTail().getPrevNode().getData());
        assertEquals(5, list.getTail().getPrevNode().getPrevNode().getData());
        assertEquals(4, list.getTail().getPrevNode().getPrevNode().getPrevNode().getData());
        assertEquals(2, list.getTail().getPrevNode().getPrevNode().getPrevNode().getPrevNode().getData());

        DNode findNode = new DNode(5);
        DLL newDLL = new DLL(findNode);

        assertEquals("Did not find", findNode, newDLL.Search(findNode));
        assertEquals("Found but should not", null, newDLL.Search(new DNode(1)));

        list.DeleteHead();
        assertEquals(4, list.getHead().getData());
        assertEquals(5, list.getHead().getNextNode().getData());
        assertEquals(7, list.getHead().getNextNode().getNextNode().getData());
        assertEquals(8, list.getHead().getNextNode().getNextNode().getNextNode().getData());
        assertEquals(8, list.getTail().getData());
        assertEquals(7, list.getTail().getPrevNode().getData());
        assertEquals(5, list.getTail().getPrevNode().getPrevNode().getData());
        assertEquals(4, list.getTail().getPrevNode().getPrevNode().getPrevNode().getData());

        list.DeleteTail();
        assertEquals(4, list.getHead().getData());
        assertEquals(5, list.getHead().getNextNode().getData());
        assertEquals(7, list.getHead().getNextNode().getNextNode().getData());
        assertEquals(7, list.getTail().getData());
        assertEquals(5, list.getTail().getPrevNode().getData());
        assertEquals(4, list.getTail().getPrevNode().getPrevNode().getData());

        list.Delete(willDelete);
        assertEquals(4, list.getHead().getData());
        assertEquals(7, list.getHead().getNextNode().getData());
        assertEquals(7, list.getTail().getData());
        assertEquals(4, list.getTail().getPrevNode().getData());

        list.Clear();
        assertEquals("Clear did not clear", null, list.getHead());

        DLL newList = new DLL();
        DNode notStored = new DNode(1);
        newList.Insert(notStored, 1);
        assertEquals(null, newList.Search(notStored));

        DNode emptyStore = new DNode(2);
        newList.Insert(emptyStore, 0);
        assertEquals(emptyStore, newList.Search(emptyStore));

        DNode headStore = new DNode(3);
        newList.Insert(headStore, 0);
        assertEquals(headStore, newList.Search(headStore));

        DNode tailStore = new DNode(4);
        newList.Insert(tailStore, 2);
        assertEquals(tailStore, newList.Search(tailStore));
    }

    @Test
    public void testCDLL() {
        CDLL empty = new CDLL();
        assertEquals(null, empty.getHead());

        CDLL list = new CDLL(new DNode(7));
        assertEquals(7, list.getHead().getData());
        assertEquals(7, list.getHead().getNextNode().getData());
        assertEquals(7, list.getHead().getPrevNode().getData());
        assertEquals(7, list.getTail().getData());
        assertEquals(7, list.getTail().getNextNode().getData());
        assertEquals(7, list.getTail().getPrevNode().getData());

        DNode willDelete = new DNode(5);
        list.InsertTail(willDelete);
        assertEquals(7, list.getHead().getData());
        assertEquals(5, list.getHead().getNextNode().getData());
        assertEquals(7, list.getHead().getNextNode().getNextNode().getData());
        assertEquals(5, list.getHead().getPrevNode().getData());

        assertEquals(5, list.getTail().getData());
        assertEquals(7, list.getTail().getNextNode().getData());
        assertEquals(5, list.getTail().getPrevNode().getPrevNode().getData());
        assertEquals(7, list.getTail().getPrevNode().getData());

        list.InsertHead(new DNode(8));
        assertEquals(8, list.getHead().getData());
        assertEquals(7, list.getHead().getNextNode().getData());
        assertEquals(5, list.getHead().getNextNode().getNextNode().getData());
        assertEquals(8, list.getHead().getNextNode().getNextNode().getNextNode().getData());
        assertEquals(5, list.getHead().getPrevNode().getData());

        assertEquals(5, list.getTail().getData());
        assertEquals(7, list.getTail().getPrevNode().getData());
        assertEquals(8, list.getTail().getPrevNode().getPrevNode().getData());
        assertEquals(5, list.getTail().getPrevNode().getPrevNode().getPrevNode().getData());
        assertEquals(8, list.getTail().getNextNode().getData());

        list.SortedInsert(new DNode(4));
        assertEquals(4, list.getHead().getData());
        assertEquals(5, list.getHead().getNextNode().getData());
        assertEquals(7, list.getHead().getNextNode().getNextNode().getData());
        assertEquals(8, list.getHead().getNextNode().getNextNode().getNextNode().getData());
        assertEquals(4, list.getHead().getNextNode().getNextNode().getNextNode().getNextNode().getData());
        assertEquals(8, list.getHead().getPrevNode().getData());


        assertEquals(8, list.getTail().getData());
        assertEquals(7, list.getTail().getPrevNode().getData());
        assertEquals(5, list.getTail().getPrevNode().getPrevNode().getData());
        assertEquals(4, list.getTail().getPrevNode().getPrevNode().getPrevNode().getData());
        assertEquals(8, list.getTail().getPrevNode().getPrevNode().getPrevNode().getPrevNode().getData());
        assertEquals(4, list.getTail().getNextNode().getData());

        list.Insert(new DNode(2), 2);
        assertEquals(4, list.getHead().getData());
        assertEquals(5, list.getHead().getNextNode().getData());
        assertEquals(2, list.getHead().getNextNode().getNextNode().getData());
        assertEquals(7, list.getHead().getNextNode().getNextNode().getNextNode().getData());
        assertEquals(8, list.getHead().getNextNode().getNextNode().getNextNode().getNextNode().getData());
        assertEquals(4, list.getHead().getNextNode().getNextNode().getNextNode().getNextNode().getNextNode().getData());
        assertEquals(8, list.getHead().getPrevNode().getData());

        assertEquals(8, list.getTail().getData());
        assertEquals(7, list.getTail().getPrevNode().getData());
        assertEquals(2, list.getTail().getPrevNode().getPrevNode().getData());
        assertEquals(5, list.getTail().getPrevNode().getPrevNode().getPrevNode().getData());
        assertEquals(4, list.getTail().getPrevNode().getPrevNode().getPrevNode().getPrevNode().getData());
        assertEquals(8, list.getTail().getPrevNode().getPrevNode().getPrevNode().getPrevNode().getPrevNode().getData());
        assertEquals(4, list.getTail().getNextNode().getData());

        list.Sort();
        assertEquals(2, list.getHead().getData());
        assertEquals(4, list.getHead().getNextNode().getData());
        assertEquals(5, list.getHead().getNextNode().getNextNode().getData());
        assertEquals(7, list.getHead().getNextNode().getNextNode().getNextNode().getData());
        assertEquals(8, list.getHead().getNextNode().getNextNode().getNextNode().getNextNode().getData());
        assertEquals(2, list.getHead().getNextNode().getNextNode().getNextNode().getNextNode().getNextNode().getData());
        assertEquals(8, list.getHead().getPrevNode().getData());

        assertEquals(8, list.getTail().getData());
        assertEquals(7, list.getTail().getPrevNode().getData());
        assertEquals(5, list.getTail().getPrevNode().getPrevNode().getData());
        assertEquals(4, list.getTail().getPrevNode().getPrevNode().getPrevNode().getData());
        assertEquals(2, list.getTail().getPrevNode().getPrevNode().getPrevNode().getPrevNode().getData());
        assertEquals(8, list.getTail().getPrevNode().getPrevNode().getPrevNode().getPrevNode().getPrevNode().getData());
        assertEquals(2, list.getTail().getNextNode().getData());

        DNode findNode = new DNode(5);
        CDLL newCDLL = new CDLL(findNode);

        assertEquals("Did not find", findNode, newCDLL.Search(findNode));
        assertEquals("Found but should not", null, newCDLL.Search(new DNode(1)));

        list.DeleteHead();
        assertEquals(4, list.getHead().getData());
        assertEquals(5, list.getHead().getNextNode().getData());
        assertEquals(7, list.getHead().getNextNode().getNextNode().getData());
        assertEquals(8, list.getHead().getNextNode().getNextNode().getNextNode().getData());
        assertEquals(4, list.getHead().getNextNode().getNextNode().getNextNode().getNextNode().getData());
        assertEquals(8, list.getHead().getPrevNode().getData());

        assertEquals(8, list.getTail().getData());
        assertEquals(7, list.getTail().getPrevNode().getData());
        assertEquals(5, list.getTail().getPrevNode().getPrevNode().getData());
        assertEquals(4, list.getTail().getPrevNode().getPrevNode().getPrevNode().getData());
        assertEquals(8, list.getTail().getPrevNode().getPrevNode().getPrevNode().getPrevNode().getData());
        assertEquals(4, list.getTail().getNextNode().getData());

        list.DeleteTail();
        assertEquals(4, list.getHead().getData());
        assertEquals(5, list.getHead().getNextNode().getData());
        assertEquals(7, list.getHead().getNextNode().getNextNode().getData());
        assertEquals(4, list.getHead().getNextNode().getNextNode().getNextNode().getData());
        assertEquals(7, list.getHead().getPrevNode().getData());

        assertEquals(7, list.getTail().getData());
        assertEquals(5, list.getTail().getPrevNode().getData());
        assertEquals(4, list.getTail().getPrevNode().getPrevNode().getData());
        assertEquals(7, list.getTail().getPrevNode().getPrevNode().getPrevNode().getData());
        assertEquals(4, list.getTail().getNextNode().getData());

        list.Delete(willDelete);
        assertEquals(4, list.getHead().getData());
        assertEquals(7, list.getHead().getNextNode().getData());
        assertEquals(4, list.getHead().getNextNode().getNextNode().getData());
        assertEquals(7, list.getHead().getPrevNode().getData());

        assertEquals(7, list.getTail().getData());
        assertEquals(4, list.getTail().getNextNode().getData());
        assertEquals(7, list.getTail().getPrevNode().getPrevNode().getData());
        assertEquals(4, list.getTail().getPrevNode().getData());

        list.Clear();
        assertEquals("Clear did not clear", null, list.getHead());

        CDLL newList = new CDLL();
        DNode notStored = new DNode(1);
        newList.Insert(notStored, 1);
        assertEquals(null, newList.Search(notStored));

        DNode emptyStore = new DNode(2);
        newList.Insert(emptyStore, 0);
        assertEquals(emptyStore, newList.Search(emptyStore));

        DNode headStore = new DNode(3);
        newList.Insert(headStore, 0);
        assertEquals(headStore, newList.Search(headStore));

        DNode tailStore = new DNode(4);
        newList.Insert(tailStore, 2);
        assertEquals(tailStore, newList.Search(tailStore));
    }

    @Test
    public void StackLL() {
        StackLL empty = new StackLL();
        assertEquals("The constructor did not create a null object", null, empty.getHead());

        SNode node1 = new SNode(3);
        SNode node2 = new SNode(1);
        StackLL list = new StackLL(node1);
        assertEquals("The constructor did not create with the correct value", 3, list.getHead().getData());

        list.push(node2);
        assertEquals(1, list.getHead().getData());

        assertEquals(1, list.search(node1));
        assertEquals(0, list.search(node2));

        list.Clear();
        assertEquals(-1, list.search(node1));
        assertEquals(-1, list.search(node2));

        list.push(node1);
        list.push(node2);

        assertEquals(node2, list.peek());
        assertEquals(node2, list.pop());

        assertEquals(node1, list.peek());
        assertEquals(node1, list.pop());

        assertEquals(-1, list.search(node1));
        assertEquals(-1, list.search(node2));

        assertEquals(true, list.Empty());
    }

    @Test
    public void QueueLL() {
        QueueLL empty = new QueueLL();
        assertEquals("The constructor did not create a null object", null, empty.getHead());

        SNode node1 = new SNode(3);
        SNode node2 = new SNode(1);
        QueueLL list = new QueueLL(node1);
        assertEquals("The constructor did not create with the correct value", 3, list.getHead().getData());

        list.Enqueue(node2);
        assertEquals(3, list.getHead().getData());

        assertEquals(node1, list.peek());
        assertEquals(node1, list.Dequeue());

        assertEquals(node2, list.peek());
        assertEquals(node2, list.Dequeue());

        assertEquals(-1, list.search(node1));
        assertEquals(-1, list.search(node2));

        list.Enqueue(node1);
        list.Enqueue(node2);

        assertEquals(0, list.search(node1));
        assertEquals(1, list.search(node2));

        list.Clear();

        assertEquals(true, list.Empty());
    }
}
