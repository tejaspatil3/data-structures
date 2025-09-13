import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/*
================================================================================
|| THEORY: UNDERSTANDING GRAPHS
================================================================================

1.  WHAT IS A GRAPH?
    - A Graph is a non-linear data structure consisting of a set of **Vertices** (also called nodes)
      and a set of **Edges** that connect these vertices.
    - It's a way to represent networks and relationships between objects.
    - Analogy: A social network is a perfect graph. Each person is a **vertex**, and a "friendship"
      is an **edge** connecting two people. Other examples include city road maps, the internet, etc.

2.  KEY TERMINOLOGY
    - **Vertex (Node)**: The fundamental unit of the graph.
    - **Edge (Link/Arc)**: The connection between two vertices.
    - **Undirected Graph**: Edges are two-way. If A is connected to B, B is also connected to A (like a Facebook friendship).
    - **Directed Graph (Digraph)**: Edges are one-way (A -> B). If A follows B on Instagram, it doesn't mean B follows A.
    - **Weighted Graph**: Each edge has a value or "weight" (e.g., the distance between two cities).
    - **Unweighted Graph**: Edges simply represent a connection, with no associated value.

3.  HOW TO REPRESENT A GRAPH
    There are two primary ways to represent a graph in code:

    a) Adjacency Matrix:
    - A 2D array where `matrix[i][j] = 1` if there is an edge from vertex `i` to vertex `j`.
    - **Pros**: Very fast to check if an edge exists between two specific vertices (O(1)).
    - **Cons**: Uses a lot of memory (V² where V is the number of vertices), which is inefficient for graphs with few edges ("sparse" graphs).

    b) Adjacency List (Most Common):
    - A collection (like a Map or an array of lists) where each vertex has a list of all the vertices it is connected to.
    - **Pros**: Memory-efficient for sparse graphs. Easy to get all neighbors of a vertex.
    - **Cons**: Slower to check if a specific edge exists (you have to search the list of neighbors).
    - **Our Implementation**: We will use an Adjacency List because it's more flexible and common.

4.  GRAPH TRAVERSAL ALGORITHMS
    Traversal means visiting every vertex in the graph in a systematic way.

    a) Breadth-First Search (BFS):
    - **Concept**: Explores the graph layer by layer. It starts at a source vertex, explores all of its immediate neighbors, and then for each of those neighbors, explores their unexplored neighbors, and so on.
    - **Analogy**: The spreading of ripples in a pond. 🌊
    - **Data Structure Used**: A **Queue** (First-In, First-Out).

    b) Depth-First Search (DFS):
    - **Concept**: Explores as far as possible down one path before backtracking. It starts at a source vertex, picks one neighbor, and explores everything reachable from that neighbor before going back to explore other neighbors of the source.
    - **Analogy**: Navigating a maze by always taking one path until you hit a dead end, then backtracking.  labyrinth
    - **Data Structure Used**: A **Stack** (Last-In, First-Out), often implemented implicitly via recursion. We will show the iterative version with an explicit Stack.
*/

/**
 * Main public class to run the Graph examples.
 */
public class GraphExample {
    public static void main(String[] args) {
        System.out.println("--- Building a Social Network Graph (Undirected) ---");
        // Using String vertices for a relatable example.
        Graph<String> socialNetwork = new Graph<>();

        // Add people (vertices) to the network
        socialNetwork.addVertex("Alice");
        socialNetwork.addVertex("Bob");
        socialNetwork.addVertex("Charlie");
        socialNetwork.addVertex("David");
        socialNetwork.addVertex("Eve");
        socialNetwork.addVertex("Frank");

        // Add friendships (undirected edges)
        socialNetwork.addEdgeUndirected("Alice", "Bob");
        socialNetwork.addEdgeUndirected("Alice", "Charlie");
        socialNetwork.addEdgeUndirected("Bob", "David");
        socialNetwork.addEdgeUndirected("Charlie", "Eve");
        socialNetwork.addEdgeUndirected("David", "Eve");
        socialNetwork.addEdgeUndirected("David", "Frank");
        socialNetwork.addEdgeUndirected("Eve", "Frank");

        System.out.println("Graph Representation (Adjacency List):");
        socialNetwork.printGraph();

        System.out.println("\n--- 1. Breadth-First Search (BFS) Traversal ---");
        System.out.println("Starting BFS from 'Alice':");
        System.out.print("   ");
        socialNetwork.bfs("Alice");

        System.out.println("\n\n--- 2. Depth-First Search (DFS) Traversal ---");
        System.out.println("Starting DFS from 'Alice':");
        System.out.print("   ");
        socialNetwork.dfs("Alice");
    }
}


