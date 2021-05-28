package model;

import databaseAccess.DbCustomers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerList {

    public static ObservableList<Customer> customerList = FXCollections.observableArrayList();

    /**
     * provides customer list to caller
     * @return list of all customers
     */
    public static ObservableList<Customer> provideCustomerList() {

        return customerList;
    }

    /**
     * adds customer to customer list
     * @param customer passed to add to list
     */
    public static void addToCustomerList(Customer customer) {

        customerList.add(customer);
    }

    /**
     * locates and removes customer with matching customer ID and then adds updated customer record
     * @param updatedCustomer customer passed from the save customer function
     */
    public static void updateCustomer(Customer updatedCustomer) {

        for (Customer customer: customerList) {
            if (customer.getCustomerID() == updatedCustomer.getCustomerID()) {
                customerList.remove(customer);
                System.out.println("Removed customer " + customer);
                break;
            }
        }

        customerList.add(updatedCustomer);

        System.out.println("added customer " + updatedCustomer);
        System.out.println("customer list updated");

    }


    /**
     * deletes an appointment from the appointment calendar
     * @param customer is the customer passed to delete from database and customer list
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

    /**
     * adds all customers from the database into the customer list
     * @throws Exception for any error
     */
    public static void getAllCustomersFromDB() throws Exception {

        customerList.addAll(DbCustomers.getCustomersFromDB());
    }

    /**
     * finds and returns a customer
     * @param customerID
     * @return
     */
    public static Customer getCustomerByID(int customerID) {
        Customer foundCustomer = null;
        for (Customer customer: customerList) {
            if (customer.getCustomerID() == customerID) {
                foundCustomer = customer;
            }
        }
        return foundCustomer;
    }
}
