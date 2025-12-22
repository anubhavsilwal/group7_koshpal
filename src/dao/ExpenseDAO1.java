/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ACER
 */
import database.Mysqlconnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Expense1;

public class ExpenseDAO1 {

    public static boolean addExpense(Expense1 exp) {

        boolean result = false;

        try {
            Connection con = Mysqlconnection.getConnection();

            String sql = "INSERT INTO expenses (category, expense_type, amount, image_path) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, exp.getCategory());
            ps.setString(2, exp.getExpenseType());
            ps.setDouble(3, exp.getAmount());
            ps.setString(4, exp.getImagePath());

            result = ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
