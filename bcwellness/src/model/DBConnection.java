package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:derby://localhost:1527/db; create=true";
    private static final String USER = "app";
    private static final String PASSWORD = "app";

    static {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            initializeDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private static void initializeDatabase() {
        try (Connection conn = getConnection()) {
            // Create Counselors table if it doesn't exist
            String createCounselorsTable = "CREATE TABLE Counselors (" +
                    "counselorId INT PRIMARY KEY, " +
                    "firstName VARCHAR(50), " +
                    "lastName VARCHAR(50), " +
                    "specialization VARCHAR(100), " +
                    "email VARCHAR(100), " +
                    "availability VARCHAR(20))";
            
            // Create Appointments table if it doesn't exist
            String createAppointmentsTable = "CREATE TABLE Appointments (" +
                    "id INT PRIMARY KEY, " +
                    "studentName VARCHAR(100), " +
                    "counselorId INT, " +
                    "date VARCHAR(10), " +
                    "time VARCHAR(8), " +
                    "status VARCHAR(20))";
            
            // Create Feedback table if it doesn't exist
            String createFeedbackTable = "CREATE TABLE Feedback (" +
                    "feedbackId INT PRIMARY KEY, " +
                    "studentName VARCHAR(100), " +
                    "rating INT, " +
                    "comments VARCHAR(500))";
            
            try {
                conn.createStatement().execute(createCounselorsTable);
            } catch (SQLException e) {
                // Table already exists, ignore
            }
            
            try {
                conn.createStatement().execute(createAppointmentsTable);
            } catch (SQLException e) {
                // Table already exists, ignore
            }
            
            try {
                conn.createStatement().execute(createFeedbackTable);
            } catch (SQLException e) {
                // Table already exists, ignore
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
