package com.example.katrindrozhak.productsdata;

import com.example.katrindrozhak.productsdata.graph.Connection;
import com.example.katrindrozhak.productsdata.graph.Node;
import com.example.katrindrozhak.productsdata.graph.Path;
import com.example.katrindrozhak.productsdata.model.Rate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyConverter {

    private Map<String, Node> nodeMap = new HashMap<>();

    public CurrencyConverter(List<Rate> rates) {
        setup(rates);
    }

    private void setup(List<Rate> rates) {
        for (Rate r : rates) {
            Node from = nodeMap.get(r.getFrom());
            if (from == null) {
                from = new Node(r.getFrom());
                nodeMap.put(r.getFrom(), from);
            }

            Node to = nodeMap.get(r.getTo());
            if (to == null) {
                to = new Node(r.getTo());
                nodeMap.put(r.getTo(), to);
            }

            from.getConnections().add(new Connection(to, r.getValue()));
        }
    }

    public Double convert(Double price, String from, String to) {
        clearCache();
        Node fromNode = nodeMap.get(from);
        Node toNode = nodeMap.get(to);

        if (fromNode != null && toNode != null) {

            Path path = path(fromNode, toNode);
            if (path != null) {
                price *= path.getCumulativeWeight();

                price = Math.rint(100.00 * price) / 100.00;
            }

            return price;
        }
        return 0.0;
    }

    private Path path(Node from, Node to) {
        List<Path> frontier = new ArrayList<>();

        frontier.add(new Path(from));

        while (!frontier.isEmpty()) {
            Path tmp = frontier.remove(0);
            if (tmp.getNode().isVisited()) {
                continue;
            }

            if (tmp.getNode().equals(to)) {
                return tmp;
            }

            tmp.getNode().setVisited(true);

            for (Connection c : tmp.getNode().getConnections()) {
                if (!c.getTo().isVisited()) {
                    frontier.add(new Path(c.getTo(), c, tmp));
                }
            }
        }
        return null;
    }

    private void clearCache() {
        for (Node n : nodeMap.values()) {
            n.setVisited(false);
        }
    }
}
