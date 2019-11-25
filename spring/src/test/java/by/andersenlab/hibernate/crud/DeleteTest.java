package by.andersenlab.hibernate.crud;

import by.andersenlab.hibernate.crud.impl.CreateImpl;
import by.andersenlab.hibernate.crud.impl.DeleteImpl;
import by.andersenlab.hibernate.crud.impl.ReadImpl;
import by.andersenlab.travelagency.model.Order;
import by.andersenlab.travelagency.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class DeleteTest {

    private String nameUser = "Sergey";
    private User user;
    private Order order;
    private Read read;
    private Create create;
    private Delete delete;

    @BeforeEach
    private void setUp() {
        ApplicationContext context = new ClassPathXmlApplicationContext("aplicationContextForTests.xml");
        read = (Read) context.getBean("readCRUD");
        create = (Create) context.getBean("createCRUD");
        delete = (Delete) context.getBean("deleteCRUD");

        user = new User(null, nameUser, null);
        create.insertNewUser(user);

        order = new Order(null, "First order", user, 40D, null);
        create.insertNewOrder(order);
    }

    @Test
    void deleteOrder() {
        Order orderInBase = read.getOrder(order.getId());

        delete.deleteOrder(orderInBase);

        Order resultOrder = read.getOrder(orderInBase.getId());

        Assertions.assertNull(resultOrder);
    }

    @Test
    void deleteUser() {
        User userInBase = read.getUser(user.getNameUser());

        delete.deleteUser(userInBase);

        User resultUser = read.getUser(userInBase.getIdUser());

        Assertions.assertNull(resultUser);
    }

    @Test
    void deleteUserCheckCascadeDelete() {
        User userInBase = read.getUser(user.getNameUser());

        int idUser = userInBase.getIdUser();
        int idOrder = userInBase.getOrders().stream()
                .findFirst().orElseGet(Order::new).getId();

        delete.deleteUser(userInBase);

        User resultUser = read.getUser(idUser);
        Order resultOrder = read.getOrder(idOrder);

        Assertions.assertNull(resultUser);
        Assertions.assertNull(resultOrder);
    }
}