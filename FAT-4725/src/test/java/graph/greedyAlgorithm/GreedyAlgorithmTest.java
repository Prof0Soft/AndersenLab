package graph.greedyAlgorithm;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class GreedyAlgorithmTest {

    @Test
    public void searchBestStations() {
        Set<String> statesNeeded = new HashSet<>(Arrays.asList("mt", "wa", "or", "id", "nv", "ut", "ca", "az"));

        Map<String, Set<String>> stantions = new HashMap<>();
        stantions.put("kOne", new HashSet<>(Arrays.asList("id", "nv", "ut")));
        stantions.put("kTwo", new HashSet<>(Arrays.asList("mt", "wa", "id")));
        stantions.put("kThree", new HashSet<>(Arrays.asList("ca", "or", "nv")));
        stantions.put("kFour", new HashSet<>(Arrays.asList("nv", "ut")));
        stantions.put("kFive", new HashSet<>(Arrays.asList("ca", "az")));

        GreedyAlgorithm greedyAlgorithm = new GreedyAlgorithm(statesNeeded, stantions);
        List<String> expectedresult = Arrays.asList("kTwo", "kThree", "kFour", "kFive");

        Assert.assertEquals(expectedresult, greedyAlgorithm.search());
    }
}