/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ACER
 */

package test;

import controller.ForgotPasswordController;
import model.FP;

public class CorrectTest {
    
    public static void main(String[] args) {
        // Use the EXACT email from your database
        String correctEmail = "test@email.com"; // ← THIS IS THE EMAIL IN YOUR DATABASE
        
        System.out.println("=== TESTING FORGOT PASSWORD ===");
        System.out.println("Using email: " + correctEmail);
        System.out.println("From database: user_id=1, username=testuser\n");
        
        // Run complete test
        runCompleteTest(correctEmail);
    }
    
    private static void runCompleteTest(String email) {
        System.out.println("STEP 1: Sending OTP...");
        FP fp1 = new FP(email);
        boolean otpSent = ForgotPasswordController.sendOtp(fp1);
        
        if (otpSent) {
            System.out.println("✓ OTP sent successfully!");
            System.out.println("Generated OTP: " + fp1.getOtp());
            System.out.println("\n⚠️ IMPORTANT: Check database with:");
            System.out.println("SELECT * FROM users WHERE email = '" + email + "';");
            System.out.println("You should see the OTP in the 'otp' column.");
            
            // Wait for user to check database
            System.out.println("\nPress Enter after checking database...");
            try {
                System.in.read();
            } catch (Exception e) {}
            
            // STEP 2: Verify OTP
            System.out.println("\nSTEP 2: Verifying OTP...");
            System.out.println("Using OTP from database: " + fp1.getOtp());
            
            boolean otpVerified = ForgotPasswordController.verifyOtp(fp1);
            
            if (otpVerified) {
                System.out.println("✓ OTP verification successful!");
                
                // STEP 3: Reset Password
                System.out.println("\nSTEP 3: Resetting password...");
                String newPassword = "NewSecurePassword123";
                fp1.setPassword(newPassword);
                
                boolean passwordReset = ForgotPasswordController.resetPassword(fp1);
                
                if (passwordReset) {
                    System.out.println("✓ Password reset successful!");
                    System.out.println("New password: " + newPassword);
                    System.out.println("\nCheck database - OTP should be NULL now:");
                    System.out.println("SELECT otp FROM users WHERE email = '" + email + "';");
                } else {
                    System.out.println("✗ Password reset failed!");
                }
            } else {
                System.out.println("✗ OTP verification failed!");
                System.out.println("Check if OTP in database matches: " + fp1.getOtp());
            }
        } else {
            System.out.println("✗ Failed to send OTP!");
            System.out.println("Possible issues:");
            System.out.println("1. Email doesn't exist (but it should: " + email + ")");
            System.out.println("2. Database connection problem");
            System.out.println("3. SQL UPDATE failed");
        }
    }
}