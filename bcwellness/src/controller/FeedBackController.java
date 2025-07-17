package controller;

import model.Feedback;
import model.DBConnection;

import java.sql.*;
import java.util.ArrayList;

/*
 * Manages CRUD operations for feedback, interacting with the database.
 */
public class FeedBackController {

    /*
     * Adds new feedback to the database.
     * feedback - The feedback object to add
     */
    public static void addFeedback(Feedback feedback) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO Feedback (id, studentName, studentEmail, feedback, rating) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, feedback.getId());
            ps.setString(2, feedback.getStudentName());
            ps.setString(3, feedback.getStudentEmail());
            ps.setString(4, feedback.getFeedback());
            ps.setInt(5, feedback.getRating());
            ps.executeUpdate(); // Execute the insert query
        } catch (SQLException e) {
            e.printStackTrace(); // Log SQL exceptions for debugging
        }
    }

    /*
     * Retrieves all feedback from the database.
     * Returns an ArrayList containing all Feedback objects
     */
    public static ArrayList<Feedback> getAllFeedback() {
        ArrayList<Feedback> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Feedback");
            while (rs.next()) {
                list.add(new Feedback(
                        rs.getInt("id"),
                        rs.getString("studentName"),
                        rs.getString("studentEmail"),
                        rs.getString("feedback"),
                        rs.getInt("rating")
                )); // Add each row as a Feedback object
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log SQL exceptions for debugging
        }
        return list;
    }

    /*
     * Updates existing feedback in the database.
     * feedback - The feedback object with updated details
     */
    public static void updateFeedback(Feedback feedback) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE Feedback SET studentName = ?, studentEmail = ?, feedback = ?, rating = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, feedback.getStudentName());
            ps.setString(2, feedback.getStudentEmail());
            ps.setString(3, feedback.getFeedback());
            ps.setInt(4, feedback.getRating());
            ps.setInt(5, feedback.getId());
            ps.executeUpdate(); // Execute the update query
        } catch (SQLException e) {
            e.printStackTrace(); // Log SQL exceptions for debugging
        }
    }

    /*
     * Deletes feedback from the database based on its ID.
     * id - The ID of the feedback to delete
     */
    public static void deleteFeedback(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM Feedback WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate(); // Execute the delete query
        } catch (SQLException e) {
            e.printStackTrace(); // Log SQL exceptions for debugging
        }
    }
}