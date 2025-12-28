/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author samee
 */

import java.util.ArrayList;
import java.util.List;

public class FinancialData {
    
    // Monthly financial summary
    public static class MonthlySummary {
        private String month;
        private double income;
        private double expenses;
        
        public MonthlySummary(String month, double income, double expenses) {
            this.month = month;
            this.income = income;
            this.expenses = expenses;
        }
        
        // Getters
        public String getMonth() { return month; }
        public double getIncome() { return income; }
        public double getExpenses() { return expenses; }
        public double getNetCashflow() { return income - expenses; }
    }
    
    // Category breakdown for pie charts
    public static class CategoryBreakdown {
        private String name;
        private double amount;
        
        public CategoryBreakdown(String name, double amount) {
            this.name = name;
            this.amount = amount;
        }
        
        // Getters
        public String getName() { return name; }
        public double getAmount() { return amount; }
    }
}