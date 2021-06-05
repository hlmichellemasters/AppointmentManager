/**
 * Heaven-leigh Michelle Masters
 * C195 Software II Advanced Java Concepts
 * QAM1 Task 1: Java Application Development
 * DB Connection class
 */
package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * provides the methods for starting, getting and closing the connection to the MYSQL database
 */
public class DbConnection {

        private static final String protocol ="jdbc";
        private static final String vendor =":mysql:";
        private static final String ipAddress = "//wgudb.ucertify.com:3306/";
        private static final String dbName ="WJ07kEJ";

        private static final String jdbcURL = protocol + vendor + ipAddress + dbName + "?connectionTimeZone = SERVER";

        private static final String userName = "U07kEJ";
        private static final String password = "53689054364";
        private static final String driver = "com.mysql.cj.jdbc.Driver";

        static Connection connxn;

    /**
     * starts the connection at the load the application
     */
    public static void startConnection() {

        try {
            Class.forName(driver);
            connxn = DriverManager.getConnection(jdbcURL, userName, password);

        } catch (SQLException e) {
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * gets the connection for any database access function
     * @return connection object
     */
    public static Connection getConnection() {

        return connxn;
    }

    /**
     * closes the connection at the exit of the application
     */
    public static void closeConnection() {

        try {
            connxn.close();

        } catch (Exception e) {
            //nothing to catch at this point
        }
    }
}

