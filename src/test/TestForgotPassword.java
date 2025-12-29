package test;

import controller.ForgotPasswordController;
import model.FP;

public class TestForgotPassword {

    public static void main(String[] args) {

 
           
FP fp = new FP();
fp.setEmail("test@gmail.com");
boolean sent = ForgotPasswordController.sendOtp(fp);
System.out.println("OTP Sent: " + sent);



fp.setOtp(fp.getOtp());
boolean verified = ForgotPasswordController.verifyOtp(fp);
System.out.println("OTP Verified: " + verified);

     
        fp.setPassword("newpassword123");
        boolean reset = ForgotPasswordController.resetPassword(fp);
        System.out.println("Password Reset: " + reset);
    }
}
