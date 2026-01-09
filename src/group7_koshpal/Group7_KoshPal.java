package group7_koshpal;

/**

 *

 * @author User
 * 
 */


import view.login;
import controller.loginController;

 /**

     * @param args the command line arguments

     */


public class Group7_KoshPal {
    public static void main(String[] args) {
        // Start with login page
        login loginView = new login();
        loginController lc = new loginController(loginView);
        lc.open();
    } 
}