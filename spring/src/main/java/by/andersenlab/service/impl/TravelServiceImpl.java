package by.andersenlab.service.impl;

import by.andersenlab.hibernate.crud.Read;
import by.andersenlab.service.TravelService;
import by.andersenlab.service.UserService;
import by.andersenlab.travelagency.model.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Data
@Component
public class TravelServiceImpl implements TravelService {

    @Autowired
    private Read read;

    @Autowired
    private UserService userService;

    public User findUserWithMinOrderPrice() {
        List<User> users = read.getAllUsers();
        return users.stream()
                .min(Comparator.comparingDouble((n) -> (userService.getSumCoastOrderByUser(n)))).orElse(null);
    }
}
