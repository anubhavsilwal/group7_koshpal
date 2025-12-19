/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

   import controller.ForgotPasswordController;

public class TestOtpSystem {
    public static void main(String[] args) {
        System.out.println("=== Testing Forgot Password System ===\n");
        
        // Use a test email (you can change this)
        String testEmail = "user@example.com";
        
        // ========== TEST 1: REQUEST OTP ==========
        System.out.println("1. Testing OTP Request...");
        String result = ForgotPasswordController.requestOtp(testEmail);
        System.out.println("   Result: " + result);
        
        // IMPORTANT: Check your console! You'll see the OTP printed
        // Example: "OTP for user@example.com: 123456"
        
        // ========== TEST 2: VERIFY OTP ==========
        System.out.println("\n2. Testing OTP Verification...");
        System.out.println("   Enter the OTP shown above: ");
        
        // For testing, let's pretend OTP is "123456" 
        // You need to change this to the actual OTP from console!
        String testOtp = "123456"; // ⬅️ CHANGE THIS to actual OTP from console
        
        boolean verified = ForgotPasswordController.verifyOtp(testEmail, testOtp);
        System.out.println("   Email: " + testEmail);
        System.out.println("   OTP entered: " + testOtp);
        System.out.println("   Verified: " + (verified ? "✅ SUCCESS" : "❌ FAILED"));
        
        // ========== TEST 3: RESET PASSWORD ==========
        System.out.println("\n3. Testing Password Reset...");
        
        String newPassword = "MyNewPassword123";
        String confirmPassword = "MyNewPassword123";
        
        boolean resetSuccess = ForgotPasswordController.resetPassword(
            testEmail, 
            newPassword, 
            confirmPassword
        );
        
        System.out.println("   New Password: " + newPassword);
        System.out.println("   Confirm Password: " + confirmPassword);
        System.out.println("   Reset Status: " + (resetSuccess ? "✅ SUCCESS" : "❌ FAILED"));
        
        System.out.println("\n=== Test Complete ===");
    }
} 

