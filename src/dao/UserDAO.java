/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.MysqlConnection;
import util.OTPUtil;
import javax.swing.JOptionPane;
import java.sql.*;

public class UserDAO {
    
    // Check if user exists
    public static boolean userExists(String identifier) {
        String query = "SELECT id FROM users WHERE email = ? OR username = ?";
        
        try (Connection conn = MysqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, identifier);
            pstmt.setString(2, identifier);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Generate and store OTP - NEW NAME for your code
    public static boolean saveOtp(String identifier, String otp) {
        Timestamp expiryTime = new Timestamp(System.currentTimeMillis() + 5 * 60 * 1000);
        
        String query = "UPDATE users SET otp_code = ?, otp_expiry = ? WHERE email = ? OR username = ?";
        
        try (Connection conn = MysqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, otp);
            pstmt.setTimestamp(2, expiryTime);
            pstmt.setString(3, identifier);
            pstmt.setString(4, identifier);
            
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Also keep the old method name for compatibility
    public static boolean generateOTPForUser(String identifier) {
        String otp = OTPUtil.generateOTP();
        return saveOtp(identifier, otp);
    }
    
    // Verify OTP - NEW NAME for your code
    public static boolean verifyOtp(String identifier, String enteredOTP) {
        String query = "SELECT otp_code, otp_expiry FROM users WHERE email = ? OR username = ?";
        
        try (Connection conn = MysqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, identifier);
            pstmt.setString(2, identifier);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String storedOTP = rs.getString("otp_code");
                Timestamp expiryTime = rs.getTimestamp("otp_expiry");
                
                if (storedOTP != null && storedOTP.equals(enteredOTP)) {
                    if (expiryTime != null && expiryTime.after(new Timestamp(System.currentTimeMillis()))) {
                        return true; // OTP is valid
                    }
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    // Also keep the old method name
    public static boolean verifyOTP(String identifier, String enteredOTP) {
        return verifyOtp(identifier, enteredOTP);
    }
    
    // Reset password
    public static boolean resetPassword(String identifier, String newPassword) {
        String query = "UPDATE users SET password = ?, otp_code = NULL, otp_expiry = NULL WHERE email = ? OR username = ?";
        
        try (Connection conn = MysqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, newPassword);
            pstmt.setString(2, identifier);
            pstmt.setString(3, identifier);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Get user's email (optional helper)
    public static String getUserEmail(String identifier) {
        String query = "SELECT email FROM users WHERE email = ? OR username = ?";
        
        try (Connection conn = MysqlConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, identifier);
            pstmt.setString(2, identifier);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getString("email");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}