package graph.dijkstrasAlgorithm;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DijkstrasAlgorithm {

    public DijkstrasAlgorithm(final LinkedHashMap<Node, Map<Node, Integer>> graph) {
        this.graph = graph;
        initStartEndNode();
        initParentsTable();
        // add to coast startNode
        updateCoastTable(graph.get(startNode));
        // add to coasts table endNode.
        coasts.put(endNode, Integer.MAX_VALUE);
    }

    private Map<Node, Map<Node, Integer>> graph;
    private Map<Node, Integer> coasts = new HashMap<>();
    private Map<Node, Node> parents = new HashMap<>();
    private Node startNode = null;
    private Node endNode = null;

    public Map<Node, Node> search() {
        // childes of start Node.
//        Map<Node, Integer> childNode = graph.get(startNode);
        Node parenNode = startNode;
        Node lowestCoastNode = indLowestCostNode(coasts);

        while (lowestCoastNode != null) {
            if (!parenNode.getProcessed()) {

                Map<Node, Integer> neighbors = graph.get(lowestCoastNode);

                for (Map.Entry<Node, Integer> setNeighbors : neighbors.entrySet()) {
                    Integer newCoast = lowestCoastNode.getCoast() + setNeighbors.getValue();
                    Node child = setNeighbors.getKey();
                    if (coasts.get(child) > newCoast) {
                        coasts.put(child, newCoast);
                        parents.put(child, lowestCoastNode);
                    }
                    //close Node if pass for the Node
                   /* if (child.equals(endNode)) {
                    }*/
                }
                lowestCoastNode.setProcessed(true);
            }
            lowestCoastNode = indLowestCostNode(coasts);
        }

        return parents;
    }

    private boolean isProcess() {
        for (Map.Entry<Node, Integer> set : coasts.entrySet()) {
            if (!set.getKey().getProcessed() && !set.getKey().getEnd()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Update coast table.
     *
     * @param nodes childes Node map.
     */
    private void updateCoastTable(final Map<Node, Integer> nodes) {
        if (nodes == null) {
            return;
        }
        for (Map.Entry<Node, Integer> set : nodes.entrySet()) {
            if (set.getKey() != endNode) {
                coasts.putIfAbsent(set.getKey(), set.getValue());
            }
        }
    }

    /**
     * Searching lowest Node.
     *
     * @param nodes childes Node map.
     * @return lowest Node.
     */
    private Node indLowestCostNode(final Map<Node, Integer> nodes) {
        int lowestCoast = Integer.MAX_VALUE;
        Node lowestCoastNode = null;

        for (Map.Entry<Node, Integer> set : nodes.entrySet()) {
            if (set.getValue() < lowestCoast && !set.getKey().getProcessed() && !set.getKey().getEnd()) {
                lowestCoast = set.getValue();
                lowestCoastNode = set.getKey();
                lowestCoastNode.setCoast(set.getValue());
            }
        }
        return lowestCoastNode;
    }

    /**
     * Initialization parents table.
     */
    private void initParentsTable() {
        for (Map.Entry<Node, Map<Node, Integer>> set : graph.entrySet()) {
            if (!set.getKey().getStart() && !set.getKey().getEnd()) {
                parents.put(set.getKey(), startNode);
            }
        }
        parents.put(endNode, null);
    }

    /**
     * Searching Start and End Node
     */
    private void initStartEndNode() {
        for (Map.Entry<Node, Map<Node, Integer>> set : graph.entrySet()) {
            if (set.getKey().getStart()) {
                startNode = set.getKey();
            }
            if (set.getKey().getEnd()) {
                endNode = set.getKey();
            }
        }
    }
}
