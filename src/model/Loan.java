/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author salmanansari.81
 */
public class Loan {
    private int loanId;
    private String borrowerName;
    private String itemName;
    private LocalDate dueDate;
    private double loanAmount;
    private String status; // "Active", "Returned", "Overdue"
    private LocalDate loanDate;
    private int itemId; // Reference to the item being loaned
    
    // Constructor without loanId (for new loans)
    public Loan (String borrowerName, String itemName, LocalDate dueDate, 
                double loanAmount, String status, LocalDate loanDate, int itemId) {
        this.borrowerName = borrowerName;
        this.itemName = itemName;
        this.dueDate = dueDate;
        this.loanAmount = loanAmount;
        this.status = status;
        this.loanDate = loanDate;
        this.itemId = itemId;
    }
    
    // Constructor with loanId (for existing loans from database)
    public Loan (int loanId, String borrowerName, String itemName, LocalDate dueDate,
                double loanAmount, String status, LocalDate loanDate, int itemId) 
    {
        this.loanId = loanId;
        this.borrowerName = borrowerName;
        this.itemName = itemName;
        this.dueDate = dueDate;
        this.loanAmount = loanAmount;
        this.status = status;
        this.loanDate = loanDate;
        this.itemId = itemId;
    }
    
    // Getters and Setters
    public int getLoanId() { return loanId; }
    public void setLoanId(int loanId) { this.loanId = loanId; }
    
    public String getBorrowerName() { return borrowerName; }
    public void setBorrowerName(String borrowerName) { this.borrowerName = borrowerName; }
    
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    
    public double getLoanAmount() { return loanAmount; }
    public void setLoanAmount(double loanAmount) { this.loanAmount = loanAmount; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDate getLoanDate() { return loanDate; }
    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }
    
    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }
    
    // Helper method to check if loan is overdue
    public boolean isOverdue() {
        return LocalDate.now().isAfter(dueDate) && status.equals("Active");
    }
    
}
