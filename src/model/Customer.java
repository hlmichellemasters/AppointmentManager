package model;

import databaseAccess.DbCustomers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class Customer {

    private int customerID;
    private String name, phoneNum, address, postalCode;
    private Country country;
    private Division division;
    private static ObservableList<Customer> customerList = FXCollections.observableArrayList();

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

    /**
     * overrides the equals method for Customer and calls two objects equal if the customer name is the same
     * adapted from https://www.infoworld.com/article/3305792/comparing-java-objects-with-equals-and-hashcode.html
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Customer customer = (Customer) object;

        return name.equals(customer.name);

    }

    public static ObservableList<Customer> provideCustomerList() throws Exception {

        return customerList;
    }

    public static void addToCustomerList(Customer customer) throws Exception {

        customerList.add(customer);
    }

    /**
     * deletes an appointment from the appointment calendar
     * @param customer
     * @return true if appointment is deleted and false for exceptions
     */
    public static boolean deleteCustomer(Customer customer) {

        try {
            DbCustomers.removeCustomer(customer);
            customerList.remove(customer);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void getAllCustomersFromDB() throws Exception {

        customerList.addAll(DbCustomers.getCustomersFromDB());
    }

    public static Customer getCustomerByID(int customerID) {
        Customer foundCustomer = null;
        for (Customer customer: customerList) {
            if (customer.customerID == customerID) {
                foundCustomer = customer;
            }
        }
        return foundCustomer;
    }
}
