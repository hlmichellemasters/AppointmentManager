package databaseAccess;

import model.Contact;
import model.User;
import utilities.DbConnection;
import utilities.DbQuery;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUsers {

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
}

