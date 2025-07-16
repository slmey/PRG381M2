package controller;

import model.Feedback;
import model.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class FeedBackController {
    public static void addFeedback(Feedback feedback) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO Feedback (id, studentName, studentEmail, feedback, rating) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, feedback.getId());
            ps.setString(2, feedback.getStudentName());
            ps.setString(3, feedback.getStudentEmail());
            ps.setString(4, feedback.getFeedback);
            ps.setInt(5, feedback.getRating());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
