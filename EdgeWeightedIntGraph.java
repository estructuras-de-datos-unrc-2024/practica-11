package studentsolutions;

import java.util.LinkedList;
import java.util.List;

/**
 *  EdgeWeightedIntGraph represents an edge-weighted
 *  graph of vertices named 0 through V - 1, where each
 *  edge is of type Edge and has a real-valued weight.
 */
public class EdgeWeightedIntGraph {
    // number of vertices in this digraph
    private final int V;
    // number of edges in this digraph
    private int E;
    // adj[v] = adjacency list for vertex v
    private List<Edge>[] adj;

    /**
     * @pre V >= 0
     * @post Initializes an edge-weighted graph with V vertices and 0 edges.
     */
    public EdgeWeightedIntGraph(int V) {
        if (V < 0)
            throw new IllegalArgumentException("Number of vertices in a Digraph must be non-negative");
        this.V = V;
        this.E = 0;
        adj = new LinkedList[V];
        for (int v = 0; v < V; v++)
            adj[v] = new LinkedList<Edge>();
    }

    /**
     * @post Returns the number of vertices in this graph.
     */
    public int V() {
        return V;
    }

    /**
     * @post Returns the number of edges in this graph.
     */
    public int E() {
        return E;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * @pre 0 <= e.from < V && 0 <= e.to < V
     * @post Adds an undirected edge e (e.from<--e.weight-->e.to)
     *   to this edge-weighted graph.
     */
    public void addEdge(Edge e) {
        if (e.from < 0 || e.from >= V)
            throw new IllegalArgumentException("vertex " + e.from +
                    " is not between 0 and " + (V-1));
        if (e.to < 0 || e.to >= V)
            throw new IllegalArgumentException("vertex " + e.to +
                    " is not between 0 and " + (V-1));
        adj[e.from].add(e);
        adj[e.to].add(e);
        E++;
    }

    /**
     * @pre 0 <= v < V
     * @post Returns the list of edges going out from vertex v.
     */
    public List<Edge> adj(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v +
                    " is not between 0 and " + (V-1));
        return adj[v];
    }

    /**
     * @post Returns all edges in this edge-weighted graph.
     */
    public List<Edge> edges() {
        List<Edge> list = new LinkedList<Edge>();
        for (int v = 0; v < V; v++) {
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                }
            }
        }
        return list;
    }

    /**
     * @post Returns a string representation of this edge-weighted graph.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + '\n');
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (Edge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append('\n');
        }
        return s.toString();
    }

    /**
     * @pre 0 <= v < V && 0 <= w < V
     * @post Returns true iff there is an edge from v to w.
     */
    public boolean existsEdge(int v, int w) {
        for (Edge edge: adj(v)) {
            if (edge.to == w)
                return true;
        }
        return false;
    }

}
