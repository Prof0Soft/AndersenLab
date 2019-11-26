package by.andersenlab.hibernate.crud;

import by.andersenlab.config.ConfigSpring;
import by.andersenlab.travelagency.model.Order;
import by.andersenlab.travelagency.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class UpdateTest {

    private String nameUser = "Sergey";
    private User user;
    private Order order;

    private Read read;
    private Create create;
    private Update update;

    @BeforeEach
    private void setUp() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigSpring.class);
        read = context.getBean(Read.class);
        create = context.getBean(Create.class);
        update = context.getBean(Update.class);

        user = new User(null, nameUser, null);
        create.insertNewUser(user);

        order = new Order(null, "First order", user, 40D, null);
        create.insertNewOrder(order);
    }

    @Test
    void updateUser() {
        user = read.getUser(nameUser);

        String newUserName = "Bob";
        user.setNameUser(newUserName);
        update.updateUser(user);

        User resultUser = read.getUser(newUserName);

        Assertions.assertNotNull(resultUser);
        Assertions.assertEquals(newUserName, resultUser.getNameUser());
    }

    @Test
    void updateOrder() {
        String newOrderName = "Second name";
        User secondUser = create.insertNewUser(new User(null, "Gena", null));

        order.setUserId(secondUser);
        order.setNameOrder(newOrderName);

        update.updateOrder(order);

        Order resultOrder = read.getOrder(order.getNameOrder());

        Assertions.assertNotNull(resultOrder);
        Assertions.assertEquals(secondUser.getIdUser(), resultOrder.getUserId().getIdUser());
        Assertions.assertEquals(newOrderName, resultOrder.getNameOrder());
    }
}