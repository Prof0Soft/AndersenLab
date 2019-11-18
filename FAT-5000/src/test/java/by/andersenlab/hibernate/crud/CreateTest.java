package by.andersenlab.hibernate.crud;

import by.andersenlab.travelagency.model.Order;
import by.andersenlab.travelagency.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class CreateTest {

    private String nameUser = "Sergey";

    @BeforeEach
    private void setUp() {
        User user = new User(null, nameUser, null);
        new Create().insertNewUser(user);
    }

    @Test
    void insertNewUser() {
        User userNew = new Read().getUser(nameUser);
        Order order = new Order(null, "First order", userNew, 20D, null);
        new Create().insertNewOrder(order);

        List<User> users = new Read().getAllUsers();

        Assertions.assertNotNull(new Read().getUser(nameUser));
    }
}