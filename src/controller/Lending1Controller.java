/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Loan;
import model.Person;
import view.LendingView;
import dao.LoanDAO;  // Based on your project structure
import dao.PersonDAO; // Based on your project structure
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author salmanansari.81
 */
public class LendingController {
    private final LendingView lendingView;
    private final LoanDAO loanDAO;
    private final PersonDAO personDAO;
    private int currentUserId = 1;
    
    public LendingController(LendingView lendingView) {
        this.lendingView = lendingView;
        this.loanDAO = new LoanDAO();
        this.personDAO = new PersonDAO();
        
        try {
            // Set up listeners for navigation
            lendingView.setDashboardListener(new DashboardListener());
            lendingView.setInventoryListener(new InventoryListener());
            lendingView.setDocumentsListener(new DocumentsListener());
            lendingView.setLendingListener(new LendingListener());
            lendingView.setGoalsListener(new GoalsListener());
            lendingView.setFinancialListener(new FinancialListener());
            
            // Set up lending-specific listeners
            lendingView.setAddLoanListener(new AddLoanListener());
            lendingView.setSearchLoansListener(new SearchLoansListener());
            lendingView.setUpdateLoanListener(new UpdateLoanListener());
            lendingView.setReturnLoanListener(new ReturnLoanListener());
            
            System.out.println("Lending Controller setup complete");
        } catch (Exception e) {
            System.err.println("ERROR in LendingController constructor: " + e);
            e.printStackTrace();
        }
    }
    
    public void open() {
        lendingView.setVisible(true);
        loadInitialData();
    }
    
    public void close() {
        lendingView.dispose();
    }
    
    private void loadInitialData() {
        refreshDashboard();
        loadAllLoans();
        loadActivePersons();
    }
    
    private void refreshDashboard() {
        try {
            // Get total loaned amount
            double totalLoaned = loanDAO.getTotalLoanedAmount(currentUserId);
            lendingView.getTotalLoanedLabel().setText(formatCurrency(totalLoaned));
            
            // Get active loans count
            int activeLoans = loanDAO.getActiveLoansCount(currentUserId);
            lendingView.getActiveLoansLabel().setText(String.valueOf(activeLoans));
            
            // Get overdue loans count
            int overdueLoans = loanDAO.getOverdueLoansCount(currentUserId);
            lendingView.getOverdueLoansLabel().setText(String.valueOf(overdueLoans));
        } catch (Exception e) {
            System.err.println("Error refreshing dashboard: " + e.getMessage());
        }
    }
    
    private String formatCurrency(double amount) {
        return String.format("$%.2f", amount);
    }
    
    private void loadAllLoans() {
        JPanel loansContainer = lendingView.getLoansContainer();
        loansContainer.removeAll();
        
        try {
            List<Loan> loans = loanDAO.getAllLoans(currentUserId);
            
            if (loans.isEmpty()) {
                JLabel noLoansLabel = new JLabel("No loan records found. Add your first loan!");
                noLoansLabel.setFont(new java.awt.Font("Arial", java.awt.Font.ITALIC, 16));
                noLoansLabel.setHorizontalAlignment(SwingConstants.CENTER);
                loansContainer.add(noLoansLabel);
            } else {
                for (Loan loan : loans) {
                    JPanel loanPanel = createLoanPanel(loan);
                    loansContainer.add(loanPanel);
                }
            }
            
            loansContainer.revalidate();
            loansContainer.repaint();
        } catch (Exception e) {
            System.err.println("Error loading loans: " + e.getMessage());
            JLabel errorLabel = new JLabel("Error loading loans. Please try again.");
            errorLabel.setForeground(java.awt.Color.RED);
            loansContainer.add(errorLabel);
        }
    }
    
