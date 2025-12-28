/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ExpenseDAO;
import model.Expense;

public class ExpenseController {

    public static boolean addExpense(Expense expense) {
        return ExpenseDAO.saveExpense(expense);
    }
}
