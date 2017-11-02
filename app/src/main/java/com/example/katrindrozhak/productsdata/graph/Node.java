package com.example.katrindrozhak.productsdata.graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String name;
    private boolean visited = false;
    private List<Connection> connections = new ArrayList<>();

    public Node(String name) {
        this.name = name;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Node)) {
            return false;
        }

        Node o = (Node) obj;

        return o.name.equals(this.name);
    }
}
