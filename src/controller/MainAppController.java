/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import view.inventory;
import view.Maindash;
import view.analytics;
import view.lending;
import view.documents;
import view.login;
import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainAppController {
    private JFrame mainFrame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private int currentUserId;
    
    // Views
    private inventory inventoryView;
    private Maindash dashboardView;
    private analytics analyticsView;
    private lending lendingView;
    private documents documentsView;
    private login loginView; // Add login view reference
    
    // Controllers
    private InventoryController inventoryController;
    private AnalyticsController analyticsController;
    
    public MainAppController() {
        initializeMainFrame();
    }
    
    private void initializeMainFrame() {
        mainFrame = new JFrame("Inventory Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1200, 800);
        mainFrame.setLocationRelativeTo(null);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainFrame.add(mainPanel);
    }
    
    public void switchToInventory(int userId) {
        this.currentUserId = userId;
        
        if (inventoryView == null) {
            inventoryView = new inventory();
            inventoryController = new InventoryController(inventoryView);
            inventoryController.setCurrentUserId(userId);
            mainPanel.add(inventoryView, "INVENTORY");
        } else {
            inventoryController.setCurrentUserId(userId);
        }
        
        // Show inventory
        cardLayout.show(mainPanel, "INVENTORY");
        mainFrame.setVisible(true);
    }
    
    public void switchToDashboard() {
        if (dashboardView == null) {
            dashboardView = new Maindash();
            dashboardView.setUserId(currentUserId);
            mainPanel.add(dashboardView, "DASHBOARD");
        }
        cardLayout.show(mainPanel, "DASHBOARD");
    }
    
    public void switchToAnalytics() {
        if (analyticsView == null) {
            analyticsView = new analytics();
            analyticsController = new AnalyticsController(analyticsView);
            mainPanel.add(analyticsView, "ANALYTICS");
        }
        cardLayout.show(mainPanel, "ANALYTICS");
    }
    
    public void switchToLending() {
        if (lendingView == null) {
            lendingView = new lending();
            mainPanel.add(lendingView, "LENDING");
        }
        cardLayout.show(mainPanel, "LENDING");
    }
    
    public void switchToDocuments() {
        if (documentsView == null) {
            documentsView = new documents();
            mainPanel.add(documentsView, "DOCUMENTS");
        }
        cardLayout.show(mainPanel, "DOCUMENTS");
    }
    
    // Add this method if you want to go back to login
    public void switchToLogin() {
        if (loginView == null) {
            loginView = new login();
            mainPanel.add(loginView, "LOGIN");
        }
        cardLayout.show(mainPanel, "LOGIN");
    }
    
    public JFrame getMainFrame() {
        return mainFrame;
    }
    
    public int getCurrentUserId() {
        return currentUserId;
    }
}