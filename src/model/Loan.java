package model;

import java.time.LocalDate;

public class Loan {
    private int loanId;
    private int userId;
    private String borrowerName;
    private String itemName;
    private double amount;
    private LocalDate dueDate;
    private String status;
    private LocalDate loanDate;
    private int itemId;

    // Constructor 1: For simple creation
    public Loan(String borrowerName, String itemName, double amount, LocalDate dueDate, String status) {
        this.borrowerName = borrowerName;
        this.itemName = itemName;
        this.amount = amount;
        this.dueDate = dueDate;
        this.status = status;
        this.loanDate = LocalDate.now();
    }

    // Constructor 2: For database retrieval
    public Loan(int loanId, String borrowerName, String itemName, LocalDate dueDate, 
                double amount, String status, LocalDate loanDate, int itemId, int userId) {
        this.loanId = loanId;
        this.borrowerName = borrowerName;
        this.itemName = itemName;
        this.dueDate = dueDate;
        this.amount = amount;
        this.status = status;
        this.loanDate = loanDate;
        this.itemId = itemId;
        this.userId = userId;
    }

    // Getters and Setters
    public int getLoanId() { return loanId; }
    public void setLoanId(int loanId) { this.loanId = loanId; }
    
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    
    public String getBorrowerName() { return borrowerName; }
    public void setBorrowerName(String borrowerName) { this.borrowerName = borrowerName; }
    
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDate getLoanDate() { return loanDate; }
    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }
    
    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }
    
    @Override
    public String toString() {
        return borrowerName + " - " + itemName + " ($" + amount + ")";
    }
}