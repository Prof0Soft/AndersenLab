package graph.dijkstrasAlgorithm;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

/**
 * Node for Dijkstra graph.
 */
@AllArgsConstructor
@Data
public class Node {

    public Node(final String name) {
        this.name = name;
    }

    public Node(final String name, final Boolean start, final Boolean end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }

    private String name;
    private Integer coast = null;
    private Boolean start = false;
    private Boolean end = false;
    private Boolean processed = false;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Node node = (Node) o;
        return Objects.equals(name, node.name) && Objects.equals(start, node.start) && Objects.equals(end, node.end)
                && Objects.equals(processed, node.processed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, start, end, processed);
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
