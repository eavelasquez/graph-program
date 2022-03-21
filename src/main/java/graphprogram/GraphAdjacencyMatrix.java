/******************************************************************************
 *  Compilation:  javac GraphAdjacencyMatrix.java
 *  Execution:    java GraphAdjacencyMatrix
 *  
 *  A graph, implementing adjacency matrix.
 *
 ******************************************************************************/
package graphprogram;

import java.util.ArrayList;

/**
 * Adjacency matrix representation of a graph.
 *
 * @author ev
 */
public class GraphAdjacencyMatrix implements Graph {

  private int vertexCount = 0; // number of vertices.
  private int[][] adjacencyMatrix = null; // adjacency matrix.
  private boolean isDirected = false; // is the graph directed?

  /**
   * Initializes the adjacency matrix.
   */
  public GraphAdjacencyMatrix(int vertexCount, boolean isDirected) {
    this.vertexCount = vertexCount;
    this.isDirected = isDirected;
    adjacencyMatrix = new int[this.vertexCount + 1][this.vertexCount + 1];

    for (int i = 1; i <= this.vertexCount; i++) {
      for (int j = 1; j <= this.vertexCount; j++) {
        adjacencyMatrix[i][j] = 0;
      }
    }
  }

  public GraphAdjacencyMatrix(int vertexCount, int[][] edges, boolean isDirected) {
    this(vertexCount, isDirected); // call the other constructor.

    for (int i = 0; i < edges.length; i++) {
      if (edges[i].length > 2) {
        throw new IllegalArgumentException("Invalid edge: " + edges[i][0] + " " + edges[i][1] + " " + edges[i][2]);
      }

      addEdge(edges[i][0], edges[i][1]);
    }
  }

  /**
   * Returns the number of vertices in the graph.
   *
   * @return the number of vertices in the graph.
   */
  public int getVertexCount() {
    return vertexCount;
  }

  /**
   * Sets the number of vertices in the graph.
   *
   * @param vertexCount the number of vertices in the graph.
   */
  public void setVertexCount(int vertexCount) {
    this.vertexCount = vertexCount;
  }

  /**
   * Returns the if the graph is directed.
   * 
   * @return the if the graph is directed.
   */
  public boolean getIsDirected() {
    return isDirected;
  }

  /**
   * Sets the if the graph is directed.
   * 
   * @param isDirected the if the graph is directed.
   */
  public void setDirected(boolean isDirected) {
    this.isDirected = isDirected;
  }

  /**
   * Adds an edge between two vertices.
   *
   * @param vertex1 the first vertex.
   * @param vertex2 the second vertex.
   */
  public void addEdge(int vertex1, int vertex2) {
    adjacencyMatrix[vertex1][vertex2] = 1;
    if (!this.isDirected) {
      this.adjacencyMatrix[vertex2][vertex1] = 1;
    }
  }

  /**
   * Removes an edge between two vertices.
   *
   * @param vertex1 the first vertex.
   * @param vertex2 the second vertex.
   */
  public void removeEdge(int vertex1, int vertex2) {
    if (this.adjacencyMatrix[vertex1][vertex2] == 1) {
      this.adjacencyMatrix[vertex1][vertex2] = 0;

      // if the graph is not directed, remove the edge in the other direction as well.
      if (!this.isDirected) {
        this.adjacencyMatrix[vertex2][vertex1] = 0;
      }
    }
  }

  /**
   * Checks if an edge exists between two vertices.
   *
   * @param vertex1 the first vertex.
   * @param vertex2 the second vertex.
   * @return true if the edge exists, false otherwise.
   */
  public boolean isEdge(int vertex1, int vertex2) {
    return this.adjacencyMatrix[vertex1][vertex2] == 1;
  }

