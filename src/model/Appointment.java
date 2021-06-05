/**
 * Heaven-leigh Michelle Masters
 * C195 Software II Advanced Java Concepts
 * QAM1 Task 1: Java Application Development
 * object class for an Appointment
 */
package model;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * creates Appointment objects and the getters, setters, formatting and comparison operations
 */
public class Appointment implements Comparable<Appointment> {

    private int apptID;
    private String title;
    private String description;
    private String location;
    private String type;
    private String formattedStart;
    private String monthYear;
    private String formattedEnd;
    private LocalDateTime start;
    private LocalDateTime end;
    private User user;
    private Contact contact;
    private Customer customer;

    /**
     * constructor to create an Appointment
     * @param apptID ID of the appointment
     * @param title title of the appointment
     * @param description description of the appointment
     * @param location location of the appointment
     * @param type type of appointment
     * @param start start date and time of the appointment
     * @param end end date and time of the appointment
     * @param user user selected for the appointment
     * @param contact contact selected for the appointment
     * @param customer customer selected for the appointment
     */
    public Appointment(int apptID, String title, String description, String location, String type,
                       LocalDateTime start, LocalDateTime end, User user, Contact contact, Customer customer) {

        this.apptID = apptID;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        formattedStart = displayDateTime(start);
        this.start = start;
        monthYear = "" + start.getMonthValue() + "-" + start.getYear();
        formattedEnd = displayDateTime(end);
        this.end = end;
        this.user = user;
        this.contact = contact;
        this.customer = customer;
    }

    /**
     * gets ID of the appointment
     * @return ID of appointment at integer
     */
    public int getApptID() { return apptID; }

    /**
     * gets the title of the appointment
     * @return title as a String
     */
    public String getTitle() { return title; }

    /**
     * sets the title of the appointment
     * @param title passed to set for the appointment
     */
    public void setTitle(String title) { this.title = title; }

    /**
     * gets the description of the appointment
     * @return description as a String
     */
    public String getDescription() { return description; }

    /**
     * sets the description of the appointment
     * @param description passed to set for the appointment
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * gets the location of the appointment
     * @return location as a String
     */
    public String getLocation() { return location; }

    /**
     * sets the location of the appointment
     * @param location passed to set for the appointment
     */
    public void setLocation(String location) { this.location = location; }

    /**
     * gets the type of the appointment
     * @return type of appointment as a String
     */
    public String getType() { return type; }

    /**
     * sets the type of the appointment
     * @param type passed to set for the appointment
     */
    public void setType(String type) { this.type = type; }

    /**
     * creates a user-friendly string displaying a date time
     * @param localDateTime passed in date time to convert to string
     * @return string that displays the date time
     */
    public static String displayDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy HH:mma");
        String formattedDateTime = localDateTime.format(formatter);
        return formattedDateTime;
    }

    /**
     * gets the start date for the appointment
     * @return start date as a LocalDate object
     */
    public LocalDate getStartDate() { return start.toLocalDate(); }

    /**
     * gets the start time for the appointment
     * @return start time as a LocalTime object
     */
    public LocalTime getStartTime() { return start.toLocalTime(); }

    /**
     * gets the start date and time for the appointment
     * @return start date and time as a LocalDateTime object
     */
    public LocalDateTime getStart() { return start; }

    /**
     * gets the end date of the appointment
     * @return end date as a LocalDate object
     */
    public LocalDate getEndDate() { return end.toLocalDate(); }

    /**
     * gets the end time of the appointment
     * @return end time as a LocalTime object
     */
    public LocalTime getEndTime() { return end.toLocalTime(); }

    /**
     * gets the end date time for the appointment
     * @return end date time as a LocalDateTime object
     */
    public LocalDateTime getEnd() { return end; }

    /**
     * gets the formatted start date and time of the appointment
     * @return the start date and time formatted as a String
     */
    public String getFormattedStart() { return formattedStart; }

    /**
     * gets the formatted end date and time of the appointment
     * @return the end date and time formatted as a String
     */
    public String getFormattedEnd() { return formattedEnd; }

    /**
     * gets the Month and Year of the appointment
     * @return the Month and Year as a String
     */
    public String getMonthYear() { return monthYear; }

    /**
     * gets the User for an appointment
     * @return a user object assigned for that appointment
     */
    public User getUser() { return user; }

    /**
     * sets the User for an appointment
     * @param user passed to set the appointment's user as
     */
    public void setUser(User user) { this.user = user; }

    /**
     * gets the Contact for an appointment
     * @return the Contact object that is assigned for that appointment
     */
    public Contact getContact() { return contact; }

    /**
     * sets the Contact for an appointment
     * @param contact passed to set for the appointment
     */
    public void setContact(Contact contact) { this.contact = contact; }

    /**
     * gets the Customer for an appointment
     * @return Customer object that is associated with that appointment
     */
    public Customer getCustomer() { return customer; }

    /**
     * sets the Customer for an appointment
     * @param customer passed to set for the appointment
     */
    public void setCustomer(Customer customer) { this.customer = customer; }

    /**
     * overrides the default toString method to instead display the appointment ID and the title for displaying Appointment
     * @return a string that displays the object in a user-friendly representation.
     */
    @Override
    public String toString() { return apptID + " " + title; }

    /**
     * overrides the compareTo method to instead compare Appointments based off their formatted start date
     * @param appt the appointment to compare to
     * @return an integer that is used to sort the appointments
     */
    @Override
    public int compareTo(Appointment appt) {

        return this.getFormattedStart().compareTo(appt.getFormattedStart());
    }

    /**
     * calculates the duration between the start and end date time of an appointment in hours
     * @return the number of hours the appointment is.
     */
    public long getHours() { return Duration.between(start, end).toHours(); }

    /**
     * calculates whether an appointment's date starts after another date
     * @param date passed for comparing to an appointment's start date
     * @return boolean value of true if it is after or false is not after
     */
    public boolean isAfter(LocalDateTime date) { return start.isAfter(date); }

    /**
     * calculates whether an appointment's date ends before another date
     * @param date passed for comparing to an appointment's end date
     * @return boolean value of true if it is before or false is not before
     */
    public boolean isBefore(LocalDateTime date) { return end.isBefore(date); }

}
