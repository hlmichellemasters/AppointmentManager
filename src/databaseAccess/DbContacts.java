package databaseAccess;

import model.Contact;
import model.Division;
import utilities.DbConnection;
import utilities.DbQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbContacts {

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

