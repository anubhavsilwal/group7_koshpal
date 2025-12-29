/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// In dao/OtpDAO.java
package dao;

import database.Mysqlconnection;
import java.sql.*;
import model.FP;

public class OtpDAO {

    // Save OTP
    public static boolean saveOtp(FP fp) {
        try {
            Connection con = Mysqlconnection.getconnection();
            String sql = "UPDATE users SET otp = ? WHERE email = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, fp.getOtp());
            ps.setString(2, fp.getEmail());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Check OTP
    public static boolean checkOtp(FP fp) {
        try {
            Connection con = Mysqlconnection.getconnection();
            String sql = "SELECT * FROM users WHERE email = ? AND otp = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, fp.getEmail());
            ps.setString(2, fp.getOtp());

            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Reset Password (MISSING BEFORE)
    public static boolean resetPassword(FP fp) {
        try {
            Connection con = Mysqlconnection.getconnection();
            String sql = "UPDATE users SET password = ?, otp = NULL WHERE email = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, fp.getPassword());
            ps.setString(2, fp.getEmail());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

