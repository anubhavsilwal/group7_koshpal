/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ACER
 */
package controller;

import dao.OtpDAO;
import model.FP;

public class ForgotPasswordController {

    public static boolean sendOtp(FP fp) {
        String otp = generateOtp();
        fp.setOtp(otp);
        return OtpDAO.saveOtp(fp);
    }

    public static boolean verifyOtp(FP fp) {
        return OtpDAO.checkOtp(fp);
    }

    public static boolean resetPassword(FP fp) {
        return OtpDAO.resetPassword(fp);
    }

    private static String generateOtp() {
        return String.valueOf((int)(Math.random() * 900000) + 100000);
    }
}
