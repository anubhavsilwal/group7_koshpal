/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package group7_koshpal;
import view.inventory;
import controller.InventoryController;
import javax.swing.SwingUtilities;
/**
 *
 * @author samee
 */
public class Main {
        public static void main(String[] args) {
        System.out.println("=== STARTING APPLICATION ===");
        
        SwingUtilities.invokeLater(() -> {
            System.out.println("1. Creating inventory view...");
            inventory inventoryView = new inventory();
            System.out.println("2. Creating controller...");
            InventoryController controller = new InventoryController(inventoryView);
            System.out.println("3. Opening window...");
            controller.open();
            System.out.println("4. Application started!");
        });
    }
}

