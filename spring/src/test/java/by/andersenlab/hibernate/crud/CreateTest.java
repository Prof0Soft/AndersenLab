package by.andersenlab.hibernate.crud;

import by.andersenlab.config.ConfigSpring;
import by.andersenlab.travelagency.model.Order;
import by.andersenlab.travelagency.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;


class CreateTest {
    private Create create;
    private Read read;

    private String nameUser = "Sergey";

    @BeforeEach
    private void setUp() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigSpring.class);
        read = context.getBean(Read.class);
        create = context.getBean(Create.class);

        User user = new User(null, nameUser, null);
        create.insertNewUser(user);
    }

    @Test
    void insertNewUser() {
        User userNew = read.getUser(nameUser);
        Order order = new Order(null, "First order", userNew, 20D, null);
        create.insertNewOrder(order);

        List<User> users = read.getAllUsers();

        Assertions.assertNotNull(read.getUser(nameUser));
    }
}