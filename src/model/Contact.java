package model;

import databaseAccess.DbContacts;
import javafx.collections.ObservableList;

public class Contact {

    private int contactID;
    private String contactName;

    public Contact(int contactID, String contactName) {
        this.contactID = contactID;
        this.contactName = contactName;
    }

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    @Override
    public String toString() {
        return "[" + contactID + "] " + contactName;
    }

    public static ObservableList<Contact> getAllContacts() throws Exception {

        return DbContacts.getContactsFromDB();
    }
}
