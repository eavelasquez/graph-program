package graphprogram;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit tests the {@code GraphProgram} data type.
 */
public class AppTest {
  int vertexCount = 5;
  int[][] edges = { { 0, 1 }, { 0, 2 }, { 1, 2 }, { 1, 3 }, { 2, 3 }, { 3, 4 } };
  boolean isDirected = false;

  @Test
  public void testGraphProgram() {
    GraphAdjacencyMatrix graph = new GraphAdjacencyMatrix(vertexCount, edges, isDirected);
    assertTrue(graph.getVertexCount() == vertexCount);
    assertTrue(graph.getIsDirected() == isDirected);
  }

  @Test
  public void testTransversalBfsGraphAdjacencyMatrix() {
    GraphAdjacencyMatrix graph = new GraphAdjacencyMatrix(this.vertexCount, this.edges, this.isDirected);

    int[] expected = { 0, 1, 2, 3, 4 };
    int[] result = graph.transversalBfs(0);

    assertArrayEquals(expected, result);
  }

  @Test
  public void testTransversalBftGraphAdjacencyLinkedList() {
    GraphAdjacencyLinkedList graph = new GraphAdjacencyLinkedList(this.vertexCount, this.edges, this.isDirected);

    int[] expected = { 0, 1, 2, 3, 4 };
    int[] result = graph.transversalBfs(0);

    assertArrayEquals(expected, result);
  }

  @Test
  public void testTransversalDfsGraphAdjacencyMatrix() {
    GraphAdjacencyMatrix graph = new GraphAdjacencyMatrix(this.vertexCount, this.edges, this.isDirected);

    Object[] expected = new Object[] { 0, 1, 2, 3, 4 };
    int[] visited = new int[this.vertexCount + 1];
    Object[] result = graph.transversalDfs(0, visited).toArray();

    assertArrayEquals(expected, result);
  }

  @Test
  public void testTransversalDftGraphAdjacencyLinkedList() {
    GraphAdjacencyLinkedList graph = new GraphAdjacencyLinkedList(this.vertexCount, this.edges, this.isDirected);

    Object[] expected = new Object[] { 0, 1, 2, 3, 4 };
    int[] visited = new int[this.vertexCount + 1];
    Object[] result = graph.transversalDfs(0, visited).toArray();

    assertArrayEquals(expected, result);
  }
}
