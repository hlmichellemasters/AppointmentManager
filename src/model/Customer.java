package model;

import databaseAccess.DbCustomers;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class Customer {

    private int customerID;
    private String name, phoneNum, address, postalCode;
    Country country;
    Division division;

    public Customer(int customerID, String name, String phoneNum, String address, String postalCode, Country country,
                    Division division) {

        this.customerID = customerID;
        this.name = name;
        this.phoneNum = phoneNum;
        this.address = address;
        this.postalCode = postalCode;
        this.country = country;
        this.division = division;

        System.out.println("Created a customer");
    }

    public Customer(int customerID, String customerName) {
        this.customerID = customerID;
        this.name = customerName;
    }

    public int getCustomerID() { return customerID;}

    public void setCustomerID(int customerID) { this.customerID = customerID;}

    public String getName() { return name;}

    public void setName(String name) { this.name = name;}

    public String getPhoneNum() { return phoneNum;}

    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum;}

    public String getAddress() { return address;}

    public void setAddress(String address) { this.address = address; }

    public String getPostalCode() { return postalCode; }

    public void setPostalCode(String postalCode) { this.postalCode = postalCode;}

    public Country getCountry() { return country;}

    public void setCountry(Country country) { this.country = country; }

    public Division getDivision() { return division; }

    public void setDivision(Division division) { this.division = division;}

    @Override
    public String toString(){

        return "[" + customerID + "] " + name;
    }

    public static ObservableList<Customer> getAllCustomers() throws Exception {

        return DbCustomers.getCustomersFromDB();
    }
}
