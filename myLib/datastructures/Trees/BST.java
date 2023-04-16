package myLib.datastructures.Trees;
import myLib.datastructures.nodes.TNode;

public class BST {
    //node class that defines BST
    private TNode root;

    //Initial empty tree
    public BST(){
        setRoot(null);
    }
    public BST(int val){
        TNode node = new TNode(val, 0, null, null, null);
        setRoot(node);
    }
    public BST(TNode obj){
        setRoot(obj);
    }
    public void setRoot(TNode val) {this.root = val; }
    public TNode getRoot(){ return root; }
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

        //Looking for node
        while (current != null && current.getData() != val) {
            if (val < current.getData()) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        //If node can not be found
        if (current == null) {
            System.out.println("null");
            return null;
        }
        return current;
    }

    public void printInOrder() {
        if (root == null) {
            System.out.println("Tree is Empty");
        } else {
            TNode current = root;

            while (current != null) {
                if (current.getLeft() == null) {
                    System.out.print(current.getData() + " ");
                    current = current.getRight();
                } else {
                    TNode pre = current.getLeft();
                    while (pre.getRight() != null && pre.getRight() != current) {
                        pre = pre.getRight();
                    }

                    if (pre.getRight() == null) {
                        pre.setRight(current);
                        current = current.getLeft();
                    } else {
                        pre.setRight(null);
                        System.out.print(current.getData() + " ");
                        current = current.getRight();
                    }
                }
            }
        }
    }

    public void printBF() {
        if (root == null) {
            System.out.println("Tree is Empty");
        } else {
            DynamicArray<TNode> queue = new DynamicArray<TNode>();
            queue.append(root);

            int currentIndex = 0;
            int currentLevelSize = 1;

            while (currentIndex < queue.getSize()) {
                // Print all the nodes at the current level and add their
                // children to the queue
                for (int i = 0; i < currentLevelSize; i++) {
                    TNode current = queue.get(currentIndex);
                    System.out.print(current.getData() + " ");

                    if (current.getLeft() != null) {
                        queue.append(current.getLeft());
                    }
                    if (current.getRight() != null) {
                        queue.append(current.getRight());
                    }

                    currentIndex++;
                }

                // Print a newline character to start the next level on a new line
                System.out.println();
                currentLevelSize = queue.getSize() - currentIndex;
            }
        }
    }


