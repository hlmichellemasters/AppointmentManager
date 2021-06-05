/**
 * Heaven-leigh Michelle Masters
 * C195 Software II Advanced Java Concepts
 * QAM1 Task 1: Java Application Development
 * object class for Contacts
 */
package model;

import databaseAccess.DbContacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * creates Contact objects, minimizes getters and setters since application does not edit these
 * also provides formatting and comparison operations
 */
public class Contact {

    private int contactID;
    private String contactName;
    private static ObservableList<Contact> contactList = FXCollections.observableArrayList();

    /**
     * constructor to hold the data of a Contact
     * @param contactID
     * @param contactName
     */
    public Contact(int contactID, String contactName) {

        this.contactID = contactID;
        this.contactName = contactName;

    }

    /**
     * gets ID of contact
     * @return ID of contact as an integer
     */
    public int getContactID() { return contactID; }

    /**
     * overrides the default toString method to represent contact objects as their ID and name
     * @return the String representation of Contact objects
     */
    @Override
    public String toString() { return "[" + contactID + "] " + contactName; }

    /**
     * overrides the equals method for Contact and calls two objects equal if the contact name is the same
     * adapted from https://www.infoworld.com/article/3305792/comparing-java-objects-with-equals-and-hashcode.html
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Contact contact = (Contact) object;

        return contactName.equals(contact.contactName);

    }

    /**
     * calls the database contacts class to get the contacts from the database
     */
    public static void getAllContactsFromDB() {

        try {
            DbContacts.getContactsFromDB();

        } catch (Exception exception) {

            exception.printStackTrace();
        }
    }

    /**
     * provides the list of all contacts
     * @return list of all contacts
     */
    public static ObservableList<Contact> provideContactList() { return contactList; }

    /**
     * adds a contact to the contact list
     * @param contact is the contact passed to add to the contact list
     */
    public static void addToContactsList(Contact contact) { contactList.add(contact); }

    /**
     * finds and provides a contact by the contact ID
     * @param contactID ID of the contact to find
     * @return contact object that matches the ID, otherwise returns null
     */
    public static Contact getContactByID(int contactID) {
        Contact foundContact = null;
        for (Contact contact : contactList) {
            if (contact.contactID == contactID) {
                foundContact = contact;
            }
        }
        return foundContact;
    }

}
