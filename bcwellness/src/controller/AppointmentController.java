package controller;

import java.sql.*;
import java.util.ArrayList;
import model.Appointment;
import model.DBConnection;

public class AppointmentController {
    public static void addAppointment(Appointment appointment) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO Appointments (studentName, counselorId, date, time, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, appointment.getStudentName());
            ps.setInt(2, appointment.getCounselorId());
            ps.setDate(3, appointment.getDate());
            ps.setTime(4, appointment.getTime());
            ps.setString(5, appointment.getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

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
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAppointment(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM Appointments WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



