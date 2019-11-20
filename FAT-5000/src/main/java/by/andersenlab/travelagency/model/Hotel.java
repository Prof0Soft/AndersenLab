package by.andersenlab.travelagency.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDHOTEL")
    private Integer id;

    @Column(name = "NAMEHOTEL")
    private String nameHotel;

    @Column(name = "RATINGSTARS")
    private Integer ratingStars;

    @OneToMany(mappedBy = "idHotelReview", fetch = FetchType.EAGER)
    private Set<Review> reviews = new HashSet<>();

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "idHotel")
    private Tour tourId;
}
