package by.andersenlab.travelagency.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private Integer idUser;
    private String nameUser;
    private Set<Order> orders = new HashSet<>();
}
