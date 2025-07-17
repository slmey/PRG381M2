package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* 
 * Provides a connection to the Derby database for the wellness management system.
 */

public class DBConnection {
    private static final String URL = "jdbc:derby:C:/Users/kemis/WellnessDB;create=true";
    private static final String USER = "app"; //databse username
    private static final String PASSWORD = "app"; //database password

    /* 
     * Establishes and returns a connection to the database.
     * Returns a Connection object for database operations
     * Throws SQLException if the connection fails
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
