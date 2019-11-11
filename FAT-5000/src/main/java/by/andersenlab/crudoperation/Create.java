package by.andersenlab.crudoperation;

import by.andersenlab.travelagency.model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Create {
    //language=SQL
    private final String countryInsert = "INSERT INTO TEST.TRAVELAGENCY.COUNTRY (NAMECOUNTRY) VALUES ( ? )";

    public int insertField(Country obClass) {
        if (obClass.getId() != null) {
            return 0;
        }

        try (Connection con = Connect.INSTANCE.getConnection();
             PreparedStatement statement = con.prepareStatement(countryInsert)) {

            statement.setString(1, obClass.getNameCountry());
            return statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
