/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;


import model.Item;
import view.inventory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import view.cardspanel;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.io.File;
import javax.swing.JFileChooser;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import view.analytics;

public class InventoryController {
    private final inventory inventoryView;
    private final itemservice itemService;
    private int currentUserId;
    
    public InventoryController(inventory inventoryView) {
        this.inventoryView = inventoryView;
        this.itemService = new itemservice();
        
        try {    
            itemService.setCurrentUserId(currentUserId);
            
            inventoryView.setAddItemListener(new AddItemListener());
            inventoryView.setSearchListener(new SearchListener());
            inventoryView.setDashboardListener(new DashboardListener());
            inventoryView.setInventoryListener(new InventoryListener());
            inventoryView.setDocumentsListener(new DocumentsListener());
            inventoryView.setLendingListener(new LendingListener());
            inventoryView.setGoalsListener(new GoalsListener());
            inventoryView.setFinancialListener(new FinancialListener());
            
            System.out.println("Controller setup complete");
        } catch (Exception e) {
            System.err.println("ERROR in controller constructor: " + e);
            e.printStackTrace();
        }
    }
    
    public void open() {
        inventoryView.setVisible(true);
        loadInitialData();
    }
    
    public void close() {
        inventoryView.dispose();
    }
    
    private void loadInitialData() {
        refreshDashboard();
        loadAllItems();
    }
    
    private void refreshDashboard() {
        int totalItems = itemService.getItemCount(currentUserId);
        inventoryView.getTotalItemsLabel().setText(String.valueOf(totalItems));
        
        double totalValue = itemService.calculateTotalInventoryValue(currentUserId);
        inventoryView.getTotalValueLabel().setText(itemService.formatCurrency(totalValue));
        
        int onLoanCount = itemService.getOnLoanCount(currentUserId);
        inventoryView.getOnLoanLabel().setText(String.valueOf(onLoanCount));
    }
    
    public void setCurrentUserId(int userId) {
        this.currentUserId = userId;
        if (itemService != null) {
            itemService.setCurrentUserId(userId);
        }
    }
    
    private void loadAllItems() {
        // Create a panel with GridLayout (3 columns, variable rows)
        JPanel cardsContainer = new JPanel(new GridLayout(0, 3, 20, 20)); // 0=infinite rows, 3 columns, 20px gaps
        
        // Set the panel as viewport
        inventoryView.getItemsScrollPane().setViewportView(cardsContainer);
        
        // Clear existing cards
        cardsContainer.removeAll();
        
        List<Item> items = itemService.getItemsByUser(currentUserId);
        
        if (items.isEmpty()) {
            JLabel noItemsLabel = new JLabel("No items found. Click 'Add item' to get started!");
            noItemsLabel.setFont(new java.awt.Font("Arial", java.awt.Font.ITALIC, 16));
            noItemsLabel.setHorizontalAlignment(SwingConstants.CENTER);
            cardsContainer.add(noItemsLabel);
        } else {
            // Create a cardspanel for each item - GridLayout will arrange them
            for (Item item : items) {
                cardspanel card = new cardspanel(item);
                
                card.getEditButton().addActionListener(e -> handleItemEdit(item.getItemId()));
                card.getRemoveButton().addActionListener(e -> handleItemRemove(item.getItemId()));
                
                cardsContainer.add(card);
            }
        }
        
        // Refresh
        cardsContainer.revalidate();
        cardsContainer.repaint();
    }
    
