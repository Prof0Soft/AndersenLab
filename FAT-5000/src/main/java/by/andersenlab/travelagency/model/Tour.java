package by.andersenlab.travelagency.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Tour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDTOUR")
    private Integer id;

    @Column(name = "NAMETOUR")
    private String nameTour;

    @OneToOne
    @JoinColumn(name = "COUNTRYID")
    private Country idCountryTour;

    @OneToOne
    @JoinColumn(name = "HOTELID")
    private Hotel idHotel;

    @ManyToOne
    @JoinColumn(name = "IDORDER")
    private Order order;
}
