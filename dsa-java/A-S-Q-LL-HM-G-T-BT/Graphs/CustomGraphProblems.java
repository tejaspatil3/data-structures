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
|| NOTES: COMMON GRAPH PROBLEMS FOR TESTS & INTERVIEWS (FROM SCRATCH)
================================================================================

This file demonstrates solutions to common problems that test your understanding of
graph algorithms. The solutions use a custom, from-scratch `Graph` implementation
(provided below) to show how to apply these algorithms to the underlying structure.

Problems covered:
1.  **Path Existence (Find if a Path Exists)**:
    A fundamental graph problem. Given a source and a destination vertex, determine if it's
    possible to travel from the source to the destination by following the graph's edges.
    This can be solved with either BFS or DFS.

2.  **Count Connected Components**:
    For an undirected graph, a "connected component" is a subgraph where every vertex is
    reachable from every other vertex within that subgraph. This algorithm counts how many
    such separate "islands" of vertices exist in the graph. It's a great test of
    your ability to handle graphs that may not be fully connected.

3.  **Detect a Cycle in a Directed Graph**:
    A very common and important interview question. A cycle exists if you can start at a
    node, follow a sequence of directed edges, and arrive back at the same node. The
    solution requires a modified DFS that keeps track of the nodes currently in the
    "recursion stack" or the path being explored.

4.  **Shortest Path in an Unweighted Graph**:
    This problem asks for the shortest path (in terms of the number of edges) from a
    single source vertex to all other reachable vertices. This is the canonical use case
    for the Breadth-First Search (BFS) algorithm, as it naturally explores the graph
    layer by layer, guaranteeing that it finds the shortest path first.
*/

/**
 * Main public class to run the problem examples.
 */
public class CustomGraphProblems {

    public static void main(String[] args) {

        // --- Problem 1: Path Existence ---
        System.out.println("## Problem 1: Path Existence in a Directed Graph");
        Graph<String> flightRoutes = createDirectedGraph();
        String source = "Pune";
        String dest1 = "New York";
        String dest2 = "Sydney";
        System.out.println("   Does a path exist from " + source + " to " + dest1 + "? " + pathExists(flightRoutes, source, dest1));
        System.out.println("   Does a path exist from " + source + " to " + dest2 + "? " + pathExists(flightRoutes, source, dest2));
        System.out.println("--------------------------------------------------\n");

        // --- Problem 2: Count Connected Components ---
        System.out.println("## Problem 2: Count Connected Components in an Undirected Graph");
        Graph<Integer> disconnectedGraph = createDisconnectedGraph();
        System.out.println("   Number of connected components: " + countConnectedComponents(disconnectedGraph));
        System.out.println("--------------------------------------------------\n");

        // --- Problem 3: Detect a Cycle in a Directed Graph ---
        System.out.println("## Problem 3: Detect a Cycle in a Directed Graph");
        Graph<Integer> cyclicGraph = createCyclicDirectedGraph();
        System.out.println("   Does the flight graph have a cycle? " + hasCycleDirected(flightRoutes)); // Re-using graph from problem 1
        System.out.println("   Does the numeric graph have a cycle? " + hasCycleDirected(cyclicGraph));
        System.out.println("--------------------------------------------------\n");

        // --- Problem 4: Shortest Path in an Unweighted Graph ---
        System.out.println("## Problem 4: Shortest Path from a Source (BFS)");
        Graph<String> unweightedGraph = createUndirectedGraphForShortestPath();
        String startNode = "A";
        Map<String, Integer> distances = shortestPathBfs(unweightedGraph, startNode);
        System.out.println("   Shortest distances from node '" + startNode + "':");
        for (Map.Entry<String, Integer> entry : distances.entrySet()) {
            System.out.println("   - To " + entry.getKey() + ": " + entry.getValue() + " edges");
        }
        System.out.println("--------------------------------------------------\n");
    }

    // --- Helper methods to create graphs for testing ---
    private static Graph<String> createDirectedGraph() {
        Graph<String> g = new Graph<>();
        g.addEdge("Pune", "Dubai");
        g.addEdge("Dubai", "New York");
        g.addEdge("Pune", "Singapore");
        g.addEdge("Singapore", "Tokyo");
        g.addVertex("Sydney"); // An unreachable city
        return g;
    }
    private static Graph<Integer> createDisconnectedGraph() {
        Graph<Integer> g = new Graph<>();
        g.addEdgeUndirected(1, 2); g.addEdgeUndirected(2, 3); // Component 1
        g.addEdgeUndirected(4, 5); // Component 2
        g.addVertex(6); // Component 3
        return g;
    }
    private static Graph<Integer> createCyclicDirectedGraph() {
        Graph<Integer> g = new Graph<>();
        g.addEdge(1, 2); g.addEdge(2, 3); g.addEdge(3, 1); // Cycle 1->2->3->1
        g.addEdge(3, 4);
        return g;
    }
    private static Graph<String> createUndirectedGraphForShortestPath() {
        Graph<String> g = new Graph<>();
        g.addEdgeUndirected("A", "B"); g.addEdgeUndirected("A", "C");
        g.addEdgeUndirected("B", "D"); g.addEdgeUndirected("B", "E");
        g.addEdgeUndirected("C", "F"); g.addEdgeUndirected("E", "F");
        return g;
    }
    
