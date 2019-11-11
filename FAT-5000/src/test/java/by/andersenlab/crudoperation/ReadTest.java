package by.andersenlab.crudoperation;

import by.andersenlab.travelagency.model.Country;
import by.andersenlab.travelagency.model.Hotel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class ReadTest {

    @Test
    void getAllCountries() {
        List<Country> listCountry = new Read().getAll(new Country());
        Assertions.assertNotNull(listCountry);
        Assertions.assertTrue(listCountry.size() > 0);
    }

    @Test
    void getAllHotels() {
        List<Hotel> listCountry = new Read().getAll(new Hotel());
        Assertions.assertNotNull(listCountry);
        Assertions.assertTrue(listCountry.size() > 0);
    }


    @Test
    void getIdByNameCountry() {
        Assertions.assertEquals(1, new Read().getIdByName(new Country(null, "Belarus")));
    }
}