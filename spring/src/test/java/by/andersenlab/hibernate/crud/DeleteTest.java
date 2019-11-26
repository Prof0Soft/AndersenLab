package by.andersenlab.hibernate.crud;

import by.andersenlab.config.ConfigSpring;
import by.andersenlab.travelagency.model.Order;
import by.andersenlab.travelagency.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class DeleteTest {

    private String nameUser = "Sergey";
    private User user;
    private Order order;
    private Read read;
    private Create create;
    private Delete delete;

    @BeforeEach
    private void setUp() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigSpring.class);
        read = context.getBean(Read.class);
        create = context.getBean(Create.class);
        delete = context.getBean(Delete.class);

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