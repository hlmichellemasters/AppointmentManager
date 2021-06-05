/**
 * Heaven-leigh Michelle Masters
 * C195 Software II Advanced Java Concepts
 * QAM1 Task 1: Java Application Development
 * utility class for querying the database
 */
package utilities;

import java.sql.*;

import static utilities.DbConnection.connxn;

/**
 * provides functions for creating queries, getting result set, and setting and getting prepared statements
 */
public class DbQuery {

    private static String query;
    private static Statement statement;
    private static ResultSet resultSet;
    private static PreparedStatement preparedStatement;

    /**
     * creates a database query given a SQL String
     * @param sql string to create query from
     */
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

    /**
     * gets the result set
     * @return ResultSet object
     */
    public static ResultSet getResultSet(){

        return resultSet;
    }

    /**
     * sets the prepared statement given a connection object and a SQL String
     * @param connxn object needed to prepare statement
     * @param sql String of SQL to prepare statement
     */
    public static void setPreparedStatement(Connection connxn, String sql) {

        try{

            preparedStatement = connxn.prepareStatement(sql);

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    /**
     * gets the prepared statement object
     * @return a prepared statement object
     */
    public static PreparedStatement getPreparedStatement() {

        return preparedStatement;
    }

}
