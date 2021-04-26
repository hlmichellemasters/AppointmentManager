package model;

import databaseAccess.DbAppointments;
import javafx.collections.ObservableList;

import java.time.ZonedDateTime;

public class Appointment {

    private int apptID;
    private String title;
    private String description;
    private String location;
    private String type;
    private ZonedDateTime start;
    private ZonedDateTime end;
    private User user;
    private Contact contact;
    private Customer customer;

    public Appointment(int apptID, String title, String description, String location, String type,
                       ZonedDateTime start, ZonedDateTime end, User user, Contact contact, Customer customer) {

        this.apptID = apptID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.user = user;
        this.contact = contact;
        this.customer = customer;
    }

    @Override
    public String toString(){

        return apptID + " " + title;

    }
}
