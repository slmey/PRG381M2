package controller;

import model.Counselor;
import model.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class CounselorController {

    public static void addCounselor(Counselor counselor){
        try (Connection conn = DBConnection.getConnection()){
            String sql = "INSERT INTO Counselor (counselorId, firstName, lastName, specialization, email, availability) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, counselor.getCounselorId());
            ps.setString(2, counselor.getFirstName());
            ps.setString(3, counselor.getLastName());
            ps.setString(4, counselor.getSpecialization());
            ps.setString(5, counselor.getEmail());
            ps.setString(6, counselor.getAvailability());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Counselor> getAllCounselors(){
        ArrayList<Counselor> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Counselors");
            while (rs.next()){
                list.add(new Counselor(
                        rs.getInt("counselorId"),
                        rs.getString("firtsName"),
                        rs.getString("lastName"),
                        rs.getString("specialization"),
                        rs.getString("email"),
                        rs.getString("availability")
                ));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
