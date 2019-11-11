package by.andersenlab.crudoperation;

import by.andersenlab.travelagency.model.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateTest {

    @Test
    void insertFieldCountry() {
        Country country = new Country(null, "Guadalupe");

        int i = new Create().insertField(country);

        Assertions.assertEquals(1, i);
    }
}