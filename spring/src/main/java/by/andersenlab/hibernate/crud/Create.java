package by.andersenlab.hibernate.crud;

import by.andersenlab.travelagency.model.Order;
import by.andersenlab.travelagency.model.User;


public interface Create {
    User insertNewUser(User user);

    Order insertNewOrder(Order order);
}
