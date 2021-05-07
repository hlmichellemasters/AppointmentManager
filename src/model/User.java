package model;

import databaseAccess.DbUsers;
import javafx.collections.ObservableList;

public class User {

    private int userID;
    private String userName;
    private String password;

    public static User userLoggedIn;

    public User(int userID, String userName) {

        this.userID = userID;
        this.userName = userName;
    }

    public int getUserID() { return userID; }

    public void setUserID(int userID) { this.userID = userID;}

    public String getUserName() { return userName;}

    public void setUserName(String userName) { this.userName = userName;}

    public String getPassword() { return password;}

    public void setPassword(String password) { this.password = password;}

    @Override
    public String toString() { return "[" + userID + "] " + userName;}

    public void setUserLoggedIn(User user) { userLoggedIn = user;}

    public static ObservableList<User> getAllUsers() throws Exception {

        return DbUsers.getUsersFromDB();
    }
}