    // --- Solutions to the Problems ---

    /**
     * Solution for Problem 1: Checks if a path exists between two nodes using DFS.
     */
    public static <T> boolean pathExists(Graph<T> graph, T source, T destination) {
        if (!graph.hasVertex(source) || !graph.hasVertex(destination)) return false;
        
        Stack<T> stack = new Stack<>();
        Set<T> visited = new HashSet<>();
        stack.push(source);
        visited.add(source);

        while (!stack.isEmpty()) {
            T current = stack.pop();
            if (current.equals(destination)) {
                return true; // Path found
            }
            for (T neighbor : graph.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    stack.push(neighbor);
                }
            }
        }
        return false; // Traversal finished, destination not found
    }

    /**
     * Solution for Problem 2: Counts the number of connected components.
     */
    public static <T> int countConnectedComponents(Graph<T> graph) {
        int count = 0;
        Set<T> visited = new HashSet<>();
        for (T vertex : graph.getAllVertices()) {
            if (!visited.contains(vertex)) {
                // Start a new traversal for a new component
                dfsForComponents(graph, vertex, visited);
                count++;
            }
        }
        return count;
    }
    
    // Helper DFS for countConnectedComponents
    private static <T> void dfsForComponents(Graph<T> graph, T start, Set<T> visited) {
        Stack<T> stack = new Stack<>();
        stack.push(start);
        visited.add(start);
        while (!stack.isEmpty()) {
            T current = stack.pop();
            for (T neighbor : graph.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    stack.push(neighbor);
                }
            }
        }
    }

    /**
     * Solution for Problem 3: Detects if a cycle exists in a directed graph.
     */
    public static <T> boolean hasCycleDirected(Graph<T> graph) {
        Set<T> visited = new HashSet<>();   // Nodes we've already fully explored
        Set<T> visiting = new HashSet<>();  // Nodes in the current recursion path
        
        for (T vertex : graph.getAllVertices()) {
            if (!visited.contains(vertex)) {
                if (hasCycleUtil(graph, vertex, visited, visiting)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    // Recursive DFS helper for hasCycleDirected
    private static <T> boolean hasCycleUtil(Graph<T> graph, T current, Set<T> visited, Set<T> visiting) {
        visiting.add(current); // Mark as currently visiting
        
        for (T neighbor : graph.getNeighbors(current)) {
            // If the neighbor is in the current path, we have a cycle (a "back edge").
            if (visiting.contains(neighbor)) {
                return true;
            }
            // If the neighbor hasn't been fully explored yet, recurse.
            if (!visited.contains(neighbor)) {
                if (hasCycleUtil(graph, neighbor, visited, visiting)) {
                    return true;
                }
            }
        }
        
        visiting.remove(current); // Backtrack: remove from current path
        visited.add(current);     // Mark as fully explored
        return false;
    }

    /**
     * Solution for Problem 4: Finds the shortest path from a start node to all others.
     */
    public static <T> Map<T, Integer> shortestPathBfs(Graph<T> graph, T startNode) {
        Map<T, Integer> distances = new HashMap<>();
        Queue<T> queue = new LinkedList<>();
        
        if (!graph.hasVertex(startNode)) return distances;
        
        distances.put(startNode, 0);
        queue.offer(startNode);
        
        while (!queue.isEmpty()) {
            T current = queue.poll();
            int currentDist = distances.get(current);
            
            for (T neighbor : graph.getNeighbors(current)) {
                if (!distances.containsKey(neighbor)) { // If not visited
                    distances.put(neighbor, currentDist + 1);
                    queue.offer(neighbor);
                }
            }
        }
        return distances;
    }
}


//==============================================================================
//== FROM-SCRATCH IMPLEMENTATION OF A GRAPH
//==============================================================================
class Graph<T> {
    private Map<T, List<T>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addVertex(T vertex) {
        adjacencyList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(T source, T destination) {
        addVertex(source);
        addVertex(destination);
        adjacencyList.get(source).add(destination);
    }
    
    public void addEdgeUndirected(T source, T destination) {
        addEdge(source, destination);
        addEdge(destination, source);
    }

    // --- Helper methods needed for the problems ---
    public boolean hasVertex(T vertex) {
        return adjacencyList.containsKey(vertex);
    }

    public List<T> getNeighbors(T vertex) {
        return adjacencyList.getOrDefault(vertex, new ArrayList<>());
    }

    public Set<T> getAllVertices() {
        return adjacencyList.keySet();
    }
}