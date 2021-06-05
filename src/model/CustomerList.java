/**
 * Heaven-leigh Michelle Masters
 * C195 Software II Advanced Java Concepts
 * QAM1 Task 1: Java Application Development
 * singleton class for the list of customers
 */
package model;

import databaseAccess.DbCustomers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * singleton class for creating, manipulating, and providing the customer list
 */
public class CustomerList {

    private static ObservableList<Customer> allCustomers;

    private static CustomerList customerListInstance = null;

    /**
     * constructor for list of customers 'allCustomers'
     */
    private CustomerList() {

        allCustomers = FXCollections.observableArrayList();
    }

    /**
     * creates new CustomerList if not already made, otherwise returns instance of it
     * @return instance of CustomerList
     */
    public static CustomerList getInstance() {

        if (customerListInstance == null) {

            customerListInstance = new CustomerList();
        }
        return customerListInstance;
    }

    /**
     * creates singular instance of customer list and uses database access customers class to get all customer from database
     * @throws Exception for any errors
     */
    public static void getAllCustomersFromDB() throws Exception {

        new CustomerList();
        allCustomers.addAll(DbCustomers.getCustomersFromDB());
    }

    /**
     * provides customer list to caller
     * @return list of all customers
     */
    public static ObservableList<Customer> provideCustomerList() {

        return allCustomers;
    }

    /**
     * adds customer to customer list
     * @param customer passed to add to list
     */
    public static void addToCustomerList(Customer customer) {

        allCustomers.add(customer);
    }

    /**
     * locates and removes customer with matching customer ID and then adds updated customer record
     * @param updatedCustomer customer passed from the save customer function
     */
    public static void updateCustomer(Customer updatedCustomer) {

        for (Customer customer: allCustomers) {
            if (customer.getCustomerID() == updatedCustomer.getCustomerID()) {

                allCustomers.remove(customer);
                allCustomers.add(updatedCustomer);

                break;
            }
        }
    }

    /**
     * deletes an appointment from the appointment calendar
     * @param customer is the customer passed to delete from database and customer list
     * @return true if appointment is deleted and false for exceptions
     */
    public static boolean deleteCustomer(Customer customer) {

        try {
            DbCustomers.removeCustomer(customer);
            allCustomers.remove(customer);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * finds and returns a customer
     * @param customerID
     * @return
     */
    public static Customer getCustomerByID(int customerID) {
        Customer foundCustomer = null;
        for (Customer customer: allCustomers) {
            if (customer.getCustomerID() == customerID) {
                foundCustomer = customer;
            }
        }
        return foundCustomer;
    }
}
