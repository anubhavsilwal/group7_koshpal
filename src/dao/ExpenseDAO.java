/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.Mysqlconnection;
import model.Expense;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ExpenseDAO {

    public static boolean saveExpense(Expense expense) {
        try {
            Connection con = Mysqlconnection.getconnection();

           String sql = "INSERT INTO expenses (name, category, amount, image_path) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, expense.getName());
            ps.setString(2, expense.getCategory());
            ps.setDouble(3, expense.getAmount());
            ps.setString(4, expense.getImagePath());

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
    
    