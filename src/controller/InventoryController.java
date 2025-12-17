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
/**
 *
 * @author samee
 */
public class InventoryController {
    private final inventory inventoryView;
    private final itemservice itemService;
    private int currentUserId=1;
    
public InventoryController(inventory inventoryView) {
        this.inventoryView = inventoryView;
        this.itemService = new itemservice();
    try{    
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
    public void open(){
        inventoryView.setVisible(true);
        loadInitialData();
    }
    
    public void close(){
        inventoryView.dispose();
    }
    
    private void loadInitialData(){
        refreshDashboard();
        loadAllItems();
    }
    
    private void refreshDashboard(){
        int totalItems = itemService.getItemCount(currentUserId);
        inventoryView.getTotalItemsLabel().setText(String.valueOf(totalItems));
        
        double totalValue = itemService.calculateTotalInventoryValue(currentUserId);
        inventoryView.getTotalValueLabel().setText(itemService.formatCurrency(totalValue));
        
        int onLoanCount = itemService.getOnLoanCount(currentUserId);
        inventoryView.getOnLoanLabel().setText(String.valueOf(onLoanCount));
    }
    
    
    private void loadAllItems(){
               // Get or create cards container
        JPanel cardsContainer;
        if (inventoryView.getItemsScrollPane().getViewport().getView() instanceof JPanel) {
            cardsContainer = (JPanel) inventoryView.getItemsScrollPane().getViewport().getView();
        } else {
            // Create new container if none exists
            cardsContainer = new JPanel();
            cardsContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
            inventoryView.getItemsScrollPane().setViewportView(cardsContainer);
        }
    
       // Clear existing cards
    cardsContainer.removeAll();
    
     List<Item> items = itemService.getItemsByUser(currentUserId);
    
    if (items.isEmpty()) {
        // Show message if no items
        JLabel noItemsLabel = new JLabel("No items found. Click 'Add item' to get started!");
        noItemsLabel.setFont(new java.awt.Font("Arial", java.awt.Font.ITALIC, 16));
        noItemsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cardsContainer.add(noItemsLabel);
    } else {
        // Create a cardspanel for each item
        for (Item item : items) {
            cardspanel card = new cardspanel(item);
            
   card.getEditButton().addActionListener
        (e -> handleItemEdit(item.getItemId()));
            card.getRemoveButton().addActionListener
        (e -> handleItemRemove(item.getItemId()));
            
            cardsContainer.add(card);
}
    }
    
 // Refresh the panel
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
        JOptionPane.showMessageDialog(inventoryView, "Dashboard feature coming soon!");
    }
}

class InventoryListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        loadAllItems();
        refreshDashboard();
    }
}

class DocumentsListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(inventoryView, "Documents feature coming soon!");
    }
}

class LendingListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(inventoryView, "Lending feature coming soon!");
    }
}

class GoalsListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(inventoryView, "Goals feature coming soon!");
    }
}

class FinancialListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(inventoryView, "Financial Analytics feature coming soon!");
    }
}

// ========== ITEM OPERATION METHODS ==========

private void openAddItemDialog() {
    String itemName = JOptionPane.showInputDialog(inventoryView, "Enter item name:");
    if (itemName == null || itemName.trim().isEmpty()) return;
    
    String category = JOptionPane.showInputDialog(inventoryView, "Enter category:");
    if (category == null || category.trim().isEmpty()) return;
    
    String valueStr = JOptionPane.showInputDialog(inventoryView, "Enter value:");
    if (valueStr == null || valueStr.trim().isEmpty()) return;
    
    try {
        double value = Double.parseDouble(valueStr);
        String status = "Available";
        String imagePath = "";
        
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
    JPanel cardsContainer = new JPanel();
    cardsContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
    inventoryView.getItemsScrollPane().setViewportView(cardsContainer);
    
    List<Item> results = itemService.searchItems(currentUserId, searchTerm);
    
    if (results.isEmpty()) {
        JLabel noResultsLabel = new JLabel("No items found for: \"" + searchTerm + "\"");
        noResultsLabel.setFont(new java.awt.Font("Arial", java.awt.Font.ITALIC, 16));
        noResultsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cardsContainer.add(noResultsLabel);
    } else {
        // ✅ FIXED: Add action listeners to search results
        for (Item item : results) {
            cardspanel card = new cardspanel(item);
            
            // Attach edit button listener
            card.getEditButton().addActionListener(e -> handleItemEdit(item.getItemId()));
            
            // Attach remove button listener  
            card.getRemoveButton().addActionListener(e -> handleItemRemove(item.getItemId()));
            
            cardsContainer.add(card);
        }
    }
    
    cardsContainer.revalidate();
    cardsContainer.repaint();
}
 public void handleItemEdit(int itemId) {
        // ✅ FIXED: Direct database lookup instead of loading all items
        Item itemToEdit = itemService.getItemById(itemId);
        
        if (itemToEdit != null) {
            // Create edit dialog
            String newName = JOptionPane.showInputDialog(inventoryView, 
                "Edit Item Name:", itemToEdit.getItemName());
            if (newName == null || newName.trim().isEmpty()) return;
            
            String newCategory = JOptionPane.showInputDialog(inventoryView, 
                "Edit Category:", itemToEdit.getCategory());
            if (newCategory == null || newCategory.trim().isEmpty()) return;
            
            String newValueStr = JOptionPane.showInputDialog(inventoryView, 
                "Edit Value:", String.valueOf(itemToEdit.getValue()));
            if (newValueStr == null || newValueStr.trim().isEmpty()) return;
            
            try {
                double newValue = Double.parseDouble(newValueStr);
                Item updatedItem = new Item(
                    itemId, 
                    newName, 
                    newCategory, 
                    newValue, 
                    itemToEdit.getStatus(), 
                    itemToEdit.getImagePath()
                );
                
                boolean success = itemService.updateItem(updatedItem);
                if (success) {
                    JOptionPane.showMessageDialog(inventoryView, "Item updated successfully!");
                    loadAllItems();  // Refresh the display
                    refreshDashboard();
                } else {
                    JOptionPane.showMessageDialog(inventoryView, "Failed to update item!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(inventoryView, "Please enter a valid number!");
            }
        } else {
            JOptionPane.showMessageDialog(inventoryView, "Item not found!");
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


