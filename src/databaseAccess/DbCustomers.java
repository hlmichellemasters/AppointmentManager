package databaseAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;
import utilities.DbConnection;
import utilities.DbQuery;

import java.sql.*;
import java.time.*;

public class DbCustomers {

    /**
     * gets all the data from all customers in the database and creates Customers.
     *
     * @return allCustomers (Observablelist of all customers in database)
     * @throws Exception
     */
    public static ObservableList<Customer> getCustomersFromDB() throws Exception {

        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

        DbConnection.getConnection();

        String sql = "SELECT * FROM customers";

        DbQuery.createQuery(sql);

        ResultSet resultSet = DbQuery.getResultSet();

            while(resultSet.next()) {

                int customerID = resultSet.getInt("Customer_ID");
                String name = resultSet.getString("Customer_Name");
                String address = resultSet.getString("Address");
                String postalCode = resultSet.getString("Postal_Code");
                String phoneNum = resultSet.getString("Phone");
                int divisionID = resultSet.getInt("Division_ID");

                Division division = DbLocations.getDivision(divisionID);
                Country country = DbLocations.getCountryByDivisionID(divisionID);

                Customer customer = new Customer(customerID, name, phoneNum, address, postalCode, country, division);

                allCustomers.add(customer);
            }

            return allCustomers;
    }

    public static int getNextCustomerID() {

        int nextCustomerID = 0;

        try {
            Connection connxn = DbConnection.getConnection();

            String sql =  "SELECT MAX(Country_ID) + 1 FROM countries";

            DbQuery.setPreparedStatement(connxn, sql);
            PreparedStatement preparedStatement = DbQuery.getPreparedStatement();

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                nextCustomerID = resultSet.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return nextCustomerID;
    }

    public static void saveCustomertoDB(int customerID, String customerName, String customerPhoneNum, String customerAddress,
                                   String customerPostal, int customerDivisionID) {

        if (customerID == getNextCustomerID()) {

            String createdBy, lastUpdatedBy;

            createdBy = lastUpdatedBy = User.userLoggedIn.getUserName();

            Timestamp createDate = Timestamp.from(Instant.now());  // using Instant to create UTC DateTime object
                                                                   // then make into Timestamp so MySQL can make DateTime
            System.out.println("The current time at UTC is ");
            Timestamp lastUpdateTS = Timestamp.valueOf(LocalDateTime.now());

            try {
                Connection connxn = DbConnection.getConnection();

                String sql = "INSERT INTO " +
                        "customers(Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, " +
                        "Last_Updated_By, Division_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                DbQuery.setPreparedStatement(connxn, sql);

                PreparedStatement preparedStatement = DbQuery.getPreparedStatement();

                preparedStatement.setString(1, customerName);
                preparedStatement.setString(2, customerAddress);
                preparedStatement.setString(3, customerPostal);
                preparedStatement.setString(4, customerPhoneNum);
                preparedStatement.setTimestamp(5, createDate);
                preparedStatement.setString(6, createdBy);
                preparedStatement.setTimestamp(7, lastUpdateTS);
                preparedStatement.setString(8, lastUpdatedBy);
                preparedStatement.setInt(9, customerDivisionID);

                preparedStatement.executeUpdate();
                System.out.println("Saved customer to DB");


            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public static void RemoveCustomer(Customer selectedCustomer) {
// TODO add check for any appointments (for customer?) that need to be deleted first, give dialog to user)

        Customer customer = selectedCustomer;

        try {
            Connection connxn = DbConnection.getConnection();

            String sql = "DELETE FROM customers WHERE Customer_ID = ?";

            DbQuery.setPreparedStatement(connxn, sql);

            PreparedStatement preparedStatement = DbQuery.getPreparedStatement();

            preparedStatement.setInt(1, customer.getCustomerID());

            preparedStatement.execute();

            System.out.println("Deleted customer from database");



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Customer getCustomer(int customerID) throws SQLException {

        String sql = "SELECT Customer_Name FROM customers WHERE Customer_ID = ?";

        DbQuery.setPreparedStatement(DbConnection.getConnection(), sql);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();
        preparedStatement.setInt(1, customerID);

        ResultSet resultSet = preparedStatement.executeQuery();

        String customerName = null;

        while(resultSet.next()){
            customerName = resultSet.getString("Customer_Name");
        }

        Customer customer = new Customer(customerID, customerName);

        return customer;
    }
}
