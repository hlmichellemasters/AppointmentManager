package model;

import databaseAccess.DbContacts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Contact {

    private int contactID;
    private String contactName;
    private static ObservableList<Contact> contactList = FXCollections.observableArrayList();

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

    public static void getAllContactsFromDB() throws Exception {

        DbContacts.getContactsFromDB();
    }

    public static ObservableList<Contact> provideContactList() throws Exception {

        return contactList;
    }

    public static void addToContactsList(Contact contact) throws Exception {

        contactList.add(contact);
    }

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