    private JPanel createLoanPanel(Loan loan) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(loan.getPersonName()));
        
        JLabel itemLabel = new JLabel("Item: " + loan.getItemName());
        JLabel dueLabel = new JLabel("Due: " + formatDate(loan.getDueDate()));
        JLabel amountLabel = new JLabel("Amount: " + formatCurrency(loan.getAmount()));
        
        // Color code for overdue loans
        if (loan.isOverdue()) {
            dueLabel.setForeground(java.awt.Color.RED);
        }
        
        panel.add(itemLabel);
        panel.add(dueLabel);
        panel.add(amountLabel);
        
        // Add action buttons
        JPanel buttonPanel = new JPanel(new java.awt.FlowLayout());
        JButton editButton = new JButton("Edit");
        JButton returnButton = new JButton("Return");
        
        editButton.addActionListener(e -> handleLoanEdit(loan.getLoanId()));
        returnButton.addActionListener(e -> handleLoanReturn(loan.getLoanId()));
        
        buttonPanel.add(editButton);
        buttonPanel.add(returnButton);
        panel.add(buttonPanel);
        
        return panel;
    }
    
    private void loadActivePersons() {
        try {
            List<Person> persons = personDAO.getActivePersons(currentUserId);
            DefaultComboBoxModel<String> personModel = new DefaultComboBoxModel<>();
            
            for (Person person : persons) {
                personModel.addElement(person.getName());
            }
            
            lendingView.getPersonComboBox().setModel(personModel);
        } catch (Exception e) {
            System.err.println("Error loading persons: " + e.getMessage());
        }
    }
    
    private String formatDate(Date date) {
        if (date == null) return "N/A";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    
    // ========== ACTION LISTENERS ==========
    
    class AddLoanListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            openAddLoanDialog();
        }
    }
    
    class SearchLoansListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchTerm = lendingView.getSearchField().getText().trim();
            if (searchTerm.isEmpty()) {
                loadAllLoans();
            } else {
                performLoanSearch(searchTerm);
            }
        }
    }
    
    class UpdateLoanListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshDashboard();
        }
    }
    
    class ReturnLoanListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedPerson = (String) lendingView.getPersonComboBox().getSelectedItem();
            if (selectedPerson != null) {
                handleBulkReturn(selectedPerson);
            }
        }
    }
    
    // Navigation listeners
    class DashboardListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Navigate to dashboard
                view.Dashboard dashboard = new view.Dashboard();
                dashboard.setVisible(true);
                lendingView.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(lendingView, "Cannot open dashboard: " + ex.getMessage());
            }
        }
    }
    
    class InventoryListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Navigate to inventory
                view.InventoryView inventoryView = new view.InventoryView();
                inventoryView.setVisible(true);
                lendingView.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(lendingView, "Cannot open inventory: " + ex.getMessage());
            }
        }
    }
    
    class DocumentsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(lendingView, "Documents feature coming soon!");
        }
    }
    
    class LendingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Already in lending view - refresh data
            loadAllLoans();
            refreshDashboard();
        }
    }
    
    class GoalsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(lendingView, "Goals feature coming soon!");
        }
    }
    
    class FinancialListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(lendingView, "Financial Analytics feature coming soon!");
        }
    }
    
    // ========== LOAN OPERATION METHODS ==========
    
    private void openAddLoanDialog() {
        // Create a custom dialog for adding loans
        JDialog addLoanDialog = new JDialog(lendingView, "Add New Loan", true);
        addLoanDialog.setLayout(new java.awt.BorderLayout());
        
        JPanel formPanel = new JPanel(new java.awt.GridLayout(5, 2, 10, 10));
        
        // Person selection
        formPanel.add(new JLabel("Person:"));
        JComboBox<String> personCombo = new JComboBox<>();
        loadActivePersons(); // Refresh person list
        personCombo.setModel(lendingView.getPersonComboBox().getModel());
        formPanel.add(personCombo);
        
        // Item name
        formPanel.add(new JLabel("Item:"));
        JTextField itemField = new JTextField();
        formPanel.add(itemField);
        
        // Amount
        formPanel.add(new JLabel("Amount:"));
        JTextField amountField = new JTextField();
        formPanel.add(amountField);
        
        // Due date
        formPanel.add(new JLabel("Due Date (YYYY-MM-DD):"));
        JTextField dateField = new JTextField(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        formPanel.add(dateField);
        
        // Notes
        formPanel.add(new JLabel("Notes:"));
        JTextField notesField = new JTextField();
        formPanel.add(notesField);
        
        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        
        saveButton.addActionListener(e -> {
            try {
                String personName = (String) personCombo.getSelectedItem();
                String itemName = itemField.getText().trim();
                String amountText = amountField.getText().trim();
                String dateText = dateField.getText().trim();
                String notes = notesField.getText().trim();
                
                if (personName == null || personName.isEmpty()) {
                    JOptionPane.showMessageDialog(addLoanDialog, "Please select a person!");
                    return;
                }
                
                if (itemName.isEmpty()) {
                    JOptionPane.showMessageDialog(addLoanDialog, "Please enter an item name!");
                    return;
                }
                
                if (amountText.isEmpty()) {
                    JOptionPane.showMessageDialog(addLoanDialog, "Please enter an amount!");
                    return;
                }
                
                double amount = Double.parseDouble(amountText);
                Date dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateText);
                
                // Create new loan
                Loan newLoan = new Loan();
                newLoan.setUserId(currentUserId);
                newLoan.setPersonName(personName);
                newLoan.setItemName(itemName);
                newLoan.setAmount(amount);
                newLoan.setDueDate(dueDate);
                newLoan.setNotes(notes);
                newLoan.setReturned(false);
                
                boolean success = loanDAO.addLoan(newLoan);
                if (success) {
                    JOptionPane.showMessageDialog(addLoanDialog, "Loan added successfully!");
                    addLoanDialog.dispose();
                    loadAllLoans();
                    refreshDashboard();
                } else {
                    JOptionPane.showMessageDialog(addLoanDialog, "Failed to add loan!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(addLoanDialog, "Error: " + ex.getMessage());
            }
        });
        
        cancelButton.addActionListener(e -> addLoanDialog.dispose());
        
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        addLoanDialog.add(formPanel, java.awt.BorderLayout.CENTER);
        addLoanDialog.add(buttonPanel, java.awt.BorderLayout.SOUTH);
        addLoanDialog.pack();
        addLoanDialog.setLocationRelativeTo(lendingView);
        addLoanDialog.setVisible(true);
    }
    
    private void performLoanSearch(String searchTerm) {
        JPanel loansContainer = lendingView.getLoansContainer();
        loansContainer.removeAll();
        
        try {
            List<Loan> results = loanDAO.searchLoans(currentUserId, searchTerm);
            
            if (results.isEmpty()) {
                JLabel noResultsLabel = new JLabel("No loans found for: \"" + searchTerm + "\"");
                noResultsLabel.setFont(new java.awt.Font("Arial", java.awt.Font.ITALIC, 16));
                noResultsLabel.setHorizontalAlignment(SwingConstants.CENTER);
                loansContainer.add(noResultsLabel);
            } else {
                for (Loan loan : results) {
                    JPanel loanPanel = createLoanPanel(loan);
                    loansContainer.add(loanPanel);
                }
            }
        } catch (Exception e) {
            System.err.println("Error searching loans: " + e.getMessage());
        }
        
        loansContainer.revalidate();
        loansContainer.repaint();
    }
    
    private void handleLoanEdit(int loanId) {
        try {
            Loan loanToEdit = loanDAO.getLoanById(loanId);
            
            if (loanToEdit != null) {
                String newAmountStr = JOptionPane.showInputDialog(lendingView, 
                    "Edit Loan Amount:", formatCurrency(loanToEdit.getAmount()));
                
                if (newAmountStr != null && !newAmountStr.trim().isEmpty()) {
                    // Remove currency symbols if present
                    newAmountStr = newAmountStr.replaceAll("[^\\d.]", "");
                    double newAmount = Double.parseDouble(newAmountStr);
                    
                    String newDueDateStr = JOptionPane.showInputDialog(lendingView,
                        "Edit Due Date (YYYY-MM-DD):", formatDate(loanToEdit.getDueDate()));
                    
                    if (newDueDateStr != null && !newDueDateStr.trim().isEmpty()) {
                        Date newDueDate = new SimpleDateFormat("yyyy-MM-dd").parse(newDueDateStr);
                        
                        loanToEdit.setAmount(newAmount);
                        loanToEdit.setDueDate(newDueDate);
                        
                        boolean success = loanDAO.updateLoan(loanToEdit);
                        if (success) {
                            JOptionPane.showMessageDialog(lendingView, "Loan updated successfully!");
                            loadAllLoans();
                            refreshDashboard();
                        } else {
                            JOptionPane.showMessageDialog(lendingView, "Failed to update loan!");
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(lendingView, "Loan not found!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(lendingView, "Error: " + ex.getMessage());
        }
    }
    
    private void handleLoanReturn(int loanId) {
        int confirm = JOptionPane.showConfirmDialog(
            lendingView,
            "Mark this loan as returned?",
            "Confirm Return",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                boolean success = loanDAO.markAsReturned(loanId);
                if (success) {
                    JOptionPane.showMessageDialog(lendingView, "Loan marked as returned!");
                    loadAllLoans();
                    refreshDashboard();
                } else {
                    JOptionPane.showMessageDialog(lendingView, "Failed to mark loan as returned!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(lendingView, "Error: " + ex.getMessage());
            }
        }
    }
    
    private void handleBulkReturn(String personName) {
        int confirm = JOptionPane.showConfirmDialog(
            lendingView,
            "Mark all loans to " + personName + " as returned?",
            "Bulk Return",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                boolean success = loanDAO.bulkReturn(personName, currentUserId);
                if (success) {
                    JOptionPane.showMessageDialog(lendingView, 
                        "All loans to " + personName + " marked as returned!");
                    loadAllLoans();
                    refreshDashboard();
                } else {
                    JOptionPane.showMessageDialog(lendingView, "Failed to mark loans as returned!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(lendingView, "Error: " + ex.getMessage());
            }
        }
    }
}
