//implemented based off of BST class made in Second Year

/**
 * @class Node
 *      a class used to store the data for different nodes of the binary tree.
 */

class Node {
    constructor(key) {
        this.left = null;
        this.right = null;
        this.key = key;
    }
}

let root = null;
let N = 0;

/**
 *  Insert key into BinaryTree.
 *  If key already exists, update with new value.
 *   
 *  @param key the key to insert
 */

function put(key) {
    root = put(root, key);
    N++;
}

function put(x, key) {
    if(x == null) return new Node(key);
    if      (x.key > key) x.left = put(x.left, key);
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
function get(key) {
    return get(root, key);
}

function get(x, key) {
    if(x == null) return false;
    if      (x.key > key) return get(x.left, key);
    else if (x.key < key) return get(x.right, key);
    return true;
}

/**
 * finds the Lowest Common Ancestor of two given key values
 * @param k1 the key value of the first LCA node to be found
 * @param k2 the key value of the second LCA node to be found
 * @return the node that is the LCA of k1 and k2
 */

function findLCA(k1, k2) {
    if(get(k1) && get(k2)) {
        return findLCA(root, k1, k2);
    }
    else return null;
}

function findLCA(x, k1, k2) {
    if(x == null) return null;
    if(x.key == k1 || x.key == k2) return x;
    leftLCA = findLCA(x.left, k1, k2);
    rightLCA = findLCA(x.right, k1, k2);
    if(leftLCA != null && rightLCA != null) return x;
    if(leftLCA != null) return leftLCA;
    else return rightLCA;
}

function Main() {
    put(4);
    put(2);
    put(3);
    put(1);
    put(6);
    put(5);
    put(7);
    lca = findLCA(3,7);
    if(lca != null) {
        document.write("LCA of key:3 and key:7 = " + lca.key);
    }
    else {
        document.write("LCA of key:3 and key:7 does not exist");
    }
    lca = findLCA(4,10);
    if(lca != null) {
        document.write("LCA of key:4 and key:10 = " + lca.key);
    }
    else {
        document.write("LCA of key:4 and key:10 does not exist");
    }
}