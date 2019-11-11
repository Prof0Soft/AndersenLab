package by.andersenlab.travelagency.model;

import lombok.Data;

@Data
public class Tour {
    private Integer id;
    private String nameTour;
    private Integer idCountry;
    private Integer idHotel;
}
