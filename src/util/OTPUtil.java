/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Random;

public class OTPUtil {
    
    public static String generateOTP() {
        // Generate 6-digit OTP (100000 to 999999)
        Random random = new Random();
        int otpNumber = 100000 + random.nextInt(900000);
        return String.valueOf(otpNumber);
    }
    
    // Alternative simple method
    public static String generateSimpleOTP() {
        return String.valueOf((int)(Math.random() * 900000) + 100000);
    }
}
