/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author samee
 */


import model.FinancialData.MonthlySummary;
import model.FinancialData.CategoryBreakdown;
import database.Mysqlconnection;
import java.sql.*;
import java.util.*;

public class FinanceDAO {
    private Mysqlconnection mysql = new Mysqlconnection();
    
    // Get 6 months of financial data (same pattern as itemDAO)
    public List<MonthlySummary> getMonthlySummary() {
        List<MonthlySummary> list = new ArrayList<>();
        String query = "SELECT month, income, expenses FROM monthly_finances ORDER BY year, " +
                      "FIELD(month, 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun') LIMIT 6";
        
        Connection conn = mysql.openConnection();
        try {
            ResultSet rs = mysql.runQuery(conn, query);
            while (rs != null && rs.next()) {
                list.add(new MonthlySummary(
                    rs.getString("month"),
                    rs.getDouble("income"),
                    rs.getDouble("expenses")
                ));
            }
        } catch (Exception e) {
            System.err.println("Error in getMonthlySummary: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return list;
    }
    
    // Get expense breakdown for specific month
    public List<CategoryBreakdown> getExpenseBreakdown(String month, int year) {
        return getCategoryData("expense_categories", "category_name", month, year);
    }
    
    // Get income sources for specific month
    public List<CategoryBreakdown> getIncomeSources(String month, int year) {
        return getCategoryData("income_sources", "source_name", month, year);
    }
    
    // Generic method for category data (same pattern as itemDAO.searchItems)
    private List<CategoryBreakdown> getCategoryData(String table, String nameColumn, String month, int year) {
        List<CategoryBreakdown> list = new ArrayList<>();
        String query = "SELECT " + nameColumn + ", amount FROM " + table + 
                      " WHERE month = ? AND year = ?";
        
        Connection conn = mysql.openConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, month);
            pstmt.setInt(2, year);
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new CategoryBreakdown(
                    rs.getString(1),
                    rs.getDouble("amount")
                ));
            }
        } catch (Exception e) {
            System.err.println("Error in getCategoryData: " + e.getMessage());
        } finally {
            mysql.closeConnection(conn);
        }
        return list;
    }
}