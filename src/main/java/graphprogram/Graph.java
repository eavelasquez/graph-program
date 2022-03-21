package graphprogram;

import java.util.ArrayList;

/**
 * The {@code Graph} class represents a interface for a graph.
 *
 * @author ev
 */
public interface Graph {

  public void addEdge(int vertex1, int vertex2);

  public void removeEdge(int vertex1, int vertex2);

  public boolean isEdge(int vertex1, int vertex2);

  public String toString();

  public int vertexDegreeUndirectedGraph(int vertex);

  public void overallVertexDegreeUndirectedGraph();

  public int vertexDegreeDirectedGraph(int vertex);

  public void overallVertexDegreeDirectedGraph();

  public ArrayList<Integer> transversalDfs(int vertex, int[] visited);

  public int[] transversalBfs(int vertex);
}
