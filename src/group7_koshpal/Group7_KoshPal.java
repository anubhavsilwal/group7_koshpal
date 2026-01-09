package group7_koshpal;

/**

 *

 * @author User
 * 
 */


import view.login;
import controller.loginController;
import controller.MainAppController;
import javax.swing.SwingUtilities;

public class Group7_KoshPal {
    private static MainAppController mainAppController;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Start with login page
            login loginView = new login();
            mainAppController = new MainAppController();
            
            // Create login controller with callback to main app
            try {
                loginController lc = new loginController(loginView, mainAppController);
                lc.open();
            } catch (Exception e) {
                // Fallback if constructor fails
                System.out.println("Using fallback login controller: " + e.getMessage());
                loginController lc = new loginController(loginView);
                lc.open();
            }
        });
    }
    
    public static MainAppController getMainAppController() {
        return mainAppController;
    }
}