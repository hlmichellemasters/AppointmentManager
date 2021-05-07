package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

        public static void startConnection() {

            try {
            Class.forName(driver);
            connxn = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connection Successful");

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Connection Unsuccessful");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("Connection Unsuccessful");
            }
        }

        public static Connection getConnection(){

            return connxn;
        }

        public static void closeConnection(){

            try {
                connxn.close();
                System.out.println("Connection Closed");
            } catch (Exception e) {
                System.out.println("Application closing anyway");
            }
        }
}

