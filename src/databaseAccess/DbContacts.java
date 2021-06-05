/**
 * Heaven-leigh Michelle Masters
 * C195 Software II Advanced Java Concepts
 * QAM1 Task 1: Java Application Development
 * database access for Contact class
 */
package databaseAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contact;
import utilities.DbConnection;
import utilities.DbQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * provides database access operations for the Contact class
 */
public class DbContacts {

    /**
     * gets the contacts information from the database and adds to the contacts list
     * @throws Exception for an SQL or other errors that occur
     */
    public static void getContactsFromDB() throws Exception {

        ObservableList<Contact> allContacts = FXCollections.observableArrayList();

        DbConnection.getConnection();

        String sql = "SELECT * FROM contacts";

        DbQuery.createQuery(sql);

        ResultSet resultSet = DbQuery.getResultSet();

        while(resultSet.next()) {

            int contactID = resultSet.getInt("Contact_ID");
            String name = resultSet.getString("Contact_Name");

            Contact contact = new Contact(contactID, name);

            Contact.addToContactsList(contact);
        }
    }

    /**
     * gets a contact from the database given a contact ID and returns contact to caller (to get appts from DB)
     * @param contactID int passed to find a contact in the database by
     * @return Contact object
     * @throws SQLException for any SQL error
     */
    public static Contact getContact(int contactID) throws SQLException {

        String sql = "SELECT Contact_Name FROM contacts WHERE Contact_ID = ?";

        DbQuery.setPreparedStatement(DbConnection.getConnection(), sql);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();
        preparedStatement.setInt(1, contactID);

        ResultSet resultSet = preparedStatement.executeQuery();

        String contactName = null;

        while(resultSet.next()){
            contactName = resultSet.getString("Contact_Name");
        }

        Contact contact = new Contact(contactID, contactName);

        return contact;
    }
}

