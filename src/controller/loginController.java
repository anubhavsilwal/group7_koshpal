package controller;

import view.login;
import dao.userDao;
import model.userModel;
import view.signup;
import controller.signupController;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class loginController {
    private final login loginView;
    private final userDao userdao = new userDao();
    private MainAppController mainAppController; // Add this field
    
    // Existing constructor
    public loginController(login loginView) {
        this.loginView = loginView;
        
        // Add action listeners
        loginView.addLoginListener(new LoginListener());
        loginView.addSignupListener(new SignupListener());
    }
    
    // NEW constructor with MainAppController
    public loginController(login loginView, MainAppController mainAppController) {
        this.loginView = loginView;
        this.mainAppController = mainAppController;
        
        // Add action listeners
        loginView.addLoginListener(new LoginListener());
        loginView.addSignupListener(new SignupListener());
    }
    
    public void open() {
        this.loginView.setVisible(true);
    }
    
    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String email = loginView.getEmailField().getText();
                String password = loginView.getPasswordField().getText();
                
                // Validate input
                if (email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(loginView, "Please fill all fields");
                    return;
                }
                
                // Authenticate user
                userModel user = userdao.login(email, password);
                
                if (user != null) {
                    JOptionPane.showMessageDialog(loginView, "Login Successful!");
                    
                    // Close login window
                    loginView.dispose();
                    
                    // Open dashboard with user data
                    if (mainAppController != null) {
                        // Use MainAppController if available
                        mainAppController.switchToInventory(user.getId());
                    } else {
                        // Fallback to old method
                        openDashboard(user);
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(loginView, "Invalid email or password");
                }
                
            } catch (Exception ex) {
                System.out.println("Login error: " + ex.getMessage());
                JOptionPane.showMessageDialog(loginView, "Login failed. Please try again.");
            }
        }
    }
    
    class SignupListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("DEBUG: Signup button clicked - Opening signup page");
            
            // Close login window
            loginView.dispose();
            
            // Create and show signup window
            signup signupView = new signup();
            signupController sc = new signupController(signupView);
            sc.open();
            
            System.out.println("DEBUG: Signup window should be visible now");
        }
    }
    
    private void openDashboard(userModel user) {
        try {
            // Create dashboard
            view.Maindash dashboard = new view.Maindash();
            
            // Pass user info to dashboard
            dashboard.setUserId(user.getId());
            dashboard.setUsername(user.getUsername());
            
            // Make dashboard visible
            dashboard.setVisible(true);
            
        } catch (Exception ex) {
            System.out.println("Error opening dashboard: " + ex.getMessage());
            JOptionPane.showMessageDialog(loginView, "Error loading dashboard");
        }
    }
}