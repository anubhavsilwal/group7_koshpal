/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group7_koshpal;

import view.inventory;
import view.analytics;
import controller.InventoryController;
import controller.AnalyticsController;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== STARTING APPLICATION ===");
        SwingUtilities.invokeLater(() -> {
            System.out.println("1. Creating inventory view...");
            inventory inventoryView = new inventory();
            
            System.out.println("2. Creating inventory controller...");
            InventoryController inventoryController = new InventoryController(inventoryView);
            
            System.out.println("3. Opening inventory window...");
            inventoryController.open();
            
            System.out.println("4. Application started!");
            
            // To open analytics directly for testing (UNCOMMENT THIS LINE):
            openAnalyticsWindow();
        });
    }
    
    // Method to open analytics window
    private static void openAnalyticsWindow() {
        System.out.println("5. Opening analytics window...");
        analytics analyticsView = new analytics();
        new AnalyticsController(analyticsView);
        analyticsView.setVisible(true);
        System.out.println("6. Analytics window opened!");
    }
}