package by.andersenlab.service;

import by.andersenlab.hibernate.crud.Read;
import by.andersenlab.service.impl.TravelServiceImpl;
import by.andersenlab.travelagency.model.Order;
import by.andersenlab.travelagency.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import util.ViewSpringInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ActiveProfiles(value = "test")
public class TravelServiceTest {

    private TravelServiceImpl travelService;
    private Read read;

    @BeforeEach
    private void setUp() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("./");
        context.refresh();

        ViewSpringInfo.showSpringBeansInfo();

        read = context.getBean(Read.class);
        travelService = context.getBean(TravelServiceImpl.class);
    }

    @Test
    void findUserWithMinOrderPrice_NotOrdersAllUser_GiveFirstMinUser() {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Pieter", null));
        users.add(new User(2, "Bob", null));
        users.add(new User(3, "Jon", null));

        Mockito.when(read.getAllUsers()).thenReturn(users);

        User userResult = travelService.findUserWithMinOrderPrice();

        assertEquals(users.get(0), userResult);
    }

    @Test
    void findUserWithMinOrderPrice_HandsOrderSet_GiveThirdUser() {
        User user1 = new User(1, "Pieter", null);
        User user2 = new User(2, "Bob", null);
        User user3 = new User(3, "Jon", null);

        Order order1 = new Order(1, "Test 1", user1, 25D, null);
        Order order2 = new Order(2, "Test 2", user2, 30D, null);
        Order order3 = new Order(3, "Test 3", user3, 10D, null);

        user1.setOrders(Collections.singleton(order1));
        user2.setOrders(Collections.singleton(order2));
        user3.setOrders(Collections.singleton(order3));

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);

        Mockito.when(read.getAllUsers()).thenReturn(users);

        User userResult = travelService.findUserWithMinOrderPrice();

        assertEquals(users.get(2), userResult);
    }
}
