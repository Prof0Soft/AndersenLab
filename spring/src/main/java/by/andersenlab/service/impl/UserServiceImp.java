package by.andersenlab.service.impl;

import by.andersenlab.service.UserService;
import by.andersenlab.travelagency.model.Order;
import by.andersenlab.travelagency.model.User;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserServiceImp implements UserService {

    public Double getSumCoastOrderByUser(User user) {
        Double priceSum = 0D;
        Set<Order> orders = user.getOrders();
        if (orders == null) {
            return 0D;
        }
        for (Order order : orders) {
            priceSum += order.getPrice();
        }
        return priceSum;
    }

}