  /**
   * This method is used to print the adjacency matrix.
   *
   * @return the adjacency matrix.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < this.vertexCount; i++) {
      for (int j = 0; j < this.vertexCount; j++) {
        sb.append(this.adjacencyMatrix[i][j]);
        sb.append(" ");
      }
      sb.append("\n");
    }

    return sb.toString();
  }

  /**
   * This method is used to get the vertex degree undirected graph.
   *
   * @param vertex the vertex whose degree is to be found.
   * @return the degree of the vertex.
   * @throws IllegalArgumentException if the vertex does not exist.
   */
  public int vertexDegreeUndirectedGraph(int vertex) {
    int degree = 0;

    if (vertex < 0 || vertex > this.vertexCount) {
      throw new IllegalArgumentException("vertex " + vertex + " does not exist");
    }

    for (int i = 0; i < this.vertexCount; i++) {
      if (this.adjacencyMatrix[vertex][i] == 1) {
        degree++;
      }
    }

    return degree;
  }

  /**
   * This method is used to get the overall vertex degree undirected graph.
   */
  public void overallVertexDegreeUndirectedGraph() {
    for (int i = 0; i < this.vertexCount; i++) {
      int degree = 0;

      for (int j = 0; j < this.vertexCount; j++) {
        if (adjacencyMatrix[i][j] == 1) {
          degree++;
        }
      }

      System.out.println("The degree of a vertex of the adjacency matrix: " + degree);
    }
  }

  /**
   * This method is used to get the vertex degree directed graph.
   *
   * @param vertex the vertex whose degree is to be found.
   * @return the degree of the vertex.
   * @throws IllegalArgumentException if the vertex does not exist.
   */
  public int vertexDegreeDirectedGraph(int vertex) {
    int outgoingDegree = 0;
    int incomingDegree = 0;

    if (vertex < 0 || vertex > this.vertexCount) {
      throw new IllegalArgumentException("vertex " + vertex + " does not exist");
    }

    for (int i = 0; i < this.vertexCount; i++) {
      if (this.adjacencyMatrix[vertex][i] == 1) {
        incomingDegree++;
      }
      if (this.adjacencyMatrix[i][vertex] == 1) {
        outgoingDegree++;
      }
    }

    return incomingDegree + outgoingDegree;
  }

  /**
   * This method is used to get the overall vertex degree directed graph.
   */
  public void overallVertexDegreeDirectedGraph() {
    for (int i = 0; i < this.vertexCount; i++) {
      int overallDegree = 0;
      int incomingDegree = 0;
      int outgoingDegree = 0;

      for (int j = 0; j < this.vertexCount; j++) {
        if (this.adjacencyMatrix[i][j] == 1) {
          incomingDegree++;
        }
        if (this.adjacencyMatrix[j][i] == 1) {
          outgoingDegree++;
        }
      }

      overallDegree = incomingDegree + outgoingDegree;

      System.out.println("Vertex " + i + ": " + incomingDegree + " " + outgoingDegree);
      System.out.println("Vertex " + i + ": " + overallDegree);
    }
  }

  /**
   * This method is used to get the transversal DFS of the graph.
   *
   * @param vertex  the vertex whose transversal is to be found.
   * @param visited the array of visited vertices.
   * @return the transversal of the graph.
   */
  public ArrayList<Integer> transversalDfs(int vertex, int[] visited) {
    ArrayList<Integer> transversal = new ArrayList<Integer>();

    visited[vertex] = 1;
    transversal.add(vertex);
    System.out.println("Vertex: " + vertex);

    for (int i = 0; i < this.vertexCount; i++) {
      if (this.adjacencyMatrix[vertex][i] == 1 && visited[i] == 0) {
        transversal.addAll(transversalDfs(i, visited));
      }
    }

    return transversal;
  }

  /**
   * This method is used to get the transversal BFS of the graph.
   *
   * @param vertex the vertex whose transversal is to be found.
   * @return the transversal of the graph. 
   */
  public int[] transversalBfs(int vertex) {
    int first = -1, latest = 0;
    int[] queue = new int[this.vertexCount];
    int[] visited = new int[this.vertexCount];

    visited[vertex] = 1;
    queue[latest] = vertex;

    while (first != latest) {
      first++;
      vertex = queue[first];
      System.out.println("Vertex: " + vertex);

      for (int i = 0; i < this.vertexCount; i++) {
        if (this.adjacencyMatrix[vertex][i] == 1 && visited[i] == 0) {
          latest++;
          visited[i] = 1;
          queue[latest] = i;
        }
      }
    }

    return queue;
  }
}
