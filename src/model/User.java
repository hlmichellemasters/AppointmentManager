/**
 * Heaven-leigh Michelle Masters
 * C195 Software II Advanced Java Concepts
 * QAM1 Task 1: Java Application Development
 * object class for users
 */
package model;

import databaseAccess.DbUsers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

/**
 * creates user objects, manages list of users, user logged in, and basic functions to manage users
 * minimizes getters and setters as this application is not used to manage user credentials
 */
public class User {

    private int userID;
    private String userName;
    private static ObservableList<User> userList = FXCollections.observableArrayList();

    public static User userLoggedIn;

    /**
     * constructor to create user objects
     * @param userID ID of the user
     * @param userName name of the user
     */
    public User(int userID, String userName) {

        this.userID = userID;
        this.userName = userName;

    }

    /**
     * gets the ID of the user
     * @return ID of the user
     */
    public int getUserID() { return userID; }

    /**
     * gets the username of the user
     * @return the user name of the user
     */
    public String getUserName() { return userName;}

    /**
     * overrides the default toString method to instead represent users as their user ID and their username
     * @return the String of their user ID and username
     */
    @Override
    public String toString() { return "[" + userID + "] " + userName;}

    /**
     * sets the user currently logged in to reference for logging and saving data to database
     * @param user is the user that logged in
     */
    public void setUserLoggedIn(User user) { userLoggedIn = user;}

    /**
     * gets the user ID from a passed in username
     * this is used in order to validate the user's username and password in the database
     * @param userName username that is passed from log-in form
     * @return int representing the user's ID, or -1 if user is not found
     */
    public static int getUserIDFromUserName(String userName) {
        for (User user: userList) {
            if (user.getUserName().equals(userName)) {
                return user.getUserID();
            }
        }
        return -1;
    }

    /**
     * calls the DBUsers class to retrieve all users from the Database
     * adds all users retrieved into the user list
     */
    public static void getAllUsersFromDB() {

        try {
            userList.addAll(DbUsers.getUsersFromDB());

        } catch (Exception exception) {

            exception.printStackTrace();
        }
    }

    /**
     * provides the list of users to the application
     * @return the list of users
     */
    public static ObservableList<User> provideUserList() {

        return userList;
    }

    /**
     * finds a user from the user list when provided a user ID
     * @param userID the ID of the user to find
     * @return the user object if found, otherwise returns null
     */
    public static User getUserByID(int userID) {

        User foundUser = null;
        for (User user : userList) {
            if (user.userID == userID) {
                foundUser = user;
            }
        }
        return foundUser;
    }

}
