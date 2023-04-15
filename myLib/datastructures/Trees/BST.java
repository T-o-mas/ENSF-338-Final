package myLib.datastructures.Trees;

import myLib.datastructures.nodes.TNode;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;


public class BST {
    //node class that defines BST
    private TNode root;

    //Initial empty tree
    public BST(){
        root = null;
    }
    public BST(int val){
        root = new TNode(val, 0, null, null, null);
    }
    public BST(TNode obj){
        this.root = obj;
    }
    public void setBST(TNode val){
        this.root = val;
    }
    public TNode getBST(){
        return this.root;
    }
    public TNode Insert(int val){
        //Need to make use of the balance.
        TNode insertNode = new TNode(val, 0, null, null, null);
        //if tree is empty
        if (root == null){
            root = insertNode;
            //System.out.println("insert in empty tree");
            return root;
        } else {
            TNode current = root;
            TNode parent = null;
            while (true) {
                parent = current;
                if (val < current.getData()) {
                    //goes to left
                    //System.out.println("goes left");
                    current = current.getLeft();
                    if (current == null) {
                        parent.setLeft(insertNode);
                        insertNode.setParent(parent);
                        return insertNode;
                    }
                } else if (val > current.getData()) {
                    //goes to right
                    //System.out.println("goes right");
                    current = current.getRight();
                    if (current == null) {
                        parent.setRight(insertNode);
                        insertNode.setParent(parent);
                        return insertNode;
                    }
                } else {
                    // Value already exists in the tree
                    //System.out.println("value exists");
                    return null;
                }
            }
        }
    }
    public TNode Insert(TNode obj){
        //Need to make use of the balance.
        //if tree is empty
        if (root == null){
            root = obj;
            //System.out.println("insert in empty tree");
            return root;
        } else {
            TNode current = root;
            TNode parent = null;
            while (true) {
                parent = current;
                if (obj.getData() < current.getData()) {
                    //goes to left
                    //System.out.println("goes left");
                    current = current.getLeft();
                    if (current == null) {
                        parent.setLeft(obj);
                        obj.setParent(parent);
                        return obj;
                    }
                } else if (obj.getData() > current.getData()) {
                    //goes to right
                    //System.out.println("goes right");
                    current = current.getRight();
                    if (current == null) {
                        parent.setRight(obj);
                        obj.setParent(parent);
                        return obj;
                    }
                } else {
                    // Value already exists in the tree
                    //System.out.println("value exists");
                    return null;
                }
            }
        }
    }

    public TNode Search(int val) {
        TNode current = root;

        // Find the node with the data to delete
        while (current != null && current.getData() != val) {
            if (val < current.getData()) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        // If node with the data to delete is not found
        if (current == null) {
            return null;
        }
        return current;
    }

    public void printInOrder() {
        Stack<TNode> s = new Stack<TNode>();
        TNode current = root;

        while (current != null || s.size() > 0) {
            // Traverse down the leftmost node and push all the nodes
            // on the way to the stack
            while (current != null) {
                s.push(current);
                current = current.getLeft();
            }

            // Pop a node from the stack, print its data, and set the
            // current node to its right child
            current = s.pop();
            System.out.print(current.getData() + " ");
            current = current.getRight();
        }
    }

    public void printBF() {
        Queue<TNode> queue = new LinkedList<TNode>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            // Print all the nodes at the current level and add their
            // children to the queue
            for (int i = 0; i < levelSize; i++) {
                TNode current = queue.poll();
                System.out.print(current.getData() + " ");

                if (current.getLeft() != null) {
                    queue.add(current.getLeft());
                }
                if (current.getRight() != null) {
                    queue.add(current.getRight());
                }
            }

            // Print a newline character to start the next level on a new line
            System.out.println();
        }
    }



    public boolean Delete(int val) {
        TNode current = root;
        TNode parent = null;
        boolean isLeftChild = true;

        // Find the node with the data to delete
        while (current != null && current.getData() != val) {
            parent = current;
            if (val < current.getData()) {
                current = current.getLeft();
                isLeftChild = true;
            } else {
                current = current.getRight();
                isLeftChild = false;
            }
        }

        // If node with the data to delete is not found
        if (current == null) {
            //System.out.println("Node with data " + val + " not found in the tree.");
            return false;
        }

        // Case 1: Node to be deleted has no children
        if (current.getLeft() == null && current.getRight() == null) {
            //System.out.println("node to be deleted has no children");
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        }

        // Case 2: Node to be deleted has one child
        else if (current.getRight() == null) {
            //System.out.println("node to be deleted has one children");
            if (current == root) {
                root = current.getLeft();
            } else if (isLeftChild) {
                parent.setLeft(current.getLeft());
            } else {
                parent.setRight(current.getLeft());
            }
        } else if (current.getLeft() == null) {
            if (current == root) {
                root = current.getRight();
            } else if (isLeftChild) {
                parent.setLeft(current.getRight());
            } else {
                parent.setRight(current.getRight());
            }
        }

        // Case 3: Node to be deleted has two children
        else {
            //System.out.println("node to be deleted has two children");
            TNode successor = getSuccessor(current);

            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.setLeft(successor);
            } else {
                parent.setRight(successor);
            }
            successor.setLeft(current.getLeft());
        }

