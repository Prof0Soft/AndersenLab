package by.andersenlab.travelagency.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Review {
    private Integer id;
    private String textReview;
    private Integer idHotel;
}
