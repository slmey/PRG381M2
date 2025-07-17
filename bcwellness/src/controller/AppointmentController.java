package controller;

import java.sql.*;
import java.util.ArrayList;
import model.Appointment;
import model.DBConnection;

/* 
 * Manages CRUD operations for appointments, interacting with the database.
 */
public class AppointmentController {
    /* 
     * Adds a new appointment to the database.
     * appointment - The appointment object to add
     */
    public static void addAppointment(Appointment appointment) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO Appointments (studentName, counselorId, date, time, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, appointment.getStudentName());
            ps.setInt(2, appointment.getCounselorId());
            ps.setDate(3, appointment.getDate());
            ps.setTime(4, appointment.getTime());
            ps.setString(5, appointment.getStatus());
            ps.executeUpdate(); // Execute the insert query
        } catch (SQLException e) {
            e.printStackTrace(); // Log SQL exceptions for debugging
        }
    }

    /* 
     * Retrieves all appointments from the database.
     * Returns an ArrayList containing all Appointment objects
     */
    public static ArrayList<Appointment> getAllAppointments() {
        ArrayList<Appointment> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Appointments");
            while (rs.next()) {
                list.add(new Appointment(
                        rs.getInt("id"),
                        rs.getString("studentName"),
                        rs.getInt("counselorId"),
                        rs.getDate("date"),
                        rs.getTime("time"),
                        rs.getString("status")
                )); // Add each row as an Appointment object
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log SQL exceptions for debugging
        }
        return list;
    }

    /* 
     * Updates an existing appointment in the database.
     * appointment - The appointment object with updated details
     */
    public static void updateAppointment(Appointment appointment) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE Appointments SET studentName = ?, counselorId = ?, date = ?, time = ?, status = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, appointment.getStudentName());
            ps.setInt(2, appointment.getCounselorId());
            ps.setDate(3, appointment.getDate());
            ps.setTime(4, appointment.getTime());
            ps.setString(5, appointment.getStatus());
            ps.setInt(6, appointment.getId());
            ps.executeUpdate(); // Execute the update query
        } catch (SQLException e) {
            e.printStackTrace(); // Log SQL exceptions for debugging
        }
    }

    /* 
     * Deletes an appointment from the database based on its ID.
     * id - The ID of the appointment to delete
     */
    public static void deleteAppointment(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM Appointments WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate(); // Execute the delete query
        } catch (SQLException e) {
            e.printStackTrace(); // Log SQL exceptions for debugging
        }
    }
}



