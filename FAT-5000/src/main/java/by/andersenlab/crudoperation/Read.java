package by.andersenlab.crudoperation;


import by.andersenlab.travelagency.model.Country;
import by.andersenlab.travelagency.model.Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Read {

    //language=SQL
    private final String readCountry = "SELECT * FROM TEST.TRAVELAGENCY.COUNTRY";
    //language=SQL
    private final String getByIdCountry = "SELECT * FROM TEST.TRAVELAGENCY.COUNTRY WHERE TEST.TRAVELAGENCY.COUNTRY.NAMECOUNTRY=?";

    //language=SQL
    private final String readHotel = "SELECT * FROM TEST.TRAVELAGENCY.HOTEL";
    //language=SQL
    private final String readOrder = "SELECT * FROM TEST.TRAVELAGENCY.ORDER";
    //language=SQL
    private final String readReview = "SELECT * FROM TRAVELAGENCY.REVIEW";
    //language=SQL
    private final String readTour = "SELECT * FROM TRAVELAGENCY.TOUR";
    //language=SQL
    private final String readUser = "SELECT * FROM TRAVELAGENCY.USER";


    public List<Country> getAll(Country obClass) {
        List<Country> result = new ArrayList<>();
        try (Connection con = Connect.INSTANCE.getConnection();
             PreparedStatement statement = con.prepareStatement(readCountry);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                result.add(new Country(resultSet.getInt("id"),
                        resultSet.getString("namecountry")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Hotel> getAll(Hotel obClass) {
        List<Hotel> result = new ArrayList<>();
        try (Connection con = Connect.INSTANCE.getConnection();
             PreparedStatement statement = con.prepareStatement(readHotel);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                result.add(new Hotel(resultSet.getInt("id"),
                        resultSet.getString("namehotel"),
                        resultSet.getInt("ratingstars")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int getIdByName(Country ob) {
        try (Connection con = Connect.INSTANCE.getConnection();
             PreparedStatement statement = con.prepareStatement(getByIdCountry)) {

            statement.setString(1, ob.getNameCountry());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt("id");
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return -1;
    }

}