/**
 * A generic, from-scratch implementation of a Graph using an Adjacency List.
 * @param <T> The type of data to be stored in the vertices.
 */
class Graph<T> {
    // The adjacency list is a map where the key is a vertex and the value is a
    // list of all adjacent vertices.
    private Map<T, List<T>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    /**
     * Adds a new vertex to the graph.
     * @param vertex The vertex to add.
     */
    public void addVertex(T vertex) {
        // putIfAbsent ensures we don't overwrite an existing vertex's adjacency list.
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    /**
     * Adds a directed edge from a source vertex to a destination vertex.
     * @param source The source vertex.
     * @param destination The destination vertex.
     */
    public void addEdge(T source, T destination) {
        // Ensure both vertices exist in the graph before adding an edge.
        addVertex(source);
        addVertex(destination);

        // Add the destination to the source's list of neighbors.
        adjacencyList.get(source).add(destination);
    }

    /**
     * Adds an undirected edge, which is a two-way connection.
     */
    public void addEdgeUndirected(T source, T destination) {
        addEdge(source, destination);
        addEdge(destination, source);
    }

    /**
     * Performs a Breadth-First Search (BFS) traversal starting from a given vertex.
     * @param startNode The vertex to start the traversal from.
     */
    public void bfs(T startNode) {
        if (!adjacencyList.containsKey(startNode)) {
            System.out.println("Start node does not exist.");
            return;
        }

        Queue<T> queue = new LinkedList<>(); // Use a Queue for BFS
        Set<T> visited = new HashSet<>();   // Use a Set to track visited nodes to avoid cycles

        // Start the traversal
        queue.offer(startNode);
        visited.add(startNode);

        while (!queue.isEmpty()) {
            T currentVertex = queue.poll();
            System.out.print(currentVertex + " -> ");

            // Get all neighbors of the current vertex
            for (T neighbor : adjacencyList.get(currentVertex)) {
                // If the neighbor has not been visited, add it to the queue and mark as visited
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);

                    queue.offer(neighbor);
                }
            }
        }
        System.out.print("End");
    }

    /**
     * Performs a Depth-First Search (DFS) traversal starting from a given vertex (iterative version).
     * @param startNode The vertex to start the traversal from.
     */
    public void dfs(T startNode) {
        if (!adjacencyList.containsKey(startNode)) {
            System.out.println("Start node does not exist.");
            return;
        }

        Stack<T> stack = new Stack<>();     // Use a Stack for DFS
        Set<T> visited = new HashSet<>();   // Track visited nodes

        // Start the traversal
        stack.push(startNode);

        while (!stack.isEmpty()) {
            T currentVertex = stack.pop();

            // If the node has not been visited yet
            if (!visited.contains(currentVertex)) {
                System.out.print(currentVertex + " -> ");
                visited.add(currentVertex);

                // Add all unvisited neighbors to the stack
                // We add them in reverse order of the neighbor list to visit them in natural order
                List<T> neighbors = adjacencyList.get(currentVertex);
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    T neighbor = neighbors.get(i);
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        System.out.print("End");
    }

    /**
     * Utility method to print the graph's adjacency list representation.
     */
    public void printGraph() {
        for (T vertex : adjacencyList.keySet()) {
            System.out.println("   " + vertex + " -> " + adjacencyList.get(vertex));
        }
    }
}