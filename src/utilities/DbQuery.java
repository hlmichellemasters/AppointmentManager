package utilities;

import java.sql.*;

import static utilities.DbConnection.connxn;

public class DbQuery {

    private static String query;
    private static Statement statement;
    private static ResultSet resultSet;
    private static PreparedStatement preparedStatement;

    public static void createQuery(String sql) {

        query = sql;

        try {
            statement = connxn.createStatement();

            if(query.toLowerCase().startsWith("select"))
                resultSet = statement.executeQuery(query);
            if(query.toLowerCase().startsWith("delete"))
                statement.executeUpdate(query);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static ResultSet getResultSet(){

        return resultSet;
    }

    public static void setPreparedStatement(Connection connxn, String sql) {

        try{

            preparedStatement = connxn.prepareStatement(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public static PreparedStatement getPreparedStatement() {

        return preparedStatement;
    }


}
