package model;

import databaseAccess.DbUsers;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User {

    private int userID;
    private String userName;
    private String password;
    private static ObservableList<User> userList = FXCollections.observableArrayList();

    public static User userLoggedIn;


    public User(int userID, String userName) {

        this.userID = userID;
        this.userName = userName;

        userList.add(this);
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

    public static ObservableList<User> getAllUsersFromDB() throws Exception {

        return DbUsers.getUsersFromDB();
    }

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
