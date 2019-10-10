package graph.dijkstrasAlgorithm;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class DijkstrasAlgorithmTest {

    private Node start;
    private Node a;
    private Node b;
    private Node end;
    private LinkedHashMap<Node, Map<Node, Integer>> graph = new LinkedHashMap<>();

    @Before
    public void setUp() throws Exception {
        end = new Node("End", false, true);
        a = new Node("A");
        b = new Node("B");
        start = new Node("start", true, false);

        // create graph
        graph.put(start, new HashMap<Node, Integer>() {{
            put(a, 6);
            put(b, 2);
        }});
        graph.put(a, new HashMap<Node, Integer>() {{
            put(end, 1);
        }});
        graph.put(b, new HashMap<Node, Integer>() {{
            put(a, 3);
            put(end, 5);
        }});
        graph.put(end, null);
    }

    @Test
    public void search() {
        DijkstrasAlgorithm dijkstrasAlgorithm = new DijkstrasAlgorithm(graph);
        System.out.println(dijkstrasAlgorithm.search());
    }
}