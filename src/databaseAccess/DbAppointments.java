package databaseAccess;

import model.*;
import utilities.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;

public class DbAppointments {

    public static ObservableList<Appointment> getAppointments() throws SQLException {

        ObservableList<Appointment> allAppts = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointments";

        PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()) {

            int apptID = resultSet.getInt("Appointment_ID");
            String title = resultSet.getString("Title");
            String description = resultSet.getString("Description");
            String location = resultSet.getString("Location");
            String type = resultSet.getString("Type");
            LocalDate startDate = resultSet.getDate("Start").toLocalDate(); //datetime in MySQL
            LocalTime startTime = resultSet.getTime("Start").toLocalTime(); //datetime in MySQL
            LocalDate endDate = resultSet.getDate("End").toLocalDate(); //datetime in MySQL
            LocalTime endTime = resultSet.getTime("End").toLocalTime(); //datetime in MySQL
            String createdBy = resultSet.getString("Created_By");
            int customerID = resultSet.getInt("Customer_ID");
            int userID = resultSet.getInt("User_ID");
            int contactID = resultSet.getInt("Contact_ID");

            // converts local datetime to zoned datetime
            ZonedDateTime startDateTime = LocalDateTime.of(startDate, startTime).atZone(ZoneId.systemDefault());
            ZonedDateTime endDateTime = LocalDateTime.of(endDate, endTime).atZone(ZoneId.systemDefault());

            Customer customer = DbCustomers.getCustomer(customerID);
            Contact contact = DbContacts.getContact(contactID);
            User user = DbUsers.getUser(userID);

            Appointment appointment = new Appointment(apptID, title, description, location, type, startDateTime,
            endDateTime, user, contact, customer);

            allAppts.add(appointment);
        }

        return allAppts;
    }
}

