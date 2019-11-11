package by.andersenlab.crudoperation;

import by.andersenlab.travelagency.model.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteTest {

    private Country country;

    @BeforeEach
    public void setUp() {
        country = new Country(null, "Guadalupe");
        new Create().insertField(country);
        country.setId(new Read().getIdByName(country));
    }

    @Test
    void deleteField() {
        int i = new Delete().deleteField(country);
        Assertions.assertTrue(1 >= i);
    }
}