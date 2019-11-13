package by.andersenlab.hibernate.crudoperation;

import by.andersenlab.travelagency.model.Order;
import by.andersenlab.travelagency.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateTest {

    private String nameUser = "Sergey";
    private User user;
    private Order order;

    @BeforeEach
    private void setUp() {
        user = new User(null, nameUser, null);
        new Create().insertNewUser(user);

        order = new Order(null, "First order", user, 40D, null);
        new Create().insertNewOrder(order);
    }

    @Test
    void updateUser() {
        user = new Read().getUserByName(nameUser);

        String newUserName = "Bob";
        user.setNameUser(newUserName);
        new Update().updateUser(user);

        User resultUser = new Read().getUserByName(newUserName);

        Assertions.assertNotNull(resultUser);
        Assertions.assertEquals(newUserName, resultUser.getNameUser());
    }

    @Test
    void updateOrder() {
        String newOrderName = "Second name";
        User secondUser = new Create().insertNewUser(new User(null, "Gena", null));

        order.setUserId(secondUser);
        order.setNameOrder(newOrderName);

        new Update().updateOrder(order);

        Order resultOrder = new Read().getOrderByName(order.getNameOrder());

        Assertions.assertNotNull(resultOrder);
        Assertions.assertEquals(secondUser.getIdUser(), resultOrder.getUserId().getIdUser());
        Assertions.assertEquals(newOrderName, resultOrder.getNameOrder());
    }
}