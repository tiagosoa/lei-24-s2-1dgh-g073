package US13;

import java.util.*;

public class Edge implements Comparable<Edge> {
    public int source;
    public int destination;
    public int weight;
    public String sourceS;
    public String destinationS;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
    public Edge(String source, String destination, int weight) {
        this.sourceS = source; // Using hash code of strings as vertex representation
        this.destinationS = destination;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}