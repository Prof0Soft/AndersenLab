package by.andersenlab.hibernate.crud;

import by.andersenlab.hibernate.crud.impl.CreateImpl;
import by.andersenlab.hibernate.crud.impl.ReadImpl;
import by.andersenlab.travelagency.model.Order;
import by.andersenlab.travelagency.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


class CreateTest {
    private Create create;
    private Read read;

    private String nameUser = "Sergey";

    @BeforeEach
    private void setUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("aplicationContextForTests.xml");
        read = (Read) context.getBean("readCRUD");
        create = (Create) context.getBean("createCRUD");

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