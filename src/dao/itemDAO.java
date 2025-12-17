
/*

 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.Item;
import database.Database;
import database.Mysqlconnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author samee
 */
public class itemDAO {
    Mysqlconnection mysql = new Mysqlconnection();
    private int userId;
    
        public void setUserId(int userId) {
        this.userId = userId;
    }
    
    //create: insertitem
    public int insertItem(Item item,int userId) throws SQLException{
        Connection conn= mysql.openConnection();
        String sql = "INSERT INTO items (item_name, category, value, status, "
                + "image_path,user_id) VALUES (?, ?, ?, ?, ?,?)";
        
            try (PreparedStatement pstm = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS)) {
            pstm.setString(1, item.getItemName());    //  Set first ? to item_name
            pstm.setString(2, item.getCategory());    //  Set second ? to category
             pstm.setBigDecimal(3, java.math.BigDecimal.valueOf(item.getValue()));       //  Set third ? to value
            pstm.setString(4, item.getStatus());      //  Set fourth ? to status
            pstm.setString(5, item.getImagePath());   //  Set fifth ? to image_path
            pstm.setInt(6, userId);
            pstm.executeUpdate();
 
           ResultSet rs = pstm.getGeneratedKeys();
           if(rs.next()){
               return rs.getInt(1);
           }
           return -1;   
        } finally{
                if(conn !=null){
                    conn.close();
                }
            }
    }
    
    
    //code for getting user items
    public List<Item> getItemsByUserId(int userId) {
    List<Item> items = new ArrayList<>();
    //<Item> name of class and only variables decleared in it can be used as input.
    
    Connection conn = mysql.openConnection();
    String sql = "SELECT * FROM items WHERE user_id = ?";
    
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, userId);  // 🎯 Set the user_id parameter
        
        ResultSet rs = pstmt.executeQuery();
        
        while (rs.next()) {
            Item item = new Item(
                rs.getInt("item_id"),       //  Get item_id from result
                rs.getString("item_name"),  // Get item_name from result
                rs.getString("category"),   //  Get category from result
                rs.getBigDecimal("value").doubleValue(),      //  Get value from result
                rs.getString("status"),     //  Get status from result
                rs.getString("image_path")  //  Get image_path from result
            );
            items.add(item);  // Add item to list
        }
        
    } catch (Exception e) {
        System.out.println("Error fetching items: " + e);
    } finally {
        mysql.closeConnection(conn);  
    }
    
    return items;  
}
    //code for updating user items.
    public boolean updateItem(Item item) {
    Connection conn = mysql.openConnection();
    String sql = "UPDATE items SET item_name=?, category=?, value=?, status=?, "
            + "image_path=? WHERE item_id=? AND user_id=?";
    
    try (PreparedStatement pstm = conn.prepareStatement(sql)) {
        pstm.setString(1, item.getItemName());      
        pstm.setString(2, item.getCategory());      
        pstm.setBigDecimal(3, java.math.BigDecimal.valueOf(item.getValue()));       
        pstm.setString(4, item.getStatus());       
        pstm.setString(5, item.getImagePath());     
        pstm.setInt(6, item.getItemId()); 
        pstm.setInt(7, userId); 
        
        int rowsUpdated = pstm.executeUpdate();
        return rowsUpdated > 0;  // Returns true if 1+ rows were updated
        
    } catch (Exception e) {
        System.out.println("Error updating item: " + e);
        return false;
    } finally {
        mysql.closeConnection(conn);  // Always close connection
    }
}
    //code for deleting items
    public boolean deleteItemById(int itemId,int userId) throws SQLException{
        Connection conn = mysql.openConnection();
        String sql = "delete from items where item_id=? AND user_id=?";
        
        try (PreparedStatement pstm = conn.prepareStatement(sql)){
            pstm.setInt(1,itemId); //set itemID parameter for deletion
            pstm.setInt(2, userId);
            
            int rowsDeleted = pstm.executeUpdate();
            return rowsDeleted >0;  //returns true if item was deleted
            
            
        }catch(Exception e){
            System.out.println("Error deleting item: "+ e);
            return false;
        }finally{
            mysql.closeConnection(conn);
        }
    }
    
    //code for searching
    public List<Item> searchItems(int userId,String searchTerm)
            throws SQLException {
    List<Item> items = new ArrayList<>();
    String sql = "SELECT * FROM items WHERE user_id = ? AND "
            + "(item_name LIKE ? OR category LIKE ?)";
    
    Connection conn = mysql.openConnection();
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, userId);
        pstmt.setString(2, "%" + searchTerm + "%");
        pstmt.setString(3, "%" + searchTerm + "%");
        
        try (ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Item item = new Item(
                    rs.getInt("item_id"),
                    rs.getString("item_name"),
                    rs.getString("category"),
                    rs.getBigDecimal("value").doubleValue(),
                    rs.getString("status"),
                    rs.getString("image_path")
                );
                items.add(item);
            }
        }
    } finally {
        if (conn != null) {
            conn.close();
        }
    }
    return items;
}
    
     public Item getItemById(int itemId, int userId) {
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM items WHERE item_id = ? AND user_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, itemId);
            pstmt.setInt(2, userId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Item(
                        rs.getInt("item_id"),
                        rs.getString("item_name"),
                        rs.getString("category"),
                        rs.getBigDecimal("value").doubleValue(),  // Handle DECIMAL
                        rs.getString("status"),
                        rs.getString("image_path")
                    );
                }
            }
        } catch (Exception e) {
            System.out.println("Error getting item by ID: " + e);
        } finally {
            mysql.closeConnection(conn);
        }
        return null;  // Item not found or error
    }

    }
    
    
    