    public TNode Delete(int val) {
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
            System.out.println(val + " is not found in the tree.");
            return null;
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

        return root;
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
        System.out.println("=== Testing BST() default constructor === \n");
        BST treeV1 = new BST();
        System.out.println("Checking root, expected: null, actual: " + treeV1.getRoot() + "\n");
        System.out.println("=== Testing BST(int val) overload constructor === \n");
        BST treeV2 = new BST(5);
        System.out.println("Checking root, expected data value: 5, actual: " + treeV2.getRoot().getData() + "\n");
        System.out.println("=== Testing BST(TNode obj) overload constructor and printBF() === \n");
        TNode nodeV3 = new TNode(7, 0, null, null, null);
        BST treeV3 = new BST(nodeV3);
        treeV3.printBF();
        TNode nodeV4 = new TNode(11, 0, null, null, null);
        nodeV4.setRight(nodeV3);
        BST treeV4 = new BST(nodeV4);
        System.out.println("\n=== Testing BST(TNode obj) overload constructor with obj having children === \n");
        treeV4.printBF();

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


        System.out.println("\n=== Testing Insert(int val) === \n");
        Tree.printBF();

        System.out.println("\n=== Connection can be further tested by checking each node parent ===\n");
        node1.print();
        System.out.println("TreeNode: " + node1 + "\n");
        node2.print();
        System.out.println("TreeNode: " + node2 + "\n");
        node3.print();
        System.out.println("TreeNode: " + node3 + "\n");
        node4.print();
        System.out.println("TreeNode: " + node4 + "\n");
        node5.print();
        System.out.println("TreeNode: " + node5 + "\n");

        System.out.println("=== Testing Insert(int val) case where val already exists ===\n");
        Tree.Insert(6);
        System.out.println("There should still be one 6 even if it was inserted twice");
        Tree.printBF();

        System.out.println("\n=== Testing Insert(TNode obj) case where obj has no children ===\n");
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
        Tree0.printBF();
        System.out.println("\n=== Connection can be further tested by checking each node parent ===\n");
        nodes1.print();
        System.out.println("TreeNode: " + nodes1 + "\n");
        nodes2.print();
        System.out.println("TreeNode: " + nodes2 + "\n");
        nodes3.print();
        System.out.println("TreeNode: " + nodes3 + "\n");
        nodes4.print();
        System.out.println("TreeNode: " + nodes4 + "\n");
        nodes5.print();
        System.out.println("TreeNode: " + nodes5 + "\n");

        System.out.println("=== Testing Insert(TNode obj) case where obj has children ===\n");
        /*
        should have current structure
                6
               /
              2
             / \
            1   5
               /
              4

        TNode obj is
                8
               / \
              0  11

        Should have this result:
                 6
               /   \
              2      8
             / \    / \
            1   5  0  11
               /
              4

        */
        BST Tree1 = new BST();
        TNode node01 = Tree1.Insert(6);
        TNode node02 = Tree1.Insert(2);
        TNode node03 = Tree1.Insert(1);
        TNode node04 = Tree1.Insert(5);
        TNode node05 = Tree1.Insert(4);

        BST subTree = new BST();
        TNode node11 = subTree.Insert(8);
        TNode node12 = subTree.Insert(11);
        TNode node13 = subTree.Insert(0);
        Tree1.Insert(node11);
        Tree1.printBF();

        System.out.println("\n=== Connection can be further tested by checking each node parent ===\n");
        node01.print();
        System.out.println("TreeNode: " + node01 + "\n");
        node02.print();
        System.out.println("TreeNode: " + node02 + "\n");
        node03.print();
        System.out.println("TreeNode: " + node03 + "\n");
        node04.print();
        System.out.println("TreeNode: " + node04 + "\n");
        node05.print();
        System.out.println("TreeNode: " + node05 + "\n");
        node11.print();
        System.out.println("TreeNode: " + node11 + "\n");
        node12.print();
        System.out.println("TreeNode: " + node12 + "\n");
        node13.print();
        System.out.println("TreeNode: " + node13 + "\n");

        System.out.println("=== Testing Insert(TNode obj) case where obj has data that already exists ===\n");
        Tree1.Insert(node11);
        System.out.println("There should still be one 8 even if it was inserted twice");
        Tree1.printBF();


        System.out.println("\n=== Testing Delete(int val) for 4 ===\n");

        /*
        should have structure - testing Delete(int val)
                6
               /
              2
             / \
            1   5

         */
        Tree.Delete(4);
        Tree.printBF();

        System.out.println("\n===Testing Delete(int val) for 2 ===\n");
        /*
        should have structure - testing Delete(int val)
                 6
                /
               5
              /
             1

        */
        Tree.Delete(2);
        Tree.printBF();
        System.out.println("\n===Testing Delete(int val) if value doesn't exist ===\n");
        Tree.Delete(0);
        System.out.println("\n===Testing Delete(int val) for entire Tree. Output should be Tree is Empty ===\n");
        Tree.Delete(6);
        Tree.Delete(1);
        Tree.Delete(5);
        Tree.printBF();

        System.out.println("\n=== Testing Search(int val) for 2 in the second tree ===\n");
        TNode searchNode = Tree0.Search(2);
        searchNode.print(); //check if node data is correct with tree01 - should have two children

        System.out.println("\n=== Testing Search(int val) if value does not exist ===\n");
        TNode searchNode1 = Tree0.Search(0);

        System.out.println("\n=== Testing printInOrder() ===\n");
        Tree0.printInOrder();
        System.out.println("\n");
        Tree.printInOrder();
        System.out.println();
        System.out.println("\n=== Testing printBF() for both trees ===\n");
        Tree0.printBF();
        System.out.println();
        Tree.printBF();
    }
}
class DynamicArray<T> {
    private int size;
    private T[] arr;

    public DynamicArray() {
        size = 0;
        arr = (T[]) new Object[1];
    }

    public int getSize() {
        return size;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
        }
        return arr[index];
    }

    public void set(int index, T value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
        }
        arr[index] = value;
    }

    public void append(T value) {
        if (size == arr.length) {
            resize(2 * arr.length);
        }
        arr[size] = value;
        size++;
    }

    private void resize(int capacity) {
        T[] copy = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = arr[i];
        }
        arr = copy;
    }

}

