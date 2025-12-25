package controller;

import view.lending;
import model.Loan;
import dao.LoanDAO;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class LendingController {
    private lending view;
    private LoanDAO loanDAO;
    private int currentUserId = 1; // Change this based on your login system
    
    public LendingController(lending view, Connection connection) {
        this.view = view;
        this.loanDAO = new LoanDAO(connection);
        
        try {
            // ADD THIS: Create table if it doesn't exist
            loanDAO.createTable();
            
            // ADD THIS: Add sample data if empty
            addSampleDataIfEmpty();
            
            loadLoanData();
        } catch (SQLException e) {
            showError("Database error: " + e.getMessage());
        }
    }
    
    // ADD THIS METHOD
    private void addSampleDataIfEmpty() throws SQLException {
        List<Loan> loans = loanDAO.getAllLoans(currentUserId);
        if (loans.isEmpty()) {
            // Add sample loans
            addSampleLoan("Salman", "Macbook Air", "2026-11-17", 5000.00);
            addSampleLoan("Sandesh", "Photography Equipment", "2025-09-22", 7000.00);
            addSampleLoan("Sameep", "Designing Material", "2025-12-27", 11000.00);
            addSampleLoan("Prajanya", "Mountain Bike", "2025-10-17", 17000.00);
        }
    }
    
    // ADD THIS METHOD        
    private void addSampleLoan(String borrower, String item, String dueDate, double amount) throws SQLException {
        Loan loan = new Loan(
            borrower, 
            item, 
            java.time.LocalDate.parse(dueDate), 
            amount, 
            1,  // item_id
            currentUserId  // user_id
        );
        loanDAO.insertLoan(loan);
    }
    
    private void loadLoanData() {
        try {
            // Load and display loan data
            List<Loan> loans = loanDAO.getAllLoans(currentUserId);
            
            // Update UI with loan data
            updateUIWithLoans(loans);
            
            // Update summary stats
            updateSummaryStats();
            
        } catch (SQLException e) {
            showError("Error loading loan data: " + e.getMessage());
        }
    }
    
    private void updateUIWithLoans(List<Loan> loans) {
        // Here you would update your UI components with loan data
        // This depends on how you want to display the loans
        // For now, just show a message
        if (!loans.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Loaded " + loans.size() + " loans");
        }
    }
    
    private void updateSummaryStats() throws SQLException {
        double totalAmount = loanDAO.getTotalLoanedAmount(currentUserId);
        int activeLoans = loanDAO.getActiveLoansCount(currentUserId);
        int overdueLoans = loanDAO.getOverdueLoansCount(currentUserId);
        
        // Update your UI labels here
        // view.jLabel7.setText("$ " + totalAmount);
        // view.jLabel8.setText(String.valueOf(activeLoans));
        // view.jLabel6.setText(String.valueOf(overdueLoans));
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
            
            // Create new loan (itemId needs to be fetched from your inventory)
            Loan newLoan = new Loan(borrower, item, dueDate, amount, 1, currentUserId);
            
            int loanId = loanDAO.insertLoan(newLoan);
            
            if (loanId > 0) {
                JOptionPane.showMessageDialog(view, "Loan created successfully!");
                loadLoanData(); // Refresh data
            }
            
        } catch (Exception e) {
            showError("Error creating loan: " + e.getMessage());
        }
    }
    
    // Update loan
    public void updateLoan(String borrower, String item) {
        try {
            // Get current loan details
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
    
    // Update status (Active/Overdue)
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
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(view, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    // Navigation methods (simplified)
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
        throw new UnsupportedOperationException("Not supported yet.");
    }
}