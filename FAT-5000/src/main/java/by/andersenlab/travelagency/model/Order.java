package by.andersenlab.travelagency.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    private Integer id;
    private String nameOrder;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User userId;
    private Double price;
    private Integer idTour;
}
