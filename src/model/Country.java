package model;

import databaseAccess.DbLocations;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class Country {

    private int countryID;
    private String countryName;
    private static ObservableList<Country> allCountries = FXCollections.observableArrayList();

    public Country(int countryID, String countryName) {
        this.countryID = countryID;
        this.countryName = countryName;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountry(String country) {
        this.countryName = country;
    }

    @Override
    public String toString() {
        return "[" + countryID + "]" + " " + countryName;
    }

    public static void getAllCountriesFromDB() throws SQLException {

        allCountries = DbLocations.getAllCountriesFromDB();
    }

    public static ObservableList<Country> provideCountryList() {

        return allCountries;
    }
}
