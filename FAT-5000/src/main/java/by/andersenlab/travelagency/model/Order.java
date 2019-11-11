package by.andersenlab.travelagency.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    private Integer id;
    private String nameOrder;
    private Double price;
    private Integer idTour;
    private Integer idUser;
}
