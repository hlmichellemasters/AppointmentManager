/**
 * Heaven-leigh Michelle Masters
 * C195 Software II Advanced Java Concepts
 * QAM1 Task 1: Java Application Development
 * singleton class to hold the appointments
 */
package model;

import controller.ControllerUtilities;
import databaseAccess.DbAppointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;

/**
 * holds and performs any operations on the appointments within the calendar for the application
 */
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

    /**
     * creates singleton instance of appointment calendar if not already made, or returns current instance if it is.
     * @return
     */
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
        allAppointments.addAll(DbAppointments.getAllAppointmentsFromDB());
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

        Collections.sort(allAppointments);
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

                if (appt.getContact().equals(selectedContact)) {
                    filteredApptsByContact.add(appt);
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

            if (appt.getCustomer().equals(selectedCustomer)) {
                filteredApptsByCustomer.add(appt);

            }
        }

        return filteredApptsByCustomer;
    }

    /**
     * checks for any appointment overlaps for a customer against a proposed new appointment
     * inspiration for condition from: https://stackoverflow.com/questions/56732882/how-to-check-if-one-date-period-overlapping-another-date-period
     * @param apptCustomer is the customer to look for any existing appointments against
     * @param apptStart is the proposed new appointment start datetime
     * @param apptEnd is the proposed new appointment end datetime
     * @return true if the appointment overlaps with an existing one and false if no overlap
     */
    public static boolean apptOverlapsForCustomer(Customer apptCustomer, LocalDateTime apptStart, LocalDateTime apptEnd,
                                                  int newApptID) {

        ObservableList<Appointment> filteredCustomerAppts = AppointmentCalendar.filterApptByCustomer(apptCustomer);

            for (Appointment appt: filteredCustomerAppts) {
                if ((!apptEnd.isBefore(appt.getStart()) && (!apptStart.isAfter(appt.getEnd()))) &&
                        (appt.getApptID() != newApptID)) // checking that the overlap is not because of edited appt
                    {
                        return true;
                    }
            }
            return false;
    }

    /**
     *  displays an alert for any upcoming appointments in 15 minutes or any appointments that are currently in progress.
     *  also lets the user know there are not any in next 15 minutes or in progress if there are not any.
     */
    public static void checkForUpcomingAppts() {

        LocalDateTime now = LocalDateTime.ofInstant((Instant.now()), ZoneId.systemDefault());
        LocalDateTime nowPlus15min = now.plusMinutes(15);
        boolean foundAppt = false;

        for (int i = 0; i < allAppointments.size(); i++) {
            Appointment thisAppt = allAppointments.get(i);

            if ((!nowPlus15min.isBefore(thisAppt.getStart()) && (!now.isAfter(thisAppt.getEnd())))) {

                ControllerUtilities.InformationalDialog("Appointment within next 15 minutes or in-progress",
                        "Appointment ID: " + thisAppt.getApptID() + "\nStart Date and Time: " + thisAppt.getFormattedStart() +
                        "\nEnd Date and Time: " + thisAppt.getFormattedEnd());
                foundAppt = true;
                break;
            }
        }

        if (!foundAppt) {
            ControllerUtilities.InformationalDialog("No upcoming appointments",
                "There are no appointments in the next 15 minutes or currently in-progress");}
        }

    /**
     * finds and removes the appointment matching the appt ID and adds the new appointment information (to update appt)
     * @param updatedAppt Appointment passed to update an existing appointment
     */
    public static void updateAppointment(Appointment updatedAppt) {

        for (Appointment appt: allAppointments) {
            if (updatedAppt.getApptID() == appt.getApptID()) {
                allAppointments.remove(appt);
                allAppointments.add(updatedAppt);
                return;
            }
        }
    }
}

