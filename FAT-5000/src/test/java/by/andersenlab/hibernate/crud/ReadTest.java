package by.andersenlab.hibernate.crud;

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
        int oldCountUsers = new Read(User.class).getAll().size();
        new Create().insertNewUser(new User(null, nameUser + 1, null));
        List<User> users = new Read(User.class).getAll();
        Assertions.assertEquals(oldCountUsers + 1, users.size());
    }

    @Test
    void getAllUsersWithOrderDependency() {
        int oldCountUsers = new Read(User.class).getAll().size();
        int oldCountOrdersForUser = new Read().getUser(nameUser).getOrders().size();

        String nameOrder = "Test order for dependency";

        User newUser = new Read().getUser(nameUser);
        new Create().insertNewOrder(new Order(null, nameOrder, newUser, 30D, null));

        new Create().insertNewUser(new User(null, nameUser + 1, null));

        List<User> users = new Read(User.class).getAll();

        User userWithDepend = new Read().getUser(nameUser);
        Optional<Order> order = userWithDepend.getOrders().stream()
                .filter((n) -> (nameOrder.equals(n.getNameOrder())))
                .findFirst();

        Assertions.assertEquals(oldCountUsers + 1, users.size());
        Assertions.assertEquals(oldCountOrdersForUser + 1, userWithDepend.getOrders().size());
        Assertions.assertEquals(nameOrder, order.orElseGet(Order::new).getNameOrder());
    }

    @Test
    void getUserByName() {
        User resultUser = new Read().getUser(nameUser);

        Assertions.assertEquals(resultUser.getNameUser(), nameUser);
    }


}