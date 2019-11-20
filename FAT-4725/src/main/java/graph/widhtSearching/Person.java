package graph.widhtSearching;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Node for graph.
 */
@Data
public class Person {

    Person(final String name, final Boolean customer) {
        this.name = name;
        this.customer = customer;
    }

    Person(final String name, final Boolean customer, final List<Person> friends) {
        this.name = name;
        this.customer = customer;
        this.friends = friends;
    }

    private String name;
    private Boolean customer;
    private List<Person> friends = new ArrayList<>();
    private Boolean checked = false;
}
