package by.andersenlab.hibernate.crud;

import by.andersenlab.travelagency.model.Order;
import by.andersenlab.travelagency.model.User;

public interface Delete {
    void deleteOrder(Order orderInBase);

    void deleteUser(User userInBase);
}
