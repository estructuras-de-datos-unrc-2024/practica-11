package studentsolutions;

/**
 *  Edge class represents an undirected, weighted edge in an
 *  EdgeWeightedGraph.
 */
public class Edge {
    final int from;
    final int to;
    final double weight;
    /**
     * @post Initializes an undirected edge from vertex from to
     * vertex to with the given weight.
     */
    public Edge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    /**
     * @pre v must be one of the endpoints of the edge (throws IllegalArgumentException)
     * @post Returns the endpoint of this edge that is different from the given vertex.
     */
    public int other(int v) {
        if      (v == from) return to;
        else if (v == to) return from;
        else throw new IllegalArgumentException("Illegal endpoint");
    }

    /**
     * @post Returns a string representation of the undirected edge.
     */
    public String toString() {
        return from + "<->" + to + " " + String.format("%5.2f", weight);
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge that = (Edge) o;
        return (from == that.from && to == that.to ||
                from == that.to && to == that.from) &&
                Double.compare(that.weight, weight) == 0;
    }

}