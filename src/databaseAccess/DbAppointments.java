package databaseAccess;

import model.*;
import utilities.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.DbQuery;
import utilities.TimeZoneConversions;

import java.sql.*;
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
//            System.out.println("startDate originally is " + startDate);
            LocalTime startTime = resultSet.getTime("Start").toLocalTime(); //datetime in MySQL
//            System.out.println("startTime originally is " + startTime);

            LocalDate endDate = resultSet.getDate("End").toLocalDate(); //datetime in MySQL
            LocalTime endTime = resultSet.getTime("End").toLocalTime(); //datetime in MySQL
            int customerID = resultSet.getInt("Customer_ID");
            int userID = resultSet.getInt("User_ID");
            int contactID = resultSet.getInt("Contact_ID");

            LocalDateTime startDateTime = TimeZoneConversions.toRealLocalDateTime(LocalDateTime.of(startDate, startTime));
            LocalDateTime endDateTime = TimeZoneConversions.toRealLocalDateTime(LocalDateTime.of(endDate, endTime));

            Customer customer = DbCustomers.getCustomer(customerID);
            Contact contact = DbContacts.getContact(contactID);
            User user = DbUsers.getUser(userID);

            Appointment appointment = new Appointment(apptID, title, description, location, type, startDateTime,
            endDateTime, user, contact, customer);

            allAppts.add(appointment);
        }
        return allAppts;
    }

    public static int getNextApptID() {

        int nextApptID = 0;

        try {
            Connection connxn = DbConnection.getConnection();

            String sql =  "SELECT MAX(Appointment_ID) + 1 FROM appointments";

            DbQuery.setPreparedStatement(connxn, sql);
            PreparedStatement preparedStatement = DbQuery.getPreparedStatement();

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                nextApptID = resultSet.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return nextApptID;
    }

    public static void saveApptToDB(int apptID, String apptTitle, String apptDescription, String apptType,
                                    String apptLocation, int apptContactID, int apptCustomerID, int apptUserID,
                                    LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {

        System.out.println("Started Saving Appt to DB");

        if (apptID == getNextApptID()) {

            String createdBy, lastUpdatedBy;

            createdBy = lastUpdatedBy = User.userLoggedIn.getUserName();

            Timestamp createDate = Timestamp.from(Instant.now());  // using Instant to create UTC DateTime object
            // then make into Timestamp so MySQL can make DateTime

//            System.out.println("The current time at UTC is ");
            Timestamp lastUpdateTS = Timestamp.valueOf(LocalDateTime.now());

            // convert the local time to UTC
            LocalDateTime startDateTime = TimeZoneConversions.toUTCTime(LocalDateTime.of(startDate, startTime));
            LocalDateTime endDateTime = TimeZoneConversions.toUTCTime(LocalDateTime.of(endDate, endTime));


            try {
                Connection connxn = DbConnection.getConnection();

                String sql = "INSERT INTO " +
                        "appointments(Title, Description, Location, Type, Start, End, Create_Date, Created_By, " +
                        "Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?," +
                        "?, ?, ?, ?, ?)";

                DbQuery.setPreparedStatement(connxn, sql);

                PreparedStatement preparedStatement = DbQuery.getPreparedStatement();

                preparedStatement.setString(1, apptTitle);
                preparedStatement.setString(2, apptDescription);
                preparedStatement.setString(3, apptLocation);
                preparedStatement.setString(4, apptType);
                preparedStatement.setObject(5, startDateTime);
                preparedStatement.setObject(6, endDateTime);
                preparedStatement.setTimestamp(7, createDate);
                preparedStatement.setString(8, createdBy);
                preparedStatement.setTimestamp(9, lastUpdateTS);
                preparedStatement.setString(10, lastUpdatedBy);
                preparedStatement.setInt(11, apptCustomerID);
                preparedStatement.setInt(12, apptUserID);
                preparedStatement.setInt(13, apptContactID);

                preparedStatement.executeUpdate();
                System.out.println("Saved appointment to DB");


            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public static void removeAppointment(Appointment selectedAppt) {

        Appointment appt = selectedAppt;

            try {
            Connection connxn = DbConnection.getConnection();

            String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";

            DbQuery.setPreparedStatement(connxn, sql);

            PreparedStatement preparedStatement = DbQuery.getPreparedStatement();

            preparedStatement.setInt(1, appt.getApptID());

            preparedStatement.execute();

            System.out.println("Deleted appointment from database");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

