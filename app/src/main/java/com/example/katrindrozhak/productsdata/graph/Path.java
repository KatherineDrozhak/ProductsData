package com.example.katrindrozhak.productsdata.graph;

public class Path {

    private Double cumulativeWeight;
    private Node node;
    private Path previousPath = null;

    public Double getCumulativeWeight() {
        return cumulativeWeight;
    }

    public Node getNode() {
        return node;
    }

    public Path(Node node) {
        this.node = node;
        this.cumulativeWeight = 1.0;
    }

    public Path(Node node, Connection connection, Path previousPath) {
        cumulativeWeight = connection.getWeight() * previousPath.cumulativeWeight;
        this.node = node;
        this.previousPath = previousPath;
    }
}
