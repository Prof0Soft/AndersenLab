package graph.widhtSearching;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class WidthSearchingTest {

    private Deque<Person> graph = new LinkedList<>();
    private Person anudg;
    private Person tom;
    private Person jony;
    private Person peggi;
    private Person alis;
    private Person bob;
    private Person kler;
    private Person you;

    @Before

    public void createGraph() {
        anudg = new Person("Anudg", false);
        tom = new Person("Tom", false);
        jony = new Person("Jony", false);
        peggi = new Person("Peggi", false);
        alis = new Person("Alis", false, Collections.singletonList(peggi));
        bob = new Person("Bob", false, Arrays.asList(anudg, peggi));
        kler = new Person("Kler", false, Arrays.asList(tom, jony));
        you = new Person("You", false, Arrays.asList(bob, kler, alis));

    }

    @Test
    public void searchCustomerPeggi() {
        peggi.setCustomer(true);
        graph.add(you);
        Assert.assertEquals(peggi, WidthSearching.search(graph));
    }

    @Test
    public void searchCustomerAnudg() {
        anudg.setCustomer(true);
        graph.add(you);
        Assert.assertEquals(anudg, WidthSearching.search(graph));
    }

    @Test
    public void searchCustomerNull() {
        graph.add(you);
        Assert.assertEquals(null, WidthSearching.search(graph));
    }
}
