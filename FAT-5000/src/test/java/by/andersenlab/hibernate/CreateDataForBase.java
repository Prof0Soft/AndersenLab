package by.andersenlab.hibernate;


import by.andersenlab.hibernate.crud.Create;
import by.andersenlab.hibernate.crud.Read;
import by.andersenlab.travelagency.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateDataForBase {

    @Test
    public void createAllData() {

        //create country
        Country country1 = new Create<Country>().insertNewObject(new Country(null, "Belarus", null));
        Country country2 = new Create<Country>().insertNewObject(new Country(null, "Russia", null));
        Country country3 = new Create<Country>().insertNewObject(new Country(null, "Spain", null));

        int countCountry = new Read<>(Country.class).getAll().size();
        int countCountryExpect = 3;

        // create hotels
        Hotel hotel1 = new Create<Hotel>().insertNewObject(new Hotel(null, "Cool Hotel", 5, null, null));
        Hotel hotel2 = new Create<Hotel>().insertNewObject(new Hotel(null, "Middle Hotel", 3, null, null));
        Hotel hotel3 = new Create<Hotel>().insertNewObject(new Hotel(null, "Bad Hotel", 1, null, null));

        int countHotel = new Read<>(Hotel.class).getAll().size();
        int countHotelExpect = 3;

        // create reviews
        new Create<Review>().insertNewObject(new Review(null, "Wounderfull hotell, nice food.", hotel1));
        new Create<Review>().insertNewObject(new Review(null, "I spend all time in this hotel. Nice. I'll back.", hotel1));
        new Create<Review>().insertNewObject(new Review(null, "Miodle hotel. Not bad but not very well", hotel2));
        new Create<Review>().insertNewObject(new Review(null, "Very bad hotel.", hotel3));
        new Create<Review>().insertNewObject(new Review(null, "Not back also. Bad bad.", hotel3));
        new Create<Review>().insertNewObject(new Review(null, "Bad", hotel3));

        int countReview = new Read<>(Review.class).getAll().size();
        int countReviewExpect = 6;

        // create tour
        Tour tour1 = new Create<Tour>().insertNewObject(new Tour(null, "Hot Spain tour", country3, hotel2, null));
        Tour tour2 = new Create<Tour>().insertNewObject(new Tour(null, "Spain tour", country3, hotel3, null));
        Tour tour3 = new Create<Tour>().insertNewObject(new Tour(null, "Belorussian's forests", country1, hotel1, null));
        Tour tour4 = new Create<Tour>().insertNewObject(new Tour(null, "Sochi tour", country2, hotel3, null));
        Tour tour5 = new Create<Tour>().insertNewObject(new Tour(null, "Near Sochi tour", country3, hotel2, null));

        int countTour = new Read<>(Tour.class).getAll().size();
        int countTourExpect = 5;

        // create Users
        User user1 = new Create<User>().insertNewObject(new User(null, "Egor", null));
        User user2 = new Create<User>().insertNewObject(new User(null, "Bob", null));
        User user3 = new Create<User>().insertNewObject(new User(null, "Jon", null));
        User user4 = new Create<User>().insertNewObject(new User(null, "Crag", null));

        int countUser = new Read<>(User.class).getAll().size();
        int countUserExpect = 4;

        //create orders
        Order order1 = new Create<Order>().insertNewObject(new Order(null, "Cool", user1, 100D, tour1));
        Order order2 = new Create<Order>().insertNewObject(new Order(null, "Cool", user1, 100D, tour2));
        Order order3 = new Create<Order>().insertNewObject(new Order(null, "Cool", user2, 100D, tour3));
        Order order4 = new Create<Order>().insertNewObject(new Order(null, "Cool", user3, 100D, tour4));
        Order order5 = new Create<Order>().insertNewObject(new Order(null, "Cool", user4, 100D, tour5));

        int countOrder = new Read<>(Order.class).getAll().size();
        int countOrderExpect = 5;

        Assertions.assertEquals(countCountryExpect, countCountry);
        Assertions.assertEquals(countHotelExpect, countHotel);
        Assertions.assertEquals(countOrderExpect, countOrder);
        Assertions.assertEquals(countReviewExpect, countReview);
        Assertions.assertEquals(countTourExpect, countTour);
        Assertions.assertEquals(countUserExpect, countUser);
    }
}
