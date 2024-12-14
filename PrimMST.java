package studentsolutions;

import java.util.LinkedList;
import java.util.List;

public class PrimMST {

    private Edge[] edgeTo;        // edgeTo[v] = shortest edge from tree vertex to non-tree vertex
    private double[] distTo;      // distTo[v] = weight of shortest such edge
    private boolean[] marked;     // marked[v] = true if v on tree, false otherwise
    private IndexMinPQ<Double> pq;

    public PrimMST() {

    }

    /**
     * @post Compute a minimum spanning tree of edge-weighted graph G
     *   starting at s.
     */
    public void prim(EdgeWeightedIntGraph G, int s) {
        if (s < 0 || s >= G.V())
            throw new IllegalArgumentException("vertex " + s +
                    " is not between 0 and " + (G.V()-1));

        // Initialization
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        pq = new IndexMinPQ<Double>(G.V());
        for (int v = 0; v < G.V(); v++) {
            if (v != s) {
                distTo[v] = Double.POSITIVE_INFINITY;
                pq.insert(v, distTo[v]);
            }
        }

        // Computation of the MST
        distTo[s] = 0.0;
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            marked[v] = true; // marked vertices belong to the MST
            for (Edge e : G.adj(v)) {
                int w = e.other(v);
                if (marked[w]) continue; // v-w is already in the MST
                if (e.weight < distTo[w]) {
                    distTo[w] = e.weight;
                    edgeTo[w] = e;
                    pq.decreaseKey(w, distTo[w]);
                }
            }
        }
    }

    /**
     * @post Returns the edges in a minimum spanning tree.
     */
    public List<Edge> edges() {
        List<Edge> mst = new LinkedList<>();
        for (int v = 0; v < edgeTo.length; v++) {
            Edge e = edgeTo[v];
            if (e != null) {
                mst.add(e);
            }
        }
        return mst;
    }

    /**
     * @post Returns the sum of the edge weights in a minimum spanning tree.
     */
    public double weight() {
        double weight = 0.0;
        for (Edge e : edges())
            weight += e.weight;
        return weight;
    }

}
