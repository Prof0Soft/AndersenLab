package by.andersenlab.hibernate.crudoperation;

import by.andersenlab.travelagency.model.Order;
import by.andersenlab.travelagency.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class ReadTest {
    private static String nameUser = "Sergey";

    @BeforeAll
    private static void setUp() {
        User user = new User(null, nameUser, null);
        new Create().insertNewUser(user);
    }

    @Test
    void getAllUsers() {
        new Create().insertNewUser(new User(null, nameUser + 1, null));
        List<User> users = new Read().getAllUsers();
        Assertions.assertEquals(2, users.size());
    }

    @Test
    void getAllUsersWithOrderDependency() {
        String nameOrder = "Test order";
        User newUser = new Read().getUserByName(nameUser);
        new Create().insertNewOrder(new Order(null, nameOrder, newUser, 30D, null));

        new Create().insertNewUser(new User(null, nameUser + 1, null));
        List<User> users = new Read().getAllUsers();

        User userWithDepend = new Read().getUserByName(nameUser);
        Optional<Order> order = userWithDepend.getOrders().stream()
                .findFirst();

        Assertions.assertEquals(2, users.size());
        Assertions.assertEquals(1, userWithDepend.getOrders().size());
        Assertions.assertEquals(nameOrder, order.orElseGet(Order::new).getNameOrder());
    }

    @Test
    void getUserByName() {
        User resultUser = new Read().getUserByName(nameUser);

        Assertions.assertEquals(resultUser.getNameUser(), nameUser);
    }


}