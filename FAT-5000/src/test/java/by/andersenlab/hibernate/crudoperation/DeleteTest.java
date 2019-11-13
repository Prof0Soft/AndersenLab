package by.andersenlab.hibernate.crudoperation;

import by.andersenlab.travelagency.model.Order;
import by.andersenlab.travelagency.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteTest {

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
    void deleteOrder() {
        Order orderInBase = new Read().getOrderByName(order.getNameOrder());

        new Delete().deleteOrder(orderInBase);

        Order resultOrder = new Read().getOrderByName(orderInBase.getNameOrder());

        Assertions.assertNull(resultOrder);
    }

    @Test
    void deleteUser() {
        User userInBase = new Read().getUserByName(user.getNameUser());

        new Delete().deleteUser(userInBase);

        User resultUser = new Read().getUserByName(userInBase.getNameUser());

        Assertions.assertNull(resultUser);
    }

    @Test
    void deleteUserCheckCascadeDelete() {
        User userInBase = new Read().getUserByName(user.getNameUser());

        new Delete().deleteUser(userInBase);

        User resultUser = new Read().getUserByName(userInBase.getNameUser());
        Order resultOrder = new Read().getOrderByName(order.getNameOrder());

        Assertions.assertNull(resultUser);
        Assertions.assertNull(resultOrder);
    }
}