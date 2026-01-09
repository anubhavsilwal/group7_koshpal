/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author salmanansari.81
 */
package main;

import view.lending;
import controller.LendingController;
import database.Mysqlconnection;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting KoshPal Application...");
        
        // Initialize database connection
        Mysqlconnection db = new Mysqlconnection();
        Connection connection = db.openConnection();
        
        if (connection == null) {
            System.err.println("❌ Failed to connect to database!");
            JOptionPane.showMessageDialog(null, 
                "Cannot connect to database!\nPlease check:\n1. MySQL is running\n2. Database 'koshpal_data' exists\n3. Username/password is correct", 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        System.out.println("✅ Database connection established!");
        
        // Create and display the main frame
        java.awt.EventQueue.invokeLater(() -> {
            try {
                lending frame = new lending();
                LendingController controller = new LendingController(frame, connection);
                frame.setController(controller);
                frame.setVisible(true);
                System.out.println("✅ Application started!");
            } catch (Exception e) {
                System.err.println("❌ Error: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}
    

