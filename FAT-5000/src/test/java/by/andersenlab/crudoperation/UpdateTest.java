package by.andersenlab.crudoperation;

import by.andersenlab.travelagency.model.Country;
import by.andersenlab.travelagency.model.Hotel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class UpdateTest {

    @AfterEach
    public void setDown() {
        Country country = new Country(1, "Belarus");
        int result = new Update().updateField(country);
    }

    @Test
    void updateHotel() {
        Country country = new Country(1, "Updating field");
        int result = new Update().updateField(country);

        Assertions.assertEquals(1, result);

    }

}