package studentsolutions;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class KruskalMST {
    private double weight;                        // weight of MST
    private List<Edge> mst = new LinkedList<Edge>();  // edges in MST

    public KruskalMST() {
    };

    /**
     * @post Compute a minimum spanning tree of edge-weighted graph G.
     */
    public void kruskal(EdgeWeightedIntGraph G) {

        // create array of edges, sorted by weight
        Edge[] edges = new Edge[G.E()];
        int t = 0;
        for (Edge e: G.edges()) {
            edges[t++] = e;
        }
        Arrays.sort(edges);

        // run greedy algorithm
        UF uf = new UF(G.V());
        for (int i = 0; i < G.E() && mst.size() < G.V() - 1; i++) {
            Edge e = edges[i];
            int v = e.from;
            int w = e.other(v);

            // v-w does not create a cycle
            if (uf.find(v) != uf.find(w)) {
                uf.union(v, w);     // merge v and w components
                mst.add(e);     // add edge e to mst
                weight += e.weight;
            }
        }

    }

    /**
     * @post Returns the edges in a minimum spanning tree.
     */
    public List<Edge> edges() {
        return mst;
    }

    /**
     * @post Returns the sum of the edge weights in a minimum spanning tree.
     */
    public double weight() {
        return weight;
    }

}
