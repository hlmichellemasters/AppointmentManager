package model;

import databaseAccess.DbAppointments;
import databaseAccess.DbCustomers;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Appointment {

    private int apptID;
    private String title;
    private String description;
    private String location;
    private String type;
    private String formattedStart;
    private String formattedEnd;
    private LocalDateTime start;
    private LocalDateTime end;
    private User user;
    private Contact contact;
    private Customer customer;

    public Appointment(int apptID, String title, String description, String location, String type,
                       LocalDateTime start, LocalDateTime end, User user, Contact contact, Customer customer) {

        this.apptID = apptID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        formattedStart = displayDateTime(start);
        this.start = start;
        formattedEnd = displayDateTime(end);
        this.end = end;
        this.user = user;
        this.contact = contact;
        this.customer = customer;

        System.out.println("Made appointment");
    }

    public int getApptID() {
        return apptID;
    }

    public void setApptID(int apptID) {
        this.apptID = apptID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String displayDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy HH:mma");
        String formattedStart = localDateTime.format(formatter);
        System.out.println("Formatted start datetime is: " + formattedStart);
        return formattedStart;
    }

    public LocalDate getStartDate() { return start.toLocalDate();}

    public LocalTime getStartTime() { return start.toLocalTime();}

    public LocalDate getEndDate() { return end.toLocalDate();}

    public LocalTime getEndTime() { return end.toLocalTime();}

    public String getFormattedStart() { return formattedStart;}

    public void setFormattedStart() { this.formattedStart = formattedStart;}

    public String getFormattedEnd() { return formattedEnd;}

    public void setFormattedEnd() { this.formattedEnd = formattedEnd;}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString(){

        return apptID + " " + title;
    }

    public static ObservableList<Appointment> getAllAppointments() throws Exception {

        return DbAppointments.getAppointments();
    }
}
