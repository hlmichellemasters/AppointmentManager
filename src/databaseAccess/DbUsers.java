package databaseAccess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;
import utilities.DbConnection;
import utilities.DbQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUsers {

    /**
     * gets all of the users from the database
     * @return a list of all of the users
     * @throws Exception back to the calling initialization for any exceptions
     */
    public static ObservableList<User> getUsersFromDB() throws Exception {

        ObservableList<User> allUsers = FXCollections.observableArrayList();

        DbConnection.getConnection();

        String sql = "SELECT * FROM users";

        DbQuery.createQuery(sql);

        ResultSet resultSet = DbQuery.getResultSet();

        while(resultSet.next()) {

            int userID = resultSet.getInt("User_ID");
            String name = resultSet.getString("User_Name");

            User user = new User(userID, name);

            allUsers.add(user);
        }

        return allUsers;
    }

    /**
     * gets a user's information from the database from the userID given
     * @param userID passed to the function to look up the user by
     * @return user if found
     * @throws SQLException back to the calling function
     */
    public static User getUser(int userID) throws SQLException {

        String sql = "SELECT User_Name FROM users WHERE User_ID = ?";

        DbQuery.setPreparedStatement(DbConnection.getConnection(), sql);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();
        preparedStatement.setInt(1, userID);

        ResultSet resultSet = preparedStatement.executeQuery();

        String userName = null;

        while(resultSet.next()){
            userName = resultSet.getString("User_Name");
        }

        User user = new User(userID, userName);

        return user;
    }

    /**
     * checks the password for the userName entered against the password in the database
     * @param userID is the userID passed from the log-in form
     * @param password is the password entered in the log-in form
     * @return true if the password is a match, otherwise false
     * @throws SQLException back to the calling function, OnLogInButton
     */
    public static boolean checkPassword(int userID, String password) throws SQLException {
        System.out.println("Checking password");

        String sql = "SELECT Password FROM users WHERE User_ID = ?";

        DbQuery.setPreparedStatement(DbConnection.getConnection(), sql);
        PreparedStatement preparedStatement = DbQuery.getPreparedStatement();
        preparedStatement.setInt(1, userID);

        ResultSet resultSet = preparedStatement.executeQuery();
        System.out.println("Result set: " + resultSet);

        while(resultSet.next()) {

            String dbPassword = resultSet.getString("Password");

            System.out.println("Password from DB is: " + dbPassword);
            System.out.println("Provided password is: " + password);
            System.out.println(password.equals(dbPassword));
            if (password.equals(dbPassword)) return true;
        }

        return false;
    }
}

