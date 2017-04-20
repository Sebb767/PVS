package de.sebb767.pvs.assignment4;

import java.util.ArrayList;
import java.util.List;

public class SimpleGraph {
    protected ArrayList<Node> nodes = new ArrayList<>();

    public SimpleGraph() {}
    protected void addNode(Node n) { nodes.add(n); }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public static SimpleGraph randomGraph() {
        SimpleGraph sg = new SimpleGraph();

        int nodes = 7500;
        for(int i = 0; i < 7500; i++)
        {
            Node n = new Node(i, new ArrayList<>());
            for(int k = 0; k < (nodes/12); k++)
            {
                int nr = (int)(nodes * Math.random()) + 1;

                if(!n.connectedTo(nr))
                    n.addConnection(nr);
            }
        }

        return sg;
    }

    public static class Node {
        List<Integer> connections;
        int nr;

        public Node(int number, List<Integer> connections) {
            this.connections = connections;
            nr = number;
        }

        public void addConnection(int nr)
        {
            connections.add(nr);
        }

        public boolean connectedTo(int node)
        {
            return ((Integer)nr).equals(node) || connections.contains(node);
        }

        public List<Integer> getConnections() {
            return connections;
        }

        public int getNumber() {
            return nr;
        }
    }

    public static final class NodePair {
        int a, b;

        public NodePair(int a, int b) {
            this.a = (a < b) ? a : b;
            this.b = (a < b) ? b : a;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            NodePair nodePair = (NodePair) o;

            if (a != nodePair.a) return false;
            return b == nodePair.b;
        }

        @Override
        public int hashCode() {
            int result = a;
            result = 31 * result + b;
            return result;
        }
    }
}
