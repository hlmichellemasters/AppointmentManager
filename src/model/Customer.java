package model;

public class Customer {

    private int customerID;
    private String name, phoneNum, address, postalCode;
    private Country country;
    private Division division;

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
