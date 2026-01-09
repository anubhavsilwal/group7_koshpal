package controller;

import view.lending;
import model.Loan;
import dao.LoanDAO;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class LendingController {
    private lending view;
    private LoanDAO loanDAO;
    private int currentUserId = 1;
    
    public LendingController(lending view, Connection connection) {
        this.view = view;
        this.loanDAO = new LoanDAO(connection);
        
        try {
            loanDAO.createTable();
            addSampleDataIfEmpty();
            loadLoanData();
        } catch (SQLException e) {
            showError("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    
    
    private void addSampleDataIfEmpty() throws SQLException {
        List<Loan> loans = loanDAO.getAllLoans(currentUserId);
        if (loans.isEmpty()) {
            System.out.println("Adding sample data...");
            addSampleLoan("Salman", "Macbook Air", "2026-11-17", 5000.00);
            addSampleLoan("Sandesh", "Photography Equipment", "2025-09-22", 7000.00);
            addSampleLoan("Sameep", "Designing Material", "2025-12-27", 11000.00);
            addSampleLoan("Prajanya", "Mountain Bike", "2025-10-17", 17000.00);
        }
    }
    
    private void addSampleLoan(String borrower, String item, String dueDate, double amount) throws SQLException {
        LocalDate parsedDate = LocalDate.parse(dueDate);
        Loan loan = new Loan(borrower, item, amount, parsedDate, "Active");
        loan.setUserId(currentUserId);
        loan.setItemId(1);
        loanDAO.insertLoan(loan);
        System.out.println("Added sample loan: " + borrower + " - " + item);
    }
    
    private void loadLoanData() {
        try {
            System.out.println("Loading loan data for user: " + currentUserId);
            List<Loan> loans = loanDAO.getAllLoans(currentUserId);
            System.out.println("Loaded " + loans.size() + " loans");
            updateSummaryStats();
            
            // Update the view with record cards
            if (view != null) {
                SwingUtilities.invokeLater(() -> {
                    view.loadRecordCards();
                });
            }
            
        } catch (SQLException e) {
            showError("Error loading loan data: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public LoanDAO getLoanDAO() {
    return loanDAO;
}
    
    // Add this method to get loans for the view
    
    public List<Loan> getLoans() {
        try {
            return loanDAO.getAllLoans(currentUserId);
        } catch (SQLException e) {
            showError("Error loading loans: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    
    private void updateSummaryStats() throws SQLException {
        double totalAmount = loanDAO.getTotalLoanedAmount(currentUserId);
        int activeLoans = loanDAO.getActiveLoansCount(currentUserId);
        int overdueLoans = loanDAO.getOverdueLoansCount(currentUserId);
        
        System.out.println("Summary Stats:");
        System.out.println("Total Amount: $" + totalAmount);
        System.out.println("Active Loans: " + activeLoans);
        System.out.println("Overdue Loans: " + overdueLoans);
        
        // TEMPORARY: Print to console instead of updating UI
        // Remove or modify this once your view is fixed
        System.out.println("UI would show: Total=$" + totalAmount + 
                         ", Active=" + activeLoans + 
                         ", Overdue=" + overdueLoans);
    }
    
    // New Loan functionality
    public void openNewLoanDialog() {
        try {
            String borrower = JOptionPane.showInputDialog(view, "Enter borrower name:");
            if (borrower == null || borrower.trim().isEmpty()) return;
            
            String item = JOptionPane.showInputDialog(view, "Enter item name:");
            if (item == null || item.trim().isEmpty()) return;
            
            String amountStr = JOptionPane.showInputDialog(view, "Enter loan amount:");
            if (amountStr == null || amountStr.trim().isEmpty()) return;
            
            String dateStr = JOptionPane.showInputDialog(view, "Enter due date (YYYY-MM-DD):");
            if (dateStr == null || dateStr.trim().isEmpty()) return;
            
            double amount = Double.parseDouble(amountStr);
            LocalDate dueDate = LocalDate.parse(dateStr);
            
            Loan newLoan = new Loan(borrower, item, amount, dueDate, "Active");
            newLoan.setUserId(currentUserId);
            newLoan.setItemId(1);
            
            int loanId = loanDAO.insertLoan(newLoan);
            
            if (loanId > 0) {
                JOptionPane.showMessageDialog(view, "Loan created successfully!");
                loadLoanData();
            }
            
        } catch (Exception e) {
            showError("Error creating loan: " + e.getMessage());
        }
    }
    
    // Update loan
    public void updateLoan(String borrower, String item) {
        try {
            List<Loan> loans = loanDAO.getAllLoans(currentUserId);
            Loan selectedLoan = null;
            
            for (Loan loan : loans) {
                if (loan.getBorrowerName().equals(borrower) && loan.getItemName().equals(item)) {
                    selectedLoan = loan;
                    break;
                }
            }
            
            if (selectedLoan == null) {
                showError("Loan not found!");
                return;
            }
            
            String[] options = {"Update Amount", "Update Due Date", "Mark as Returned", "Cancel"};
            int choice = JOptionPane.showOptionDialog(view,
                "Update loan for " + borrower + " - " + item,
                "Update Loan",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]);
            
            switch(choice) {
                case 0: // Update Amount
                    String newAmountStr = JOptionPane.showInputDialog(view, 
                        "Current amount: $" + selectedLoan.getAmount() + "\nEnter new amount:");
                    if (newAmountStr != null && !newAmountStr.trim().isEmpty()) {
                        double newAmount = Double.parseDouble(newAmountStr);
                        selectedLoan.setAmount(newAmount);
                        loanDAO.updateLoan(selectedLoan);
                        JOptionPane.showMessageDialog(view, "Amount updated!");
                        loadLoanData();
                    }
                    break;
                    
                case 1: // Update Due Date
                    String newDateStr = JOptionPane.showInputDialog(view,
                        "Current due date: " + selectedLoan.getDueDate() + "\nEnter new due date (YYYY-MM-DD):");
                    if (newDateStr != null && !newDateStr.trim().isEmpty()) {
                        LocalDate newDate = LocalDate.parse(newDateStr);
                        selectedLoan.setDueDate(newDate);
                        loanDAO.updateLoan(selectedLoan);
                        JOptionPane.showMessageDialog(view, "Due date updated!");
                        loadLoanData();
                    }
                    break;
                    
                case 2: // Mark as Returned
                    int confirm = JOptionPane.showConfirmDialog(view,
                        "Mark loan as returned?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        loanDAO.markAsReturned(selectedLoan.getLoanId());
                        JOptionPane.showMessageDialog(view, "Loan marked as returned!");
                        loadLoanData();
                    }
                    break;
            }
            
        } catch (Exception e) {
            showError("Error updating loan: " + e.getMessage());
        }
    }
    
    // Return item
    public void returnItem(String borrower, String item) {
        try {
            int confirm = JOptionPane.showConfirmDialog(view,
                "Mark " + item + " as returned by " + borrower + "?",
                "Confirm Return", JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                List<Loan> loans = loanDAO.getAllLoans(currentUserId);
                
                for (Loan loan : loans) {
                    if (loan.getBorrowerName().equals(borrower) && loan.getItemName().equals(item)) {
                        loanDAO.markAsReturned(loan.getLoanId());
                        JOptionPane.showMessageDialog(view, "Item returned successfully!");
                        loadLoanData();
                        return;
                    }
                }
                
                showError("Loan not found!");
            }
        } catch (Exception e) {
            showError("Error returning item: " + e.getMessage());
        }
    }
    
    // Update status
    public void updateLoanStatus(String borrower, String item, String status) {
        try {
            List<Loan> loans = loanDAO.getAllLoans(currentUserId);
            
            for (Loan loan : loans) {
                if (loan.getBorrowerName().equals(borrower) && loan.getItemName().equals(item)) {
                    loan.setStatus(status);
                    loanDAO.updateLoan(loan);
                    JOptionPane.showMessageDialog(view, "Status updated to: " + status);
                    loadLoanData();
                    return;
                }
            }
            
            showError("Loan not found!");
        } catch (Exception e) {
            showError("Error updating status: " + e.getMessage());
        }
    }
    
    // Add this to LendingController.java
public void createLoanFromForm(String borrower, String itemName, double amount, String dueDate) {
    try {
        LocalDate parsedDate = LocalDate.parse(dueDate);
        Loan newLoan = new Loan(borrower, itemName, amount, parsedDate, "Active");
        newLoan.setUserId(currentUserId);
        newLoan.setItemId(1);
        
        int loanId = loanDAO.insertLoan(newLoan);
        
        if (loanId > 0) {
            JOptionPane.showMessageDialog(view, "Loan created successfully!");
            loadLoanData(); // This will refresh the data
        } else {
            JOptionPane.showMessageDialog(view, "Failed to create loan.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } catch (Exception e) {
        showError("Error creating loan: " + e.getMessage());
        e.printStackTrace();
    }
}
    
    
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(view, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    // Navigation methods
    public void navigateToMyInventory() {
        JOptionPane.showMessageDialog(view, "My Inventory - Coming Soon");
    }
    
    public void navigateToGoal() {
        JOptionPane.showMessageDialog(view, "Goal - Coming Soon");
    }
    
    public void navigateToFinancialAnalytics() {
        JOptionPane.showMessageDialog(view, "Financial Analytics - Coming Soon");
    }
    
    public void navigateToDocuments() {
        JOptionPane.showMessageDialog(view, "Documents - Coming Soon");
    }
    
    public void navigateToDashboard() {
        JOptionPane.showMessageDialog(view, "Dashboard - Coming Soon");
    }
    
    public void navigateToLending() {
        loadLoanData();
    }
    
     public void refreshView() {
        loadLoanData();
    }
}