    class AddItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            openAddItemDialog();
        }
    }
    
    class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchTerm = inventoryView.getSearchField().getText();
            if (searchTerm.equals("Search by name or category")) {
                searchTerm = "";
            }
            
            if (searchTerm.isEmpty()) {
                loadAllItems();
            } else {
                performSearch(searchTerm);
            }
        }
    }
    
    class DashboardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Open Dashboard window
            view.Maindash dashboardView = new view.Maindash();
            dashboardView.setVisible(true);
            inventoryView.setVisible(false); // Hide inventory, don't dispose
        }
    }
    
    class InventoryListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Already in inventory view
            loadAllItems();
            refreshDashboard();
        }
    }
    
    class DocumentsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Open Documents window
            view.documents docsView = new view.documents();
            docsView.setVisible(true);
            inventoryView.setVisible(false);
        }
    }
    
    class LendingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Open Lending window
            view.lending lendingView = new view.lending();
            lendingView.setVisible(true);
            inventoryView.setVisible(false);
        }
    }
    
    class GoalsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(inventoryView, "Goals feature coming soon!");
            // If you have a goals view, uncomment below:
            // view.goals goalsView = new view.goals();
            // goalsView.setVisible(true);
            // inventoryView.setVisible(false);
        }
    }
    
    class FinancialListener implements ActionListener {
        @Override 
        public void actionPerformed(ActionEvent e) {
            // Open analytics window
            analytics analyticsView = new analytics();
            new AnalyticsController(analyticsView);
            analyticsView.setVisible(true);
            inventoryView.setVisible(false);
        }
    }
    
    // ========== ITEM OPERATION METHODS ==========
    
    private void openAddItemDialog() {
        String itemName = JOptionPane.showInputDialog(inventoryView, "Enter item name:");
        if (itemName == null || itemName.trim().isEmpty()) return;
        
        // FIX 1: Fixed category dropdown
        String[] categories = {"Electronics", "Furniture", "Books", "Clothing", "Other"};
        String category = (String) JOptionPane.showInputDialog(inventoryView, 
            "Select category:", "Category", 
            JOptionPane.QUESTION_MESSAGE, null, categories, categories[0]);
        if (category == null) return;
        
        // NEW: Image selection with Browse button
        JPanel imagePanel = new JPanel(new BorderLayout(5, 5));
        JTextField imagePathField = new JTextField(20);
        JButton browseButton = new JButton("Browse...");
        
        browseButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select Item Image");
            
            // Filter for image files
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Image files", "jpg", "jpeg", "png", "gif", "bmp"));
            
            int result = fileChooser.showOpenDialog(inventoryView);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                imagePathField.setText(selectedFile.getAbsolutePath());
            }
        });
        
        JPanel fieldPanel = new JPanel(new BorderLayout(5, 5));
        fieldPanel.add(new JLabel("Image (optional):"), BorderLayout.WEST);
        fieldPanel.add(imagePathField, BorderLayout.CENTER);
        fieldPanel.add(browseButton, BorderLayout.EAST);
        imagePanel.add(fieldPanel, BorderLayout.NORTH);
        imagePanel.add(new JLabel("Leave empty for no image"), BorderLayout.SOUTH);
        
        int imageResult = JOptionPane.showConfirmDialog(inventoryView, imagePanel,
            "Select Image", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        String imagePath = "";
        if (imageResult == JOptionPane.OK_OPTION) {
            imagePath = imagePathField.getText().trim();
        }
        
        String valueStr = JOptionPane.showInputDialog(inventoryView, "Enter value:");
        if (valueStr == null || valueStr.trim().isEmpty()) return;
        
        // FIX 3: Status dropdown
        String[] statuses = {"Available", "On Loan", "Sold"};
        String status = (String) JOptionPane.showInputDialog(inventoryView, 
            "Select status:", "Status", 
            JOptionPane.QUESTION_MESSAGE, null, statuses, statuses[0]);
        if (status == null) return;
        
        try {
            double value = Double.parseDouble(valueStr);
            
            int itemId = itemService.addItem(itemName, category, value, status, imagePath);
            if (itemId > 0) {
                JOptionPane.showMessageDialog(inventoryView, "Item added successfully!");
                loadAllItems();
                refreshDashboard();
            } else {
                JOptionPane.showMessageDialog(inventoryView, "Failed to add item!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(inventoryView, "Please enter a valid number!");
        }
    }
    
    private void performSearch(String searchTerm) {
        // Create panel with GridLayout for search results too
        JPanel cardsContainer = new JPanel(new GridLayout(0, 3, 20, 20));
        inventoryView.getItemsScrollPane().setViewportView(cardsContainer);
        
        List<Item> results = itemService.searchItems(currentUserId, searchTerm);
        
        if (results.isEmpty()) {
            JLabel noResultsLabel = new JLabel("No items found for: \"" + searchTerm + "\"");
            noResultsLabel.setFont(new java.awt.Font("Arial", java.awt.Font.ITALIC, 16));
            noResultsLabel.setHorizontalAlignment(SwingConstants.CENTER);
            cardsContainer.add(noResultsLabel);
        } else {
            for (Item item : results) {
                cardspanel card = new cardspanel(item);
                
                card.getEditButton().addActionListener(e -> handleItemEdit(item.getItemId()));
                card.getRemoveButton().addActionListener(e -> handleItemRemove(item.getItemId()));
                
                cardsContainer.add(card);
            }
        }
        
        cardsContainer.revalidate();
        cardsContainer.repaint();
    }
    
    public void handleItemEdit(int itemId) {
        Item itemToEdit = itemService.getItemById(itemId);
        
        if (itemToEdit != null) {
            String newName = JOptionPane.showInputDialog(inventoryView, 
                "Edit Item Name:", itemToEdit.getItemName());
            if (newName == null || newName.trim().isEmpty()) return;
            
            // FIX 1: Category dropdown
            String[] categories = {"Electronics", "Furniture", "Books", "Clothing", "Other"};
            String newCategory = (String) JOptionPane.showInputDialog(inventoryView, 
                "Select category:", "Category", 
                JOptionPane.QUESTION_MESSAGE, null, categories, itemToEdit.getCategory());
            if (newCategory == null) return;
            
            // NEW: Image selection with Browse button for edit
            JPanel imagePanel = new JPanel(new BorderLayout(5, 5));
            JTextField imagePathField = new JTextField(itemToEdit.getImagePath(), 20);
            JButton browseButton = new JButton("Browse...");
            
            browseButton.addActionListener(e -> {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Select Item Image");
                fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                    "Image files", "jpg", "jpeg", "png", "gif", "bmp"));
                
                int result = fileChooser.showOpenDialog(inventoryView);
                if (result == JFileChooser.APPROVE_OPTION) {
                    imagePathField.setText(fileChooser.getSelectedFile().getAbsolutePath());
                }
            });
            
            JPanel fieldPanel = new JPanel(new BorderLayout(5, 5));
            fieldPanel.add(new JLabel("Image:"), BorderLayout.WEST);
            fieldPanel.add(imagePathField, BorderLayout.CENTER);
            fieldPanel.add(browseButton, BorderLayout.EAST);
            imagePanel.add(fieldPanel, BorderLayout.NORTH);
            imagePanel.add(new JLabel("Clear field to remove image"), BorderLayout.SOUTH);
            
            int imageResult = JOptionPane.showConfirmDialog(inventoryView, imagePanel,
                "Select Image", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
            String newImagePath = itemToEdit.getImagePath();
            if (imageResult == JOptionPane.OK_OPTION) {
                newImagePath = imagePathField.getText().trim();
            }
            
            // FIX 3: Status dropdown
            String[] statuses = {"Available", "On Loan", "Sold"};
            String newStatus = (String) JOptionPane.showInputDialog(inventoryView, 
                "Select status:", "Status", 
                JOptionPane.QUESTION_MESSAGE, null, statuses, itemToEdit.getStatus());
            if (newStatus == null) return;
            
            String newValueStr = JOptionPane.showInputDialog(inventoryView, 
                "Edit Value:", String.valueOf(itemToEdit.getValue()));
            if (newValueStr == null || newValueStr.trim().isEmpty()) return;
            
            try {
                double newValue = Double.parseDouble(newValueStr);
                Item updatedItem = new Item(
                    itemId, newName, newCategory, newValue, newStatus, newImagePath
                );
                
                boolean success = itemService.updateItem(updatedItem);
                if (success) {
                    JOptionPane.showMessageDialog(inventoryView, "Item updated successfully!");
                    loadAllItems();
                    refreshDashboard();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(inventoryView, "Please enter a valid number!");
            }
        }
    }
    
    public void handleItemRemove(int itemId) {
        int confirm = JOptionPane.showConfirmDialog(
            inventoryView,
            "Are you sure you want to remove this item?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = itemService.deleteItem(itemId);
            if (success) {
                JOptionPane.showMessageDialog(inventoryView, "Item removed successfully!");
                loadAllItems();
                refreshDashboard();
            } else {
                JOptionPane.showMessageDialog(inventoryView, "Failed to remove item!");
            }
        }
    }
}