package controller;

import model.Counselor;
import model.DBConnection;

import java.sql.*;
import java.util.ArrayList;

/*
 * Manages CRUD operations for counselors, interacting with the database.
 */
public class CounselorController {

    /*
     * Adds a new counselor to the database.
     * counselor - The counselor object to add
     */
    public static void addCounselor(Counselor counselor) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO Counselor (counselorId, firstName, lastName, specialization, email, availability) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, counselor.getCounselorId());
            ps.setString(2, counselor.getFirstName());
            ps.setString(3, counselor.getLastName());
            ps.setString(4, counselor.getSpecialization());
            ps.setString(5, counselor.getEmail());
            ps.setString(6, counselor.getAvailability());
            ps.executeUpdate(); // Execute the insert query
        } catch (SQLException e) {
            e.printStackTrace(); // Log SQL exceptions for debugging
        }
    }

    /*
     * Retrieves all counselors from the database.
     * Returns an ArrayList containing all Counselor objects
     */
    public static ArrayList<Counselor> getAllCounselors() {
        ArrayList<Counselor> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Counselor"); // Fixed table name
            while (rs.next()) {
                list.add(new Counselor(
                        rs.getInt("counselorId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("specialization"),
                        rs.getString("email"),
                        rs.getString("availability")
                )); // Add each row as a Counselor object
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log SQL exceptions for debugging
        }
        return list;
    }

    /*
     * Updates an existing counselor's details in the database.
     * counselor - The counselor object with updated details
     */
    public static void updateCounselor(Counselor counselor) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE Counselor SET firstName = ?, lastName = ?, specialization = ?, email = ?, availability = ? WHERE counselorId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, counselor.getFirstName());
            ps.setString(2, counselor.getLastName());
            ps.setString(3, counselor.getSpecialization());
            ps.setString(4, counselor.getEmail());
            ps.setString(5, counselor.getAvailability());
            ps.setInt(6, counselor.getCounselorId());
            ps.executeUpdate(); // Execute the update query
        } catch (SQLException e) {
            e.printStackTrace(); // Log SQL exceptions for debugging
        }
    }

    /*
     * Deletes a counselor from the database based on their ID.
     * id - The ID of the counselor to delete
     */
    public static void deleteCounselor(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM Counselor WHERE counselorId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate(); // Execute the delete query
        } catch (SQLException e) {
            e.printStackTrace(); // Log SQL exceptions for debugging
        }
    }
}