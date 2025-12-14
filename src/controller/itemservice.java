/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.itemDAO;
import model.Item;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author samee
 */
public class itemservice {
    private final itemDAO itemDao;
    
    public itemservice(){
        this.itemDao= new itemDAO();
        //QN if itemDAO itemdao = new itemDAO same?
    }
    
    public int addItem(String itemName, String category, double value, 
            String status, String imagePath) {
        try {
            // Create model object
            Item newItem = new Item(0, itemName, category,
                    value, status, imagePath);
            
            // Call DAO
            int itemId = itemDao.insertItem(newItem);
            
            if (itemId > 0) {
                System.out.println("Item added successfully with ID: " + itemId);
                return itemId;
            }
        } catch (SQLException e) {
            System.err.println("Error adding item: " + e.getMessage());
        }
        return -1;
    }
    
    //get all items for a specific user
    public List<Item> getItemsByUser(int userId) {
        try {
            return itemDao.getItemsByUserId(userId);
        } catch (Exception e) {
            System.err.println("Error fetching items: " + e.getMessage());
            return List.of(); // Return empty list instead of null
        }
    }
    //update item
    public boolean updateItemStatus(int itemId, String newStatus, int userId) {
        try {
            // Get all items for the user
            List<Item> allItems = itemDao.getItemsByUserId(userId);
            
            // Find the specific item
            Item itemToUpdate = null;
            for (Item item : allItems) {
                if (item.getItemId() == itemId) {
                    itemToUpdate = item;
                    break;
                }
            }
            
            if (itemToUpdate != null) {
                itemToUpdate.setStatus(newStatus);
                return itemDao.updateItem(itemToUpdate);
            }
        } catch (Exception e) {
            System.err.println("Error updating status: " + e.getMessage());
        }
        return false;
    }
    
    public boolean deleteItem(int itemId) {
        try {
            return itemDao.deleteItemById(itemId);
        } catch (SQLException e) {
            System.err.println("Error deleting item: " + e.getMessage());
            return false;
        }
    }
    
    public List<Item> searchItems(int userId, String searchTerm) {
        try {
            return itemDao.searchItems(userId, searchTerm);
        } catch (SQLException e) {
            System.err.println("Error searching items: " + e.getMessage());
            return List.of();
        }
    }
    


    
    
    public boolean validateItemData(String itemName, double value) {
        // Business rules
        if (itemName == null || itemName.trim().isEmpty()) {
            return false;
        }
        if (value <= 0) {
            return false;
        }
        if (itemName.length() > 100) {
            return false;
        }
        return true;
    }
    
    
    public double calculateTotalInventoryValue(int userId) {
        try {
            List<Item> items = itemDao.getItemsByUserId(userId);
            double total = 0.0;
            for (Item item : items) {
                total += item.getValue();
            }
            return total;
        } catch (Exception e) {
            System.err.println("Error calculating total value: " + e.getMessage());
            return 0.0;
        }
    }
    
    public boolean updateItem(Item item) {
        return itemDao.updateItem(item);
    }
    
    
    public int getItemCount(int userId) {
        List<Item> items = getItemsByUser(userId);
        return items.size();
    }

    public int getOnLoanCount(int userId) {
        List<Item> items = getItemsByUser(userId);
        int count = 0;
        for (Item item : items) {
            if ("On Loan".equals(item.getStatus())) {
                count++;
            }
        }
        return count;
    }
   
   public String formatCurrency(double value) {
        return String.format("$%.2f", value);
    }
    
    public Item getItemById(int itemId, int userId) {
        List<Item> items = getItemsByUser(userId);
        for (Item item : items) {
            if (item.getItemId() == itemId) {
                return item;
            }
        }
        return null;
    }
    
    
    public boolean validateAllItemFields(String itemName, String category, 
            double value, String status) {
        if (!validateItemData(itemName, value)) {
            return false;
        }
        if (category == null || category.trim().isEmpty()) {
            return false;
        }
        if (status == null || status.trim().isEmpty()) {
            return false;
        }
        // Validate status is one of allowed values
        if (!status.equals("Available") && !status.equals("Sold") && !status.equals("On Loan")) {
            return false;
        }
        return true;
    }
}
   
