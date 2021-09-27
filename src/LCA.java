//implemented based off of BST class made in Second Year

/**
 * @class Node
 *      a class used to store the data for different nodes of the binary tree.
 */
class Node {
    int key;
    Node left = null;
    Node right = null;

    public Node(int key) {
        this.key = key;
    }
}
/**
 * @class BinaryTree
 *      a class used to implement a binary tree data structure with the aid of the node class.
 */
class BinaryTree {
    Node root;

    /**
     * initialises Binary Tree Structure with initial root key
     * @param key the key to insert
     */
    BinaryTree(int key) {
        root = new Node(key);
    }

    /**
     *  Insert key into BinaryTree.
     *  If key already exists, update with new value.
     *
     *  @param key the key to insert
     */
    public void put(int key) {
        root = put(root, key);
    }

    private Node put(Node x, int key) {
        if (x == null) return new Node(key);
        if      (x.key > key) x.left  = put(x.left,  key);
        else if (x.key < key) x.right = put(x.right, key);
        return x;
    }

    /**
     *  Search BST for given key.
     *  What is the value associated with given key?
     *
     *  @param key the search key
     *  @return value associated with the given key if found, or null if no such key exists.
     */
    public boolean get(int key) { return get(root, key); }

    private boolean get(Node x, int key) {
        if (x == null) return false;
        if      (x.key > key) return get(x.left, key);
        else if (x.key < key) return get(x.right, key);
        else              return true;
    }

    /**
     * finds the Lowest Common Ancestor of two given key values
     * @param k1 the key value of the first LCA node to be found
     * @param k2 the key value of the second LCA node to be found
     * @return the node that is the LCA of k1 and k2
     */
    public Node findLCA(int k1, int k2) {
        if(get(k1) && get(k2)) {
            return findLCA(root, k1, k2);
        }
        else return null;
    }

    private Node findLCA(Node x, int k1, int k2) {
        if(x == null) return null;
        if(x.key == k1 || x.key == k2) return x;
        Node leftLCA = findLCA(x.left, k1, k2);
        Node rightLCA = findLCA(x.right, k1, k2);
        if(leftLCA != null && rightLCA != null) return x;
        if(leftLCA != null) return leftLCA;
        else return rightLCA;
    }
}

public class LCA {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(4);
        tree.put(2);
        tree.put(3);
        tree.put(1);
        tree.put(6);
        tree.put(5);
        tree.put(7);
        Node lca = tree.findLCA(3, 7);
        if(lca != null) {
            System.out.println("LCA of key:3 and key:7 = " + lca.key);
        }
        else {
            System.out.println("LCA of key:3 and key:7 does not exist");
        }
        lca = tree.findLCA(4, 10);
        if(lca != null) 
            System.out.println("LCA of key:4 and key:10 = " + lca.key);
        else 
            System.out.println("LCA of key:4 and key:10 does not exist"); 
    }
}
