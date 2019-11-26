package by.andersenlab.hibernate.crud;

import by.andersenlab.config.ConfigSpring;
import by.andersenlab.travelagency.model.Order;
import by.andersenlab.travelagency.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Optional;

class ReadTest {
    private Read read;
    private Create create;

    private static String nameUser = "Sergey";

    @BeforeEach
    private void setUp() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigSpring.class);
        read = context.getBean(Read.class);
        create = context.getBean(Create.class);

        User user = new User(null, nameUser, null);
        create.insertNewUser(user);
    }

    @Test
    void getAllUsers() {
        int oldCountUsers = read.getAllUsers().size();
        create.insertNewUser(new User(null, nameUser + 1, null));
        List<User> users = read.getAllUsers();
        Assertions.assertEquals(oldCountUsers + 1, users.size());
    }

    @Test
    void getAllUsersWithOrderDependency() {
        int oldCountUsers = read.getAllUsers().size();
        int oldCountOrdersForUser = read.getUser(nameUser).getOrders().size();

        String nameOrder = "Test order for dependency";

        User newUser = read.getUser(nameUser);
        create.insertNewOrder(new Order(null, nameOrder, newUser, 30D, null));

        create.insertNewUser(new User(null, nameUser + 1, null));

        List<User> users = read.getAllUsers();

        User userWithDepend = read.getUser(nameUser);
        Optional<Order> order = userWithDepend.getOrders().stream()
                .filter((n) -> (nameOrder.equals(n.getNameOrder())))
                .findFirst();

        Assertions.assertEquals(oldCountUsers + 1, users.size());
        Assertions.assertEquals(oldCountOrdersForUser + 1, userWithDepend.getOrders().size());
        Assertions.assertEquals(nameOrder, order.orElseGet(Order::new).getNameOrder());
    }

    @Test
    void getUserByName() {
        User resultUser = read.getUser(nameUser);

        Assertions.assertEquals(resultUser.getNameUser(), nameUser);
    }


}