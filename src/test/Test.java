/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

/**
 *
 * @author ACER
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("Testing OTP: " + util.OTPUtil.generateOTP());
        
        // Test database
        dao.UserDAO dao = new dao.UserDAO();
        boolean exists = dao.userExists("test@example.com");
        System.out.println("User exists: " + exists);
    }
}
