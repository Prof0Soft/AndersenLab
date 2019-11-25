package by.andersenlab.hibernate.crud;

import by.andersenlab.travelagency.model.Order;
import by.andersenlab.travelagency.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        Order orderInBase = new Read().getOrder(order.getId());

        new Delete().deleteOrder(orderInBase);

        Order resultOrder = new Read().getOrder(orderInBase.getId());

        Assertions.assertNull(resultOrder);
    }

    @Test
    void deleteUser() {
        User userInBase = new Read().getUser(user.getNameUser());

        new Delete().deleteUser(userInBase);

        User resultUser = new Read().getUser(userInBase.getIdUser());

        Assertions.assertNull(resultUser);
    }

    @Test
    void deleteUserCheckCascadeDelete() {
        User userInBase = new Read().getUser(user.getNameUser());

        int idUser = userInBase.getIdUser();
        int idOrder = userInBase.getOrders().stream()
                .findFirst().orElseGet(Order::new).getId();

        new Delete().deleteUser(userInBase);

        User resultUser = new Read().getUser(idUser);
        Order resultOrder = new Read().getOrder(idOrder);

        Assertions.assertNull(resultUser);
        Assertions.assertNull(resultOrder);
    }
}