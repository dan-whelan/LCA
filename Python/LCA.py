class Node:
    def __init__(self,key):
        self.key = key
        self.left = None
        self.right = None

class BinaryTree:
    def __init__(self,key):
        self.root = Node(key)

    def put(self, root, key):
        if root is None: 
            return Node(key)
        if root.key > key: 
            root.left = self.put(root.left, key)
        elif root.key < key: 
            root.right = self.put(root.right, key)
        return root  
    
    def get(self, root, key):
        if root is None: 
            return False
        if root.key > key: 
            return self.get(root.left, key)
        elif root.key < key: 
            return self.get(root.right, key)
        return True  

    def findLCA(self,root, k1, k2):
        if root is None: 
            return None
        if root.key is k1 or root.key is k2: 
            return root
        leftLCA = self.findLCA(root.left,k1,k2)
        rightLCA = self.findLCA(root.right,k1,k2)
        if leftLCA is not None and rightLCA is not None: 
            return root
        if leftLCA is not None: 
            return leftLCA
        else: 
            return rightLCA


if __name__ =='__main__':
    tree = BinaryTree(4)
    tree.put(tree.root,2)
    tree.put(tree.root,3)
    tree.put(tree.root,1)
    tree.put(tree.root,6)
    tree.put(tree.root,5)
    tree.put(tree.root,7)
    lcaFound = tree.findLCA(tree.root, 3,7)
    if lcaFound is not None and tree.get(tree.root,3) and tree.get(tree.root,7):
        print("LCA of key:3 and key:7  = " + str(lcaFound.key))
    else:
        print("LCA of key:3 and key:7 does not exist")
    lcaFound = tree.findLCA(tree.root, 4, 10)
    if lcaFound is not None and tree.get(tree.root,4) and tree.get(tree.root,10):
        print("LCA of key:4 and key:10 is" + str(lcaFound.key))
    else:
        print("LCA of key:4 and key:10 does not exist")




