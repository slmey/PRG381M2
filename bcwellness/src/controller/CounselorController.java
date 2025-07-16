package controller;

import model.Counselor;
import model.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class CounselorController {

    // Create - Add new counselor
    public static boolean addCounselor(Counselor counselor){
        try (Connection conn = DBConnection.getConnection()){
            String sql = "INSERT INTO Counselors (counselorId, firstName, lastName, specialization, email, availability) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, counselor.getCounselorId());
            ps.setString(2, counselor.getFirstName());
            ps.setString(3, counselor.getLastName());
            ps.setString(4, counselor.getSpecialization());
            ps.setString(5, counselor.getEmail());
            ps.setString(6, counselor.getAvailability());
            ps.executeUpdate();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    // Read - Get all counselors
    public static ArrayList<Counselor> getAllCounselors(){
        ArrayList<Counselor> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Counselors");
            while (rs.next()){
                list.add(new Counselor(
                        rs.getInt("counselorId"),
                        rs.getString("firstName"),
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

    // Read - Get counselor by ID
    public static Counselor getCounselorById(int counselorId){
        try (Connection conn = DBConnection.getConnection()){
            String sql = "SELECT * FROM Counselors WHERE counselorId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, counselorId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return new Counselor(
                        rs.getInt("counselorId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("specialization"),
                        rs.getString("email"),
                        rs.getString("availability")
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    // Update - Update existing counselor
    public static boolean updateCounselor(Counselor counselor){
        try (Connection conn = DBConnection.getConnection()){
            String sql = "UPDATE Counselors SET firstName = ?, lastName = ?, specialization = ?, email = ?, availability = ? WHERE counselorId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, counselor.getFirstName());
            ps.setString(2, counselor.getLastName());
            ps.setString(3, counselor.getSpecialization());
            ps.setString(4, counselor.getEmail());
            ps.setString(5, counselor.getAvailability());
            ps.setInt(6, counselor.getCounselorId());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    // Delete - Remove counselor
    public static boolean deleteCounselor(int counselorId){
        try (Connection conn = DBConnection.getConnection()){
            String sql = "DELETE FROM Counselors WHERE counselorId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, counselorId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    // Check if counselor ID exists
    public static boolean counselorExists(int counselorId){
        try (Connection conn = DBConnection.getConnection()){
            String sql = "SELECT COUNT(*) FROM Counselors WHERE counselorId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, counselorId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return rs.getInt(1) > 0;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
