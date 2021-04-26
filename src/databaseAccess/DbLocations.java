package databaseAccess;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import model.Customer;
import model.Division;
import utilities.DbConnection;
import utilities.DbQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DbLocations {

    private static ObservableList<Country> allCountries = FXCollections.observableArrayList();
    private static ObservableList<Division> selectedCountryDivisions = FXCollections.observableArrayList();

    private static int countryID = 0;
    private static String countryName = null;
    private static int divisionID = 0;
    private static String divisionName = null;

    public static ObservableList<Country> getAllCountries() throws SQLException {

        DbConnection.getConnection();

        String sql = "SELECT Country_ID, Country FROM countries";

        DbQuery.createQuery(sql);

        ResultSet resultSet = DbQuery.getResultSet();

            while (resultSet.next()) {

                countryID = resultSet.getInt("Country_ID");
                countryName = resultSet.getString("Country");

                Country country = new Country(countryID, countryName);

                System.out.println("Created new customer");

                allCountries.add(country);
            }

            return allCountries;
        }

    public static ObservableList<Division> getDivisions(Country selectedCountry) throws SQLException {

        Connection connxn = DbConnection.getConnection();
        String sql = "SELECT Division_ID, Division FROM first_level_divisions WHERE COUNTRY_ID = ?";

        countryID = selectedCountry.getCountryID();
        DbQuery.setPreparedStatement(connxn, sql);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();
        preparedStatement.setInt(1, countryID);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {

            divisionID = resultSet.getInt("Division_ID");
            divisionName = resultSet.getString("Division");

            Division division = new Division(divisionID, divisionName);

            selectedCountryDivisions.add(division);
        }

        return selectedCountryDivisions;
    }

    public static Country getCountryByDivisionID(int divisionID) throws SQLException {

        Connection connxn = DbConnection.getConnection();

        String sql = "SELECT COUNTRY_ID FROM first_level_divisions WHERE Division_ID = ?";

        DbQuery.setPreparedStatement(connxn, sql);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();
        preparedStatement.setInt(1, divisionID);

        ResultSet resultSet = preparedStatement.executeQuery();

        int countryID = 0;

        while(resultSet.next()){
            countryID = resultSet.getInt("Country_ID");
        }

        sql = "SELECT Country FROM countries WHERE Country_ID = ?";
        DbQuery.setPreparedStatement(connxn, sql);
        preparedStatement = DbQuery.getPreparedStatement();
        preparedStatement.setInt(1, countryID);

        resultSet = preparedStatement.executeQuery();

        String countryName = null;

        while(resultSet.next()){
            countryName = resultSet.getString("Country");
        }

        Country country = new Country(countryID, countryName);

        return country;
    }

    /**
     * gets the division name of a division ID from the database.
     * @param divisionID
     * @return division (division name)
     * @throws SQLException
     */
    public static Division getDivision(int divisionID) throws SQLException {

        Connection connxn = DbConnection.getConnection();

        String sql = "SELECT Division FROM first_level_divisions WHERE Division_ID = ?";

        DbQuery.setPreparedStatement(connxn, sql);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();
        preparedStatement.setInt(1, divisionID);

        ResultSet resultSet = preparedStatement.executeQuery();

        String divisionName = null;

        while(resultSet.next()){
            divisionName = resultSet.getString("Division");
        }

        Division division = new Division(divisionID, divisionName);

        return division;
    }
    }

