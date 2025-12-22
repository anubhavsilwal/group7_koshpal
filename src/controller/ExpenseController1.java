/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author ACER
 */
import dao.ExpenseDAO1;
import model.Expense1;

public class ExpenseController1 {

    public boolean addExpense(String category, String type, double amount, String imagePath) {

        Expense1 exp = new Expense1();
        exp.setCategory(category);
        exp.setExpenseType(type);
        exp.setAmount(amount);
        exp.setImagePath(imagePath);

        return ExpenseDAO1.addExpense(exp);
    }
}

