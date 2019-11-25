package by.andersenlab.hibernate.crud;

import by.andersenlab.travelagency.model.Order;
import by.andersenlab.travelagency.model.User;

import java.util.List;

public interface Read {
    List<User> getAllUsers();

    User getUser(String nameUser);
    User getUser(int nameUser);
    Order getOrder(int id);
    Order getOrder(String id);
}
