import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


//-------------------------------------------------------------------------
/**
 *  Test class for Lowest Commonn Ancestor using Binary Tree implementation
 *
 *  @author  Daniel Whelan
 */

 @RunWith(JUnit4.class)
 public class LCATest
 {
     @Test
     public void testPut() 
     {
        BinaryTree test = new BinaryTree(1);
        assertEquals("Checking constriuction of tree containing key 1 as root node", 1, test.root.key);
        test.put(2);
        test.put(3);
        test.put(0);
        assertEquals("Checking if all keys in test have bneen input into tree by calling BinaryTree.size()",4,test.size());
     }

     @Test
     public void testGet()
     {
         BinaryTree test = new BinaryTree(0);
         test.put(1);
         test.put(2);
         test.put(-3);
         test.put(-4);
         assertEquals("Checking get() for right hand side of tree",true,test.get(2));
         assertEquals("Checking get() for left hand side of tree", true, test.get(-4));
         assertEquals("Checking get() for root value of tree", true, test.get(0));
         assertEquals("Checking get() for value not in tree", false, test.get(9));
     }

     @Test
     public void testFindLCA() 
     {
        BinaryTree test = new BinaryTree(4);
        test.put(2);
        test.put(3);
        test.put(1);
        test.put(6);
        test.put(5);
        test.put(7);
        Node t = test.findLCA(3,7);
        assertEquals("Checking findLCA for two values where LCA is root", 4, t.key);
        t = test.findLCA(3,1);
        assertEquals("Checking findLCA for two value where LCA is on left of root", 2, t.key);
        t = test.findLCA(5,7);
        assertEquals("CHecking findLCA for two values where LCA is on the right of root",6, t.key);
        t = test.findLCA(4,10);
        assertEquals("Checking findLCA for two values where one does not exist in the tree", null, t);
        t = test.findLCA(10,10);
        assertEquals("Checking findLCA for two values where neither are present in tree", null, t);
        t = test.findLCA(6,7);
        assertEquals("Checking findLCA for two values where one is the lowest common ancestor of the pair", 6, t.key);
        t = test.findLCA(3,3);
        assertEquals("Checking findLCA for two values that are the same and present in the tree", 3, t.key);
     }
 }