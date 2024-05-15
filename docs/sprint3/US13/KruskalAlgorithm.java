package US13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalAlgorithm {
    int V; // Number of vertices
    List<Edge> edges;

    public KruskalAlgorithm(int V) {
        this.V = V;
        edges = new ArrayList<>();
    }

    public void addEdge(int source, int destination, int weight) {
        edges.add(new Edge(source, destination, weight));
    }
    public void addEdge(String source, String destination, int weight) {
        edges.add(new Edge(source, destination, weight));
    }

    public List<Edge> kruskalMST() {
        List<Edge> result = new ArrayList<>();
        // Sort edges in ascending order of weight
        Collections.sort(edges);

        DisjointSet ds = new DisjointSet(V);

        for (Edge edge : edges) {
            int sourceParent = ds.find(edge.source);
            int destParent = ds.find(edge.destination);

            // If including this edge doesn't cause cycle, include it in result and union
            // the sets of source and destination
            if (sourceParent != destParent) {
                result.add(edge);
                ds.union(sourceParent, destParent);
            }
        }
        return result;
    }
}
