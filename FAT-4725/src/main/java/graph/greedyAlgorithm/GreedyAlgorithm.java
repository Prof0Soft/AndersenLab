package graph.greedyAlgorithm;

import java.util.*;

/**
 * Use greedy algorithm for searching best radio stations.
 */
public class GreedyAlgorithm {
    private Set<String> statesNeeded;
    private Map<String, Set<String>> stations;

    public GreedyAlgorithm(final Set<String> statesNeeded, final Map<String, Set<String>> stations) {
        this.statesNeeded = statesNeeded;
        this.stations = stations;
    }

    /**
     * Start searching process.
     *
     * @return list of best stations.
     */
    public List<String> search() {
        List<String> finalStations = new ArrayList<>();

        while (!statesNeeded.isEmpty()) {
            String bestStantion = null;
            Set<String> statesCovered = stations.get(bestStantion);

            for (Map.Entry<String, Set<String>> set : stations.entrySet()) {
                Set<String> intersection = new HashSet<>(statesNeeded);
                intersection.retainAll(set.getValue());
                int countNeeded = intersection.size();
                if (countNeeded > (statesCovered != null ? statesCovered.size() : 0)) {
                    bestStantion = set.getKey();
                    statesCovered = intersection;
                }
            }
            statesNeeded.removeAll(statesCovered);
            finalStations.add(bestStantion);
        }
        return finalStations;
    }
}
