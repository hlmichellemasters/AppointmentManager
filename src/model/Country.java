/**
 * Heaven-leigh Michelle Masters
 * C195 Software II Advanced Java Concepts
 * QAM1 Task 1: Java Application Development
 * object class for countries
 */
package model;

import databaseAccess.DbLocations;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

/**
 * creates Country objects, formatting operations, with minimal getters or setters as application does not edit these
 */
public class Country {

    private int countryID;
    private String countryName;
    private static ObservableList<Country> allCountries = FXCollections.observableArrayList();

    /**
     * constructor for creating country objects
     * @param countryID ID for the country
     * @param countryName name of the country
     */
    public Country(int countryID, String countryName) {
        this.countryID = countryID;
        this.countryName = countryName;
    }

    /**
     * gets the ID of a country
     * @return ID of the country as an integer
     */
    public int getCountryID() { return countryID; }

    /**
     * overrides the default toString method to instead display the country ID and the country name
     * @return a string that displays the object in a user-friendly representation.
     */
    @Override
    public String toString() {
        return "[" + countryID + "]" + " " + countryName;
    }

    /**
     * calls the database locations class to get a list of all the countries from the database
     * @throws SQLException for any SQL error
     */
    public static void getAllCountriesFromDB() throws SQLException {

        allCountries = DbLocations.getAllCountriesFromDB();
    }

    /**
     * provides the list of the countries
     * @return the list of the countries
     */
    public static ObservableList<Country> provideCountryList() {

        return allCountries;
    }
}
