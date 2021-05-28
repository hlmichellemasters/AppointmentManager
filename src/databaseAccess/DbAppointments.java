/**
 * Heaven-leigh Michelle Masters
 * C195 Software II Advanced Java Concepts
 * QAM1 Task 1: Java Application Development
 * database access for Appointment class
 */

package databaseAccess;

import model.*;
import utilities.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utilities.DbQuery;
import utilities.TimeZoneConversions;

import java.sql.*;
import java.time.*;

/**
 * provides database access operations for the Appointment class
 */
public class DbAppointments {

    /**
     * gets all the appointments from the database
     * @return an observable list of Appointments
     * @throws SQLException to calling function for any SQL errors
     */
    public static ObservableList<Appointment> getAllAppointmentsFromDB() throws SQLException {

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

    /**
     * finds the next appointment ID from querying the database for the current max and adding 1
     * @return the next appointment ID found from this method to the calling function
     */
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

    /**
     * saves a new, or updates, an appointment to the database given the appointment parameters:
     * @param apptID ID of the appointment
     * @param apptTitle title of the appointment
     * @param apptDescription description of the appointment
     * @param apptType type of the appointment
     * @param apptLocation location of the appointment
     * @param apptContactID ID of the contact for the appointment
     * @param apptCustomerID ID of the customer for the appointment
     * @param apptUserID ID of th user for the appointment
     * @param startDate start date for the appointment
     * @param startTime start time for the appointment
     * @param endDate end date for the appointment
     * @param endTime end time for the appointment
     */
    public static void saveApptToDB(int apptID, String apptTitle, String apptDescription, String apptType,
                                    String apptLocation, int apptContactID, int apptCustomerID, int apptUserID,
                                    LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {

        // convert the local time to UTC
        LocalDateTime startDateTime = TimeZoneConversions.toUTCTime(LocalDateTime.of(startDate, startTime));
        LocalDateTime endDateTime = TimeZoneConversions.toUTCTime(LocalDateTime.of(endDate, endTime));

        // if the appointment is a new appointment
        if (apptID == getNextApptID()) {

            System.out.println("new appointment");

            String createdBy, lastUpdatedBy;
            createdBy = lastUpdatedBy = User.userLoggedIn.getUserName();

            Timestamp createDate = Timestamp.from(Instant.now());  // using Instant to create UTC DateTime object
            // then make into Timestamp so MySQL can make DateTime

            Timestamp lastUpdateTS = Timestamp.valueOf(LocalDateTime.now());

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


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // else this an edited appointment and need to update the SQL database
        else {

            System.out.println("updated appointment");

            String lastUpdatedBy = User.userLoggedIn.getUserName();
            Timestamp lastUpdateTS = Timestamp.valueOf(LocalDateTime.now());

            try {
                Connection connxn = DbConnection.getConnection();

                String sql = "UPDATE appointments " +
                        "SET Title = ?, Description = ? , Location = ?, Type = ?, Start = ?, End = ?, Last_Update = ?, " +
                        "Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? " +
                        "WHERE Appointment_ID = ?";

                DbQuery.setPreparedStatement(connxn, sql);

                PreparedStatement preparedStatement = DbQuery.getPreparedStatement();

                preparedStatement.setString(1, apptTitle);
                preparedStatement.setString(2, apptDescription);
                preparedStatement.setString(3, apptLocation);
                preparedStatement.setString(4, apptType);
                preparedStatement.setObject(5, startDateTime);
                preparedStatement.setObject(6, endDateTime);
                preparedStatement.setTimestamp(7, lastUpdateTS);
                preparedStatement.setString(8, lastUpdatedBy);
                preparedStatement.setInt(9, apptCustomerID);
                preparedStatement.setInt(10, apptUserID);
                preparedStatement.setInt(11, apptContactID);
                preparedStatement.setInt(12, apptID);

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * removes an appointment from the database
     * @param selectedAppt appointment passed to remove
     */
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

