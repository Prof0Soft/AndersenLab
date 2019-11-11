package by.andersenlab.crudoperation;

import by.andersenlab.travelagency.model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update {
    //language=SQL
    private final String updateCountry = "UPDATE TEST.TRAVELAGENCY.COUNTRY SET NAMECOUNTRY=? WHERE ID = ?";

    public int updateField(Country obClass) {
        try (Connection con = Connect.INSTANCE.getConnection();
             PreparedStatement statement = con.prepareStatement(updateCountry)) {

            statement.setString(1, obClass.getNameCountry());
            statement.setInt(2, obClass.getId());

            return statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
