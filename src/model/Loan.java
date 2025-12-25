package model;

import java.time.LocalDate;

public class Loan {
    private int loanId;
    private String borrowerName;
    private String itemName;
    private LocalDate dueDate;
    private double amount;
    private String status;
    private LocalDate loanDate;
    private int itemId;
    private int userId;

    // Constructor with all fields
    public Loan(int loanId, String borrowerName, String itemName, 
                LocalDate dueDate, double amount, String status, 
                LocalDate loanDate, int itemId, int userId) {
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

    // Constructor for new loans
    public Loan(String borrowerName, String itemName, 
                LocalDate dueDate, double amount, int itemId, int userId) {
        this.borrowerName = borrowerName;
        this.itemName = itemName;
        this.dueDate = dueDate;
        this.amount = amount;
        this.itemId = itemId;
        this.userId = userId;
        this.status = "Active";
        this.loanDate = LocalDate.now();
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
    
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDate getLoanDate() { return loanDate; }
    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }
    
    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }
    
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    
    public boolean isOverdue() {
        return dueDate.isBefore(LocalDate.now()) && status.equals("Active");
    }
}