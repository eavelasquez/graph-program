/******************************************************************************
 *  Compilation:  javac GraphAdjacencyLinkedList.java
 *  Execution:    java GraphAdjacencyLinkedList
 *  
 *  A graph, implementing adjacency linked list.
 *
 ******************************************************************************/
package graph.program;

/**
 * Adjacency matrix representation of a graph.
 *
 * @author ev
 */
public class GraphAdjacencyLinkedList implements Graph {

  private int vertexCount = 0; // number of vertices.
  private Node head; // head of the adjacency linked list.
  private boolean isDirected = false; // is the graph directed?

  /**
   * Initializes the adjacency linked list.
   */
  public GraphAdjacencyLinkedList(int vertexCount, boolean isDirected) {
    this.vertexCount = vertexCount;
    this.isDirected = isDirected;
    this.head = new Node(this.vertexCount + 1);
  }

  public GraphAdjacencyLinkedList(int vertexCount, int[][] edges, boolean isDirected) {
    this(vertexCount, isDirected); // call the other constructor.

    for (int i = 0; i < edges.length; i++) {
      if (edges[i].length > 2) {
        throw new IllegalArgumentException("Invalid edge: " + edges[i][0] + " " + edges[i][1] + " " + edges[i][2]);
      }

      addEdge(edges[i][0], edges[i][1]);
    }
  }

  /**
   * Returns the head of the adjacency linked list.
   * 
   * @return the head of the adjacency linked list.
   */
  public Node getHead() {
    return head;
  }

  /**
   * Sets the head of the adjacency linked list.
   * 
   * @param head the head of the adjacency linked list.
   */
  public void setHead(Node head) {
    this.head = head;
  }

  /**
   * Returns the number of vertices.
   * 
   * @return the number of vertices.
   */
  public int getVertexCount() {
    return vertexCount;
  }

  /**
   * Sets the number of vertices.
   * 
   * @param vertexCount the number of vertices.
   */
  public void setVertexCount(int vertexCount) {
    this.vertexCount = vertexCount;
  }

  /**
   * Returns if the graph is directed.
   * 
   * @return if the graph is directed.
   */
  public boolean isDirected() {
    return isDirected;
  }

  /**
   * Sets if the graph is directed.
   * 
   * @param isDirected if the graph is directed.
   */
  public void setDirected(boolean isDirected) {
    this.isDirected = isDirected;
  }

  /**
   * This method is used to check the circularity reference.
   *
   * @param vertex1 the first vertex.
   * @param vertex2 the second vertex.
   * @return true if the graph is circular, false otherwise.
   */
  public boolean isCircular(int vertex1, int vertex2) {
    return vertex1 == vertex2;
  }

  /**
   * Adds an edge between two vertices.
   *
   * @param vertex1 the first vertex.
   * @param vertex2 the second vertex.
   */
  public void addEdge(int vertex1, int vertex2) {
    if (isCircular(vertex1, vertex2)) {
      return;
    }

    Node temp = this.head;
    Node newNode = new Node(vertex2);

    while (temp.getNext() != null) {
      if (temp.getVertex() == vertex1) {
        newNode.setNext(temp.getNext());
        temp.setNext(newNode);
        break;
      }
      temp = temp.getNext();
    }
  }

  /**
   * Removes an edge between two vertices.
   *
   * @param vertex1 the first vertex.
   * @param vertex2 the second vertex.
   */
  public void removeEdge(int vertex1, int vertex2) {
    if (isCircular(vertex1, vertex2)) {
      return;
    }

    Node temp = this.head;

    while (temp.getNext() != null) {
      if (temp.getVertex() == vertex1) {
        Node temp2 = temp.getNext();

        while (temp2.getNext() != null) {
          if (temp2.getVertex() == vertex2) {
            temp.setNext(temp2.getNext());
            break;
          }
          temp2 = temp2.getNext();
        }
        break;
      }
      temp = temp.getNext();
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
    if (isCircular(vertex1, vertex2)) {
      return false;
    }

    Node temp = head;

    while (temp.getNext() != null) {
      if (temp.getVertex() == vertex1) {
        Node temp2 = temp.getNext();
        while (temp2.getNext() != null) {
          if (temp2.getVertex() == vertex2) {
            return true;
          }
          temp2 = temp2.getNext();
        }
        break;
      }
      temp = temp.getNext();
    }

    return false;
  }

  /**
   * This method is used to print the adjacency linked list.
   *
   * @return the adjacency linked list.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Node temp = head;

    while (temp.getNext() != null) {
      sb.append(temp.getVertex());
      sb.append(" -> ");
      temp = temp.getNext();
    }
    sb.append(temp.getVertex());
    sb.append(" -> null");

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
    if (vertex < 1 || vertex > this.vertexCount) {
      throw new IllegalArgumentException("Invalid vertex: " + vertex);
    }

    Node temp = head;
    int degree = 0;

    while (temp.getNext() != null) {
      if (temp.getVertex() == vertex) {
        degree++;
      }
      temp = temp.getNext();
    }

    return degree;
  }

  /**
   * This method is used to get the overall vertex degree undirected graph.
   */
  public void overallVertexDegreeUndirectedGraph() {
    Node temp = head;
    int degree = 0;

    while (temp.getNext() != null) {
      degree += vertexDegreeUndirectedGraph(temp.getVertex());
      temp = temp.getNext();
    }

    degree += vertexDegreeUndirectedGraph(temp.getVertex());
    System.out.println("Overall vertex degree: " + degree);
  }

  /**
   * This method is used to get the vertex degree directed graph.
   *
   * @param vertex the vertex whose degree is to be found.
   * @return the degree of the vertex.
   * @throws IllegalArgumentException if the vertex does not exist.
   */
  public int vertexDegreeDirectedGraph(int vertex) {
    if (vertex < 1 || vertex > this.vertexCount) {
      throw new IllegalArgumentException("Invalid vertex: " + vertex);
    }

    Node temp = head;
    int degree = 0;

    while (temp.getNext() != null) {
      if (temp.getVertex() == vertex) {
        degree++;
      }
      temp = temp.getNext();
    }

    return degree;
  }

  /**
   * This method is used to get the overall vertex degree directed graph.
   */
  public void overallVertexDegreeDirectedGraph() {
    Node temp = head;
    int degree = 0;

    while (temp.getNext() != null) {
      degree += vertexDegreeDirectedGraph(temp.getVertex()); // incoming and outgoing edges
      temp = temp.getNext();
    }

    degree += vertexDegreeDirectedGraph(temp.getVertex());
    System.out.println("Overall vertex degree: " + degree);
  }

  /**
   * This method is used to get the transversal DFS of the graph.
   *
   * @param vertex the vertex whose transversal is to be found.
   * @param visited the vertex visited.
   */
  public void transversalDfs(int vertex, int[] visited) {
    int v;
    Node temp = head;

    visited[vertex] = 1;
    System.out.print(vertex + " ");

    while (temp != null) {
      v = temp.getVertex();

      if (visited[v] == 0) {
        transversalDfs(v, visited);
      }

      temp = temp.getNext();
    }
  }

  /**
   * This method is used to get the transversal BFS of the graph.
   *
   * @param vertex the vertex whose transversal is to be found.
   */
  public void transversalBfs(int vertex) {
    Node temp = null;
    int v, first = -1, latest = 0;
    int[] queue = new int[vertexCount];
    int[] visited = new int[vertexCount];

    visited[vertex] = 1;
    queue[latest] = vertex;

    while (first != latest) {
      first++;
      vertex = queue[first];
      temp = head;

      System.out.println("Vertex: " + vertex);

      while (temp != null) {
        v = temp.getVertex();

        if (visited[v] == 0) {
          latest++;
          visited[v] = 1;
          queue[latest] = v;
        }

        temp = temp.getNext();
      }
    }
  }
}

/**
 * Node class for the adjacency linked list.
 *
 * @author ev
 */
class Node {
  private int vertex; // vertex number.
  private Node next; // next node in the linked list.

  /**
   * This is the constructor for the Node class.
   *
   * @param vertex the vertex number.
   */
  public Node(int vertex) {
    this.vertex = vertex;
  }

  /**
   * This method is used to get the vertex number.
   *
   * @return the vertex number.
   */
  public int getVertex() {
    return vertex;
  }

  /**
   * This method is used to set the vertex number.
   *
   * @param vertex the vertex number.
   */
  public void setVertex(int vertex) {
    this.vertex = vertex;
  }

  /**
   * This method is used to get the next node in the linked list.
   *
   * @return the next node in the linked list.
   */
  public Node getNext() {
    return next;
  }

  /**
   * This method is used to set the next node in the linked list.
   *
   * @param next the next node in the linked list.
   */
  public void setNext(Node next) {
    this.next = next;
  }
}