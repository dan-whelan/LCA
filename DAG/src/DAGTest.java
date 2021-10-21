import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
@RunWith(JUnit4.class)
public class DAGTest {
    DAG acyclic = new DAG(8);
    DAG cyclic = new DAG(8);
    DAG straightLine = new DAG(8);

    public void straightLine() {
        straightLine.addEdge(0, 1);
        straightLine.addEdge(1, 2);
        straightLine.addEdge(2, 3);
        straightLine.addEdge(3, 4);
        straightLine.addEdge(4, 5);
        straightLine.addEdge(5, 6);
        straightLine.addEdge(6, 7);
    }

    public void acyclic() {
        acyclic.addEdge(0, 1);
        acyclic.addEdge(0, 2);
        acyclic.addEdge(1, 3);
        acyclic.addEdge(3, 5);
        acyclic.addEdge(2, 4);
        acyclic.addEdge(4, 6);
        acyclic.addEdge(5, 7);
        acyclic.addEdge(6, 7);
    }

    public void cyclic() {
        cyclic.addEdge(0, 1);
        cyclic.addEdge(0, 2);
        cyclic.addEdge(1, 2);
        cyclic.addEdge(2, 4);
        cyclic.addEdge(4, 3);
        cyclic.addEdge(3, 1);
        cyclic.addEdge(3, 5);
        cyclic.addEdge(5, 7);
        cyclic.addEdge(6, 7);
    }

   @Test(expected = IllegalArgumentException.class)
   public void testConstructor() {
       DAG negativeTest = new DAG(-2);
   }

   @Test
   public void testInDegreeVertex() {
       acyclic();
       cyclic();
       assertEquals("Testing in for negative number", -1, acyclic.inDegreeVertex(-2));
       assertEquals("Testing for vertex with one in degree", 1, acyclic.inDegreeVertex(5));
       assertEquals("Testing for vertex with more than one in degree", 2, cyclic.inDegreeVertex(7));
   }

   @Test
   public void testOutDegreeVertex() {
       straightLine();
       cyclic();
       assertEquals("Testing out for negative vertex", -1, straightLine.outDegreeVertex(-2));
       assertEquals("Testing for vertex with one out degree", 1, straightLine.outDegreeVertex(0));
       assertEquals("Testing for vertex with more than one out degree", 2, cyclic.outDegreeVertex(0));
   }

   @Test
   public void testAdj() {
       straightLine();
       cyclic();
       assertArrayEquals(new int[]{4}, straightLine.adj(3));
       assertArrayEquals(new int[]{1,5}, cyclic.adj(3));
   }

   @Test
   public void testE() {
       straightLine();
       acyclic();
       cyclic();
       assertEquals("Testing number of edges in straight line graph", 7, straightLine.e());
       assertEquals("Testing number of edges in acyclic graph", 8, acyclic.e()); 
       assertEquals("Testing number of edges in cyclic graph",9, cyclic.e());
   }

   @Test
   public void testV() {
        straightLine();
        acyclic();
        cyclic();
        assertEquals("Testing number of vertices in straight line graph", 8, straightLine.v());
        assertEquals("Testing number of vertices in acyclic graph", 8, acyclic.v()); 
        assertEquals("Testing number of vertices in cyclic graph",8, cyclic.v());
   }

   @Test
   public void testAddEdge() {
       acyclic();
       assertEquals("Number of edges should be 9", 9, acyclic.e());
       acyclic.addEdge(4, 7);
       assertEquals("Number of edges should increase to 10", 10, acyclic.e());
       acyclic.addEdge(0, 7);
       assertEquals("Number of edges should increase to 11", 11, acyclic.e());
   }

   @Test
   public void testHasCycle() {
       acyclic();
       cyclic();
       assertEquals("Testing for acyclic graph", false, acyclic.hasCycle());
       assertEquals("Testing for cyclic graph", true, cyclic.hasCycle());
   }

   @Test
   public void testLCA() {
       straightLine();
       acyclic();
       cyclic();
       assertEquals("Testing for vertex being its own ancestor", 3, straightLine.findLCA(3,3));
       assertEquals("Testing for cyclic graph", -1, cyclic.findLCA(3, 3));
       assertEquals("Testing for different levels present in graph", 1, straightLine.findLCA(1,5));
       assertEquals("Testing for different levels present in graph", 2, straightLine.findLCA(2,5));
       assertEquals("Testing for acyclic graph", 0, acyclic.findLCA(5, 2));
       assertEquals("Testing for swap in vertices", 1, straightLine.findLCA(1, 5));
       assertEquals("Testing for swap in vertices", 1, straightLine.findLCA(5, 1));
   }
}
