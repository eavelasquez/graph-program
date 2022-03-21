/******************************************************************************
 *  Compilation:  javac GraphProgram.java
 *  Execution:    java GraphProgram
 *  
 *  A graph, implementing with adjacency linked list and matrix.
 *
 ******************************************************************************/
package graph.program;

/**
 * The {@code GraphProgram} class represents a graph program.
 *
 * @author ev
 */
public class GraphProgram {

    /**
     * Unit tests the {@code GraphProgram} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int vertexCount = 5;
        int[][] edges = { { 0, 1 }, { 0, 2 }, { 1, 2 }, { 1, 3 }, { 2, 3 }, { 3, 4 } };
        Graph graph = new GraphAdjacencyMatrix(vertexCount, edges, false);

        System.out.println(graph.toString());

        int[] visited = new int[vertexCount + 1];

        graph.transversalBfs(1);
        graph.transversalDfs(1, visited);
    }
}
