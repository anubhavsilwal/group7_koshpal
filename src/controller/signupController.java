package controller;

import view.login;
import dao.userDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.userModel;
import view.signup;

/**
 *
 * @author anubhavsilwal
 */
public class signupController {
    private final userDao userdao =  new userDao();
    private final signup userView;
    
    
    public signupController(signup userView){
    System.out.println("=== SIGNUP CONTROLLER INITIALIZED ===");
    this.userView = userView;
    
    System.out.println("Adding listeners to buttons...");
    
    userView.AddUserListner(new SignUpListener());
    System.out.println("Signup listener added");
    
    userView.LoginButtonListener(new LoginListener());
    System.out.println("Login listener added");
    
    System.out.println("All listeners connected successfully");
}
    
   
    public void open(){
    System.out.println("DEBUG: signupController.open() called");
    this.userView.setVisible(true);  // This line MUST be here
    System.out.println("DEBUG: Window set to visible");
}

    public void close(){
        this.userView.dispose();
    }
    
    class LoginListener implements ActionListener {
@Override
        public void actionPerformed(ActionEvent ex) {
            login log = new login();
            loginController lc = new loginController(log);
            lc.open();
        }
    }
    
    
    
     class SignUpListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            System.out.println("=== SIGNUP PROCESS START ===");
            
            // Get values
            String username = userView.getUsernameField().getText();
            String email = userView.getEmailField().getText();
            String password = userView.getPasswordField().getText();
            
            // Check for placeholder text
            if ("Enter Username".equals(username)) username = "";
            if ("Enter Your Email".equals(email)) email = "";
            if ("Enter Password".equals(password)) password = "";
            
            System.out.println("RAW VALUES:");
            System.out.println("Username: '" + username + "'");
            System.out.println("Email: '" + email + "'");
            System.out.println("Password: '" + password + "'");
            
            // Validate
            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                System.out.println("ERROR: Empty fields!");
                JOptionPane.showMessageDialog(userView, "Please fill all fields");
                return;
            }
            
            // Create model - CORRECT ORDER
            System.out.println("\nCreating userModel with:");
            System.out.println("username = " + username);
            System.out.println("email = " + email);
            System.out.println("password = " + password);
            
            userModel usermodel = new userModel(username, email, password);
            
            // === ADD THE MISSING DATABASE LOGIC ===
            System.out.println("Checking if user exists...");
            boolean check = userdao.check(usermodel);
            
            if (check) {
                System.out.println("User already exists!");
                JOptionPane.showMessageDialog(userView, "Email or username already exists");
            } else {
                System.out.println("Creating new user in database...");
                userdao.signup(usermodel);
                JOptionPane.showMessageDialog(userView, "Account created successfully!");
                
                System.out.println("Attempting auto-login...");
                System.out.println("Auto-login email: " + email);
                System.out.println("Auto-login password: " + password);
                
                // Auto-login after successful signup
                userModel loggedInUser = userdao.login(email, password);
                
                if (loggedInUser != null) {
                    System.out.println("Auto-login SUCCESS! User ID: " + loggedInUser.getId());
                    // Close signup window
                    userView.dispose();
                    
                    // Open dashboard
                    openDashboard(loggedInUser);
                } else {
                    System.out.println("Auto-login FAILED! Opening login page instead.");
                    JOptionPane.showMessageDialog(userView, 
                        "Account created! Please login with your credentials.");
                    
                    // Open login page instead
                    openLoginPage();
                }
            }
            
        } catch (Exception ex) {
            System.out.println("Signup error: " + ex.getMessage());
            ex.printStackTrace();
            JOptionPane.showMessageDialog(userView, "Error creating account");
        }
    }
}
     
     
     private void openLoginPage() {
    try {
        // Close signup window
        userView.dispose();
        
        // Open login window
        login loginView = new login();
        loginController lc = new loginController(loginView);
        lc.open();
        
    } catch (Exception ex) {
        System.out.println("Error opening login page: " + ex.getMessage());
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
        JOptionPane.showMessageDialog(userView, "Error loading dashboard");
    }
   }
}
