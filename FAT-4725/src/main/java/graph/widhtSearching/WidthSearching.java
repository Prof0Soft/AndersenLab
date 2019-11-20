package graph.widhtSearching;

import java.util.Deque;

/**
 * Search nearest customer in graph.
 */
public class WidthSearching {

    /**
     * Searching nearest node for customer.
     *
     * @param graph original graph.
     * @return nearest customer person.
     */
    static Person search(final Deque<Person> graph) {
        while (graph.peek() != null) {
            Person person = graph.pop();
            if (!person.getChecked()) {
                if (!person.getCustomer()) {
                    if (!person.getFriends().isEmpty()) {
                        graph.addAll(person.getFriends());
                    }
                } else {
                    return person;
                }
                person.setChecked(true);
            }
        }
        return null;
    }
}
