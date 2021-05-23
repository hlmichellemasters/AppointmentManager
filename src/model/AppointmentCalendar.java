package model;

import databaseAccess.DbAppointments;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

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

    public static void getAllAppointmentsFromDB() throws Exception {

        new AppointmentCalendar();
        allAppointments.addAll(DbAppointments.getAppointments());

    }

    public static void addApptToCalendar(Appointment appointment) {

        allAppointments.add(appointment);
    }

    public static ObservableList<Appointment> provideApptList() throws Exception {

        return DbAppointments.getAppointments();

    }

    public static ObservableList<Appointment> filterApptByContact(Contact selectedContact) {

        ObservableList<Appointment> filterAppts = FXCollections.observableArrayList();

        try {
            allAppointments.addAll(DbAppointments.getAppointments());

            for (Appointment appt : allAppointments) {

                System.out.println("Appt contact is: " + appt.getContact() + " and the selected Contact is: " + selectedContact);
                if (appt.getContact().equals(selectedContact)) {
                    filterAppts.add(appt);
                    System.out.println("Found a match");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filterAppts;
    }

    public static ObservableList<Appointment> filterApptByType(String type) {

        ObservableList<Appointment> allAppts = FXCollections.observableArrayList();
        ObservableList<Appointment> filterAppts = FXCollections.observableArrayList();

        try {
            allAppts.addAll(DbAppointments.getAppointments());

            for (Appointment appt : allAppts) {

                System.out.println("Appt type is: " + appt.getType() + " and the selected type is: " + type);
                if (appt.getType().equals(type)) {
                    filterAppts.add(appt);
                    System.out.println("Found a match");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return filterAppts;
    }
}
