package by.andersenlab.travelagency.model;

import lombok.Data;

@Data
public class Review {
    private Integer id;
    private String textReview;
    private Integer idHotel;
}
