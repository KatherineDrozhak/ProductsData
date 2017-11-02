package com.example.katrindrozhak.productsdata.graph;

public class Connection {
    private Node to;
    private Double weight;

    public Connection(Node to, Double weight) {
        this.to = to;
        this.weight = weight;
    }

    public Double getWeight() {
        return weight;
    }

    public Node getTo() {
        return to;
    }
}
