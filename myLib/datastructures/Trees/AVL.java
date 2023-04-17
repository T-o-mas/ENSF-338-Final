package myLib.datastructures.trees;

import myLib.datastructures.nodes.TNode;

public class AVL extends BST{
    private TNode root;

    public AVL() {
        this.root = null;
    }


    public AVL(int val) {
        TNode node = new TNode(val, 0, null, null, null);
        setRoot(node);
        super.setRoot(node);
    }


    public AVL(TNode obj) {
        if (obj == null) {
            return;
        }
        super.setRoot(obj);
        setRoot(obj);
    }

    @Override
    public void setRoot(TNode val) {
        this.root = balance(val);
    }

    @Override
    public TNode getRoot(){ return root; }

    public int height(TNode node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    public int getBalance(TNode N) {
        if (N == null) return 0;
        return height(N.getRight()) - height(N.getLeft());
    }


    @Override
    public TNode Insert(int val) {
        TNode addedNode = super.Insert(val);
        return balance(addedNode);
    }

    @Override
    public TNode Insert(TNode obj) {
        TNode addedNode = super.Insert(obj);
        return balance(addedNode);
    }


    private TNode balance(TNode node) {
        TNode currentNode = node;
        while (currentNode != null) {
            int balanceFactor = getBalance(currentNode);
            if (balanceFactor > 1) {
                // Perform right rotation (RR) or left-right rotation (RL)
                if (getBalance(currentNode.getRight()) < 0) {
                    currentNode.setRight(rightRotate(currentNode.getRight()));
                }
                currentNode = leftRotate(currentNode);
            } else if (balanceFactor < -1) {
                // Perform left rotation (LL) or right-left rotation (LR)

                if (getBalance(currentNode.getLeft()) > 0) {
                    currentNode.setLeft(leftRotate(currentNode.getLeft()));
                }
                currentNode = rightRotate(currentNode);
            }
            currentNode = currentNode.getParent();
        }
        return node;
    }
    private TNode leftRotate(TNode x) {
        TNode y = x.getRight();
        TNode z = y.getLeft();

        // Perform the rotation
        y.setLeft(x);
        x.setRight(z);
        y.setParent(x.getParent());
        x.setParent(y);
        if (z != null) {
            z.setParent(x);
        }
        if (y.getParent() == null) {
            root = y;
            super.setRoot(y); //used for inheritance. Sets the root in BST so print functions use corrected value
        } else if (y.getParent().getLeft() == x) {
            y.getParent().setLeft(y);
        } else {
            y.getParent().setRight(y);
        }
        return y;
    }

    private TNode rightRotate(TNode x) {
        TNode y = x.getLeft();
        x.setLeft(y.getRight());
        if (y.getRight() != null) {
            y.getRight().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == null) {
            root = y;
            super.setRoot(y); //used for inheritance. Sets the root in BST so print functions use corrected value
        } else if (x == x.getParent().getRight()) {
            x.getParent().setRight(y);
        } else {
            x.getParent().setLeft(y);
        }
        y.setRight(x);
        x.setParent(y);
        return y;
    }
    @Override
    public TNode Delete(int val) {
        TNode nodeToDelete = super.Search(val); // find the node to be deleted
        if (nodeToDelete == null) {
            System.out.println(val + " is not found in the tree.");
            return null; // node not found, return null
        }
        TNode parent = nodeToDelete.getParent();
        TNode left = nodeToDelete.getLeft();
        TNode right = nodeToDelete.getRight();

        // Case 1: Node has no children
        if (left == null && right == null) {
            if (parent == null) {
                super.setRoot(null); // Node is root
            } else if (parent.getLeft() == nodeToDelete) {
                parent.setLeft(null); // Node is left child
            } else {
                parent.setRight(null); // Node is right child
            }
            return balance(parent); // balance the tree
        }

        // Case 2: Node has one child
        if (left == null) {
            // Node has only right child
            if (parent == null) {
                super.setRoot(right); // Node is root
                right.setParent(null);
            } else if (parent.getLeft() == nodeToDelete) {
                parent.setLeft(right); // Node is left child
                right.setParent(parent);
            } else {
                parent.setRight(right); // Node is right child
                right.setParent(parent);
            }
            return balance(parent); // balance the tree
        }

        if (right == null) {
            // Node has only left child
            if (parent == null) {
                super.setRoot(left); // Node is root
                left.setParent(null);
            } else if (parent.getLeft() == nodeToDelete) {
                parent.setLeft(left); // Node is left child
                left.setParent(parent);
            } else {
                parent.setRight(left); // Node is right child
                left.setParent(parent);
            }
            return balance(parent); // balance the tree
        }

        // Case 3: Node has two children
        // Find the minimum node in the right subtree of the node to be deleted
        TNode min = right;
        while (min.getLeft() != null) {
            min = min.getLeft();
        }
        int minVal = min.getData();

        // Delete the minimum node from the right subtree
        Delete(minVal);

        // Replace the value of the node to be deleted with the minimum value found
        nodeToDelete.setData(minVal);

        return balance(parent); // balance the tree
    }

    @Override
    public void printBF() {
        super.printBF();
    }

    @Override
    public void printInOrder() {
        super.printInOrder();
    }

    @Override
    public TNode Search(int val) {
        return super.Search(val);
    }

    public static void main(String args[]) {
        System.out.println("=== Testing AVL() default constructor === \n");
        AVL treeV1 = new AVL();
        System.out.println("Checking root, expected: null, actual: " + treeV1.getRoot() + "\n");
        System.out.println("=== Testing AVL(int val) overload constructor === \n");
        AVL treeV2 = new AVL(5);
        System.out.println("Checking root, expected data value: 5, actual: " + treeV2.getRoot().getData() + "\n");
        System.out.println("=== Testing AVL(TNode obj) overload constructor and printBF() === \n");
        TNode nodeV3 = new TNode(7, 0, null, null, null);
        AVL treeV3 = new AVL(nodeV3);
        System.out.println("Expected:");
        System.out.println("7");
        System.out.println("Actual:");
        treeV3.printBF();
        TNode nodeV4 = new TNode(11, 0, null, null, null);
        nodeV4.setRight(nodeV3);
        TNode nodeV5 = new TNode(19, 0, null, null, null);
        nodeV5.setRight(nodeV4);
        TNode nodeV6 = new TNode(12, 0, null, null, null);
        nodeV6.setLeft(nodeV5);
        AVL treeV4 = new AVL(nodeV6);
        System.out.println("\n=== Testing AVL(TNode obj) overload constructor with obj having children === \n");
        treeV4.printBF();
        /*
        should have structure - testing Insert(int val)
                 5
               /  \
              2    11
             / \    / \
            1   4  6  12
         */
        AVL Tree = new AVL();

        TNode node1 = Tree.Insert(6);
        TNode node2 = Tree.Insert(2);
        TNode node3 = Tree.Insert(1);
        TNode node4 = Tree.Insert(5);
        TNode node5 = Tree.Insert(4);
        TNode node6 = Tree.Insert(11);
        TNode node7 = Tree.Insert(12);


        System.out.println("\n=== Testing Insert(int val) === \n");
        System.out.println("Expected:");
        System.out.println("5\n2 11\n1 4 6 12");
        System.out.println("Actual:");
        Tree.printBF();

        AVL Tree1 = new AVL(12);
        TNode node8 = Tree1.Insert(5);
        TNode node9 = Tree1.Insert(2);

        System.out.println("\n=== Testing Insert(int val) case where val already exists ===\n");
        Tree.Insert(6);
        System.out.println("Expected:");
        System.out.println("5\n2 11\n1 4 6 12");
        System.out.println("Actual:");
        Tree.printBF();

        System.out.println("\n=== Testing LL Rotation === \n");
        System.out.println("Expected:");
        System.out.println("5\n2 12");
        System.out.println("Actual:");
        Tree1.printBF();

        AVL Tree2 = new AVL();
        TNode node10 = Tree2.Insert(12);
        TNode node11 = Tree2.Insert(15);
        TNode node12 = Tree2.Insert(19);
        System.out.println("\n=== Testing RR Rotation === \n");
        System.out.println("Expected:");
        System.out.println("15\n12 19");
        System.out.println("Actual:");
        Tree2.printBF();

        AVL Tree3 = new AVL();
        TNode node13 = Tree3.Insert(13);
        TNode node14 = Tree3.Insert(11);
        TNode node15 = Tree3.Insert(12);
        System.out.println("\n=== Testing LR Rotation === \n");
        System.out.println("Expected:");
        System.out.println("12\n11 13");
        System.out.println("Actual:");
        Tree3.printBF();

        AVL Tree4 = new AVL();
        TNode node16 = Tree4.Insert(12);
        TNode node17 = Tree4.Insert(15);
        TNode node18 = Tree4.Insert(13);
        System.out.println("\n=== Testing RL Rotation === \n");
        System.out.println("Expected:");
        System.out.println("13\n12 15");
        System.out.println("Actual:");
        Tree4.printBF();

        System.out.println("\n=== Testing Insert(TNode obj) case where obj has no children ===\n");
        AVL Tree0 = new AVL();
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
        System.out.println("Expected:");
        System.out.println("2\n1 5\n4 6");
        System.out.println("Actual:");
        Tree0.printBF();

        System.out.println("\n === Testing Insert(TNode obj) case where obj has children ===\n");
        System.out.println("Expected:");
        System.out.println("6\n2 8\n1 5 0 11\n4");
        System.out.println("Actual:");
        AVL Tree5 = new AVL();
        TNode node01 = Tree5.Insert(6);
        TNode node02 = Tree5.Insert(2);
        TNode node03 = Tree5.Insert(1);
        TNode node04 = Tree5.Insert(5);
        TNode node05 = Tree5.Insert(4);

        AVL subTree = new AVL();
        TNode node21 = subTree.Insert(8);
        TNode node22 = subTree.Insert(11);
        TNode node23 = subTree.Insert(0);
        Tree5.Insert(node21);
        Tree5.printBF();

        System.out.println("\n === Testing Insert(TNode obj) case where obj has data that already exists ===\n");
        Tree5.Insert(node21);
        System.out.println("Expected:");
        System.out.println("6\n2 8\n1 5 0 11\n4");
        System.out.println("Actual:");
        Tree5.printBF();

        System.out.println("\n=== Testing Delete(int val) for 4 ===\n");
        /*
        The original structure of the tree should be
                 5
               /  \
              2     11
             / \   /  \
            1   4 6   12
         */
        Tree.Delete(4);
        System.out.println("Expected:");
        System.out.println("5\n2 11\n1 6 12");
        System.out.println("Actual:");
        Tree.printBF();

        System.out.println("\n===Testing Delete(int val) for 2 ===\n");
        Tree.Delete(2);
        System.out.println("Expected:");
        System.out.println("5\n1 11\n6 12");
        System.out.println("Actual:");
        Tree.printBF();
        System.out.println("\n===Testing Delete(int val) if value doesn't exist ===\n");
        System.out.println("Expected:");
        System.out.println("null\n0 is not found in the tree");
        System.out.println("Actual:");
        Tree.Delete(0);
        System.out.println("\n===Testing Delete(int val) for entire Tree. Output should be Tree is Empty ===\n");
        Tree.Delete(6);
        Tree.Delete(1);
        Tree.Delete(5);
        Tree.Delete(11);
        Tree.Delete(12);
        Tree.printBF();

        System.out.println("\n=== Testing Search(int val) for 2 in the second tree. Should return a node with data 2===\n");
        TNode searchNode = Tree0.Search(2);
        searchNode.print(); //check if node data is correct with tree01 - should have two children

        System.out.println("\n=== Testing Search(int val) if value does not exist ===\n");
        System.out.println("Expected:");
        System.out.println("null");
        System.out.println("Actual:");
        TNode searchNode1 = Tree0.Search(0);

        System.out.println("\n=== Testing printInOrder() ===\n");
        System.out.println("Expected:");
        System.out.println("1 2 4 5 6\n\nTree is Empty");
        System.out.println("Actual:");
        Tree0.printInOrder();
        System.out.println("\n");
        Tree.printInOrder();
        System.out.println();
        System.out.println("\n=== Testing printBF() for both trees ===\n");
        System.out.println("Expected:");
        System.out.println("2\n1 5\n4 6\n\nTree is Empty");
        System.out.println("Actual:");
        Tree0.printBF();
        System.out.println();
        Tree.printBF();
    }
}



