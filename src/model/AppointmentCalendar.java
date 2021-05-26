package model;

import databaseAccess.DbAppointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;

public class AppointmentCalendar {

    private static ObservableList<Appointment> allAppointments;

    private static AppointmentCalendar calendarInstance = null;
    /**
     * singleton class to hold the appointments and do any filtering and sorting of the appointments
     * with help from Geeks for Geeks (https://www.geeksforgeeks.org/singleton-class-java/)
     */
    private AppointmentCalendar() {

        allAppointments = FXCollections.observableArrayList();
    }

    public static AppointmentCalendar getInstance() {
        if (calendarInstance == null) {
            calendarInstance = new AppointmentCalendar();
        }
        return calendarInstance;
    }

    /**
     * calls the getAppointments from the database appointments class and instantiates the
     * appointment calendar, which holds the appointments
     * also sorts the appointments
     * @throws Exception
     */
    public static void getAllAppointmentsFromDB() throws Exception {

        new AppointmentCalendar();
        allAppointments.addAll(DbAppointments.getAppointments());
        Collections.sort(allAppointments);

    }

    /**
     * adds an appointment to the appointment calendar
     * @param appointment
     */
    public static void addApptToCalendar(Appointment appointment) {

        allAppointments.add(appointment);
    }

    /**
     * deletes an appointment from the appointment calendar
     * @param selectedAppt
     * @return true if appointment is deleted and false for exceptions
     */
    public static boolean deleteAppointment(Appointment selectedAppt) {

        try {
            DbAppointments.removeAppointment(selectedAppt);
            allAppointments.remove(selectedAppt);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * provides the list of appointments in the appointment calendar
     * @return the list of all appointments
     */
    public static ObservableList<Appointment> provideApptList() {

        return allAppointments;

    }

    /**
     * filters all the appointments in the calendar for any appointments with the passed contact
     * @param selectedContact is the contact passed to filter the appointments for
     * @return a list of appointments that have the contact
     */
    public static ObservableList<Appointment> filterApptByContact(Contact selectedContact) {

        ObservableList<Appointment> filteredApptsByContact = FXCollections.observableArrayList();

            for (Appointment appt : allAppointments) {

                System.out.println("Appt contact is: " + appt.getContact() + " and the selected Contact is: " + selectedContact);
                if (appt.getContact().equals(selectedContact)) {
                    filteredApptsByContact.add(appt);
                    System.out.println("Found a match");
                }
            }

        return filteredApptsByContact;
    }

    /**
     * filters all the appointments in the calendar for any appointments with the passed customer
     * @param selectedCustomer is the customer passed to filter the appointments for
     * @return a list of appointments that have the customer
     */
    public static ObservableList<Appointment> filterApptByCustomer(Customer selectedCustomer) {

        ObservableList<Appointment> filteredApptsByCustomer = FXCollections.observableArrayList();

        for (Appointment appt : allAppointments) {

            System.out.println("Appt customer is: " + appt.getCustomer() + " and the selected Customer is: " + selectedCustomer);
            if (appt.getCustomer().equals(selectedCustomer)) {
                filteredApptsByCustomer.add(appt);
                System.out.println("Found a match");
            }
        }

        return filteredApptsByCustomer;
    }

    public static ObservableList<Appointment> filterApptByType(String type) {

        ObservableList<Appointment> allAppts = FXCollections.observableArrayList();
        ObservableList<Appointment> filteredApptsByType = FXCollections.observableArrayList();

        try {
            allAppts.addAll(DbAppointments.getAppointments());

            for (Appointment appt : allAppts) {

                System.out.println("Appt type is: " + appt.getType() + " and the selected type is: " + type);
                if (appt.getType().equals(type)) {
                    filteredApptsByType.add(appt);
                    System.out.println("Found a match");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filteredApptsByType;
    }

    /**
     * checks for any appointment overlaps for a customer against a proposed new appointment
     * inspiration for condition from: https://stackoverflow.com/questions/56732882/how-to-check-if-one-date-period-overlapping-another-date-period
     * @param apptCustomer is the customer to look for any existing appointments against
     * @param apptStart is the proposed new appointment start datetime
     * @param apptEnd is the proposed new appointment end datetime
     * @return true if the appointment overlaps with an existing one and false if no overlap
     */
    public static boolean apptOverlapsForCustomer(Customer apptCustomer, LocalDateTime apptStart, LocalDateTime apptEnd) {

        ObservableList<Appointment> filteredCustomerAppts = FXCollections.observableArrayList();

        filteredCustomerAppts = AppointmentCalendar.filterApptByCustomer(apptCustomer);
        System.out.println("Looked up customer's other appointments");

            for (Appointment appt: filteredCustomerAppts) {
                if ((!apptEnd.isBefore(appt.getStart()) && (!apptStart.isAfter(appt.getEnd())))) {
                    System.out.println("The appointments overlap");
                    return true;
                }
                System.out.println("apptEnd: " + apptEnd + "appt.getStart: " + appt.getStart());
                System.out.println(!apptEnd.isBefore(appt.getStart()));
            }
            return false;
    }
}