        return true;
    }

    //Added helper function for Delete(int val)
    private TNode getSuccessor(TNode node) {
        TNode parent = node;
        TNode successor = node.getRight();
        while (successor.getLeft() != null) {
            parent = successor;
            successor = successor.getLeft();
        }
        if (successor != node.getRight()) {
            parent.setLeft(successor.getRight());
            successor.setRight(node.getRight());
        }
        return successor;
    }


    public static void main(String args[]){
        /*
        should have structure - testing Insert(int val)
                6
               /
              2
             / \
            1   5
               /
              4

         */
        BST Tree = new BST();
        TNode node1 = Tree.Insert(6);
        TNode node2 = Tree.Insert(2);
        TNode node3 = Tree.Insert(1);
        TNode node4 = Tree.Insert(5);
        TNode node5 = Tree.Insert(4);

        node1.print();
        System.out.println("TreeNode: " + node1);
        System.out.println("");
        node2.print();
        System.out.println("TreeNode: " + node2);
        System.out.println("");
        node3.print();
        System.out.println("TreeNode: " + node3);
        System.out.println("");
        node4.print();
        System.out.println("TreeNode: " + node4);
        System.out.println("");
        node5.print();
        System.out.println("TreeNode: " + node5);
        System.out.println("");
        System.out.println("=============Testing Insert(TNode obj)=============");
        System.out.println("");


         /*
        should have structure - testing Insert(TNode obj)
                6
               /
              2
             / \
            1   5
               /
              4

         */
        BST Tree0 = new BST();
        TNode nodes1 = new TNode(6, 0, null, null, null);
        Tree0.Insert(nodes1);
        TNode nodes2 = new TNode(2, 0, null, null, null);
        Tree0.Insert(nodes2);
        TNode nodes3 = new TNode(1, 0, null, null, null);
        Tree0.Insert(nodes3);
        TNode nodes4 = new TNode(5, 0, null, null, null);
        Tree0.Insert(nodes4);
        TNode nodes5 = new TNode(4, 0, null, null, null);
        Tree0.Insert(nodes5);

        nodes1.print();
        System.out.println("TreeNode: " + nodes1);
        System.out.println("");
        nodes2.print();
        System.out.println("TreeNode: " + nodes2);
        System.out.println("");
        nodes3.print();
        System.out.println("TreeNode: " + nodes3);
        System.out.println("");
        nodes4.print();
        System.out.println("TreeNode: " + nodes4);
        System.out.println("");
        nodes5.print();
        System.out.println("TreeNode: " + nodes5);
        System.out.println("");
        System.out.println("=============Testing Delete(int val)============= for 4");
        System.out.println("");

        /*
        deleting value 4 for Tree ---NEED TO FIX TESTS FOR DELETE
        should have structure - testing Delete(int val)
                6
               /
              2
             / \
            1   5

         */
        Tree.Delete(4);
        node1.print();
        System.out.println("TreeNode: " + node1);
        System.out.println("");
        node2.print();
        System.out.println("TreeNode: " + node2);
        System.out.println("");
        node3.print();
        System.out.println("TreeNode: " + node3);
        System.out.println("");
        node4.print();
        System.out.println("TreeNode: " + node4);
        System.out.println("");
        node5.print();
        System.out.println("TreeNode: " + node5);
        System.out.println("");
        System.out.println("=============Testing Delete(int val)============= for 2");
        System.out.println("");
        /*
        deleting value 2 for Tree
        should have structure - testing Delete(int val)
                 6
                /
               5
              /
             1

        */
        Tree.Delete(2);
        node1.print();
        System.out.println("TreeNode: " + node1); //6
        System.out.println("");
        node2.print();
        System.out.println("TreeNode: " + node2); //2
        System.out.println("");
        node3.print();
        System.out.println("TreeNode: " + node3); //1
        System.out.println("");
        node4.print();
        System.out.println("TreeNode: " + node4); //5
        System.out.println("");
        node5.print();
        System.out.println("TreeNode: " + node5); //4
        System.out.println("");

        Tree.Delete(0);

        System.out.println("");
        System.out.println("=============Testing Search(int val)============= for 2");
        System.out.println("");
        TNode searchNode = Tree0.Search(2);
        searchNode.print(); //check if node data is correct with tree01 - should have two children
        System.out.println("Tree node: " + searchNode); //should give output myLib.datastructures.nodes.TNode@a09ee92
        System.out.println("");

        TNode searchNode1 = Tree0.Search(0); //test value that does not exist
        searchNode.print();
        System.out.println("Tree node: " + searchNode); //should give no output ie null
        System.out.println("");
        System.out.println("=============Testing printInOrder()=============");
        System.out.println("");
        Tree0.printInOrder(); //output should be 1 2 4 5 6
        System.out.println("");
        Tree.printInOrder(); //output should be 1 5 6 due to value 2 and 4 being deleted
        System.out.println("");
        System.out.println("=============Testing printBF()=============");
        System.out.println("");
        Tree0.printBF();
        System.out.println();
        Tree.printBF();
        System.out.println("");
        System.out.println("=============Testing Sub tree insertion with Insert(TNode obj)=============");
        System.out.println("");
        /*
        should have structure - testing Insert(int val)
                6
               /
              2
             / \
            1   5
               /
              4

         */
        BST Tree1 = new BST();
        TNode node01 = Tree1.Insert(6);
        TNode node02 = Tree1.Insert(2);
        TNode node03 = Tree1.Insert(1);
        TNode node04 = Tree1.Insert(5);
        TNode node05 = Tree1.Insert(4);
         /*
        should have structure - testing Insert(int val)
                8
               / \
              0  11


         */
        BST subTree = new BST();
        TNode node11 = subTree.Insert(8);
        TNode node16 = subTree.Insert(11);
        TNode node12 = subTree.Insert(0);
        subTree.printBF();
        Tree1.Insert(node11);
        Tree1.printBF();
        /*
        Should have this result:
                 6
               /   \
              2      8
             / \    / \
            1   5  0  11
               /
              4
         */

    }

}
