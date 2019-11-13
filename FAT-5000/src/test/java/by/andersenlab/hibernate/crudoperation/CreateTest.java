package by.andersenlab.hibernate.crudoperation;

import by.andersenlab.travelagency.model.Order;
import by.andersenlab.travelagency.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class CreateTest {

    String nameUser = "Sergey";

    @BeforeEach
    private void setUp() {
        User user = new User(null, nameUser, null);
        new Create().insertNewUser(user);
    }

    @Test
    void insertNewUser() {
        User userNew = new Read().getUserByName(nameUser);
        Order order = new Order(null, "First order", userNew, 20D, 1);
        new Create().insertNewOrder(order);

        List<User> users = new Read().getAllUsers();
        System.out.println(users);
    }
}