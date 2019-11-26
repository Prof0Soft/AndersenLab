package by.andersenlab.hibernate.crud;

import by.andersenlab.travelagency.model.Order;
import by.andersenlab.travelagency.model.User;

public interface Update {
    boolean updateUser(User user);

    boolean updateOrder(Order order);
}
