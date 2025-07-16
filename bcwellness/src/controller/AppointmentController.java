package controller;

import model.Appointment;
import model.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class AppointmentController {

    public static void addAppointment(Appointment appointment) {
        try (Connection conn = DBConnection.getConnection()){
            String sql = "INSERT INTO appointments (id,studentName,counselorId,date,time,status) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, appointment.getId());
            ps.setString(2, appointment.getStudentName());
            ps.setInt(3, appointment.getCounselorId());
            ps.setDate(4, appointment.getDate());
            ps.setTime(5, appointment.getTime());
            ps.setString(6, appointment.getStatus());
            ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Appointment> getAllAppointments(){
        ArrayList<Appointment> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Appointments");
            while (rs.next()){
                list.add(new Appointment(
                        rs.getInt("id"),
                        rs.getString("studentName"),
                        rs.getInt("counselorId"),
                        rs.getDate("date").toString(),
                        rs.getTime("time").toString(),
                        rs.getString("status")
                ));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}


