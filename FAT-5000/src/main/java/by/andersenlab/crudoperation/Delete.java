package by.andersenlab.crudoperation;

import by.andersenlab.travelagency.model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {
    //language=SQL
    private final String deleteCountry = "DELETE FROM TEST.TRAVELAGENCY.COUNTRY WHERE ID = ?";

    public int deleteField(Country obClass) {
        try (Connection con = Connect.INSTANCE.getConnection();
             PreparedStatement statement = con.prepareStatement(deleteCountry)) {

            statement.setInt(1, obClass.getId());

            return statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
