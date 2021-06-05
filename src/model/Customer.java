/**
 * Heaven-leigh Michelle Masters
 * C195 Software II Advanced Java Concepts
 * QAM1 Task 1: Java Application Development
 * object class for customers
 */
package model;

/**
 * creates Customer objects and the getters, setters, and formatting operations
 */
public class Customer {

    private int customerID;
    private String name, phoneNum, address, postalCode;
    private Country country;
    private Division division;

    /**
     * constructor for creating customer objects
     * @param customerID ID of the customer
     * @param name name of the customer
     * @param phoneNum phone number of the customer
     * @param address address of the customer
     * @param postalCode postal code of the customer
     * @param country country of the customer
     * @param division division of the customer
     */
    public Customer(int customerID, String name, String phoneNum, String address, String postalCode, Country country,
                    Division division) {

        this.customerID = customerID;
        this.name = name;
        this.phoneNum = phoneNum;
        this.address = address;
        this.postalCode = postalCode;
        this.country = country;
        this.division = division;
    }

    /**
     * over-loaded customer constructor for creating simpler customer objects
     * @param customerID ID of the customer
     * @param customerName name of the customer
     */
    public Customer(int customerID, String customerName) {

        this.customerID = customerID;
        this.name = customerName;
    }

    /**
     * gets the ID of a customer
     * @return ID of customer as integer
     */
    public int getCustomerID() { return customerID;}

    /**
     * sets the ID of a customer
     * @param customerID ID to set for the customer
     */
    public void setCustomerID(int customerID) { this.customerID = customerID; }

    /**
     * gets the name of a customer
     * @return name of customer as a String
     */
    public String getName() { return name;}

    /**
     * sets the name of a customer
     * @param name of customer to set
     */
    public void setName(String name) { this.name = name;}

    /**
     * gets the phone number of a customer
     * @return phone number of customer as a String
     */
    public String getPhoneNum() { return phoneNum;}

    /**
     * sets the phone number of a customer
     * @param phoneNum phone number of customer to set
     */
    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum;}

    /**
     * gets the address of a customer
     * @return address of customer as a String
     */
    public String getAddress() { return address;}

    /**
     * sets the address of a customer
     * @param address of customer to set
     */
    public void setAddress(String address) { this.address = address; }

    /**
     * gets the postal code of a customer
     * @return postal code of customer as a String
     */
    public String getPostalCode() { return postalCode; }

    /**
     * sets the postal code of a customer
     * @param postalCode postal code of customer to set
     */
    public void setPostalCode(String postalCode) { this.postalCode = postalCode;}

    /**
     * gets the country for a customer
     * @return Country object for the customer
     */
    public Country getCountry() { return country;}

    /**
     * sets the country for a customer
     * @param country for the customer to set
     */
    public void setCountry(Country country) { this.country = country; }

    /**
     * gets the division of a customer
     * @return division for the customer
     */
    public Division getDivision() { return division; }

    /**
     * sets the division for a customer
     * @param division to set the customer
     */
    public void setDivision(Division division) { this.division = division;}

    /**
     * overrides the default to string method to represent customers as their ID and name
     * @return the String with customerID and name formatted
     */
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
}
