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
public class LoanModel {
    private int loanId;
    private String borrowerName;
    private String itemName;
    private LocalDate dueDate;
    private double amount;
    private String status;

    
    public LoanModel(int loanId, String borrowerName, String itemName, LocalDate dueDate, double amount, String status) {
        this.loanId = loanId;
        this.borrowerName = borrowerName;
        this.itemName = itemName;
        this.dueDate = dueDate;
        this.amount = amount;
        this.status = status;
    }

    public LoanModel(String borrowerName, String itemName, LocalDate dueDate, double amount) {
        this.borrowerName = borrowerName;
        this.itemName = itemName;
        this.dueDate = dueDate;
        this.amount = amount;
        this.status = "Active";
    }

    
    public void setLoanId(int loanId){
        this.loanId=loanId;
    }
    public int getLoanId() 
    { return loanId; }
    
    public void setBorrowerName(String borrowerName){
        this.borrowerName=borrowerName;
    }
    public String getBorrowerName() 
    { return borrowerName; }
    
    public void setItemName(String itemName){
        this.itemName=itemName;
    }
    public String getItemName() 
    { return itemName; }
    
    public void setDueDate(LocalDate dueDate) {
    this.dueDate = dueDate;
    }

    public LocalDate getDueDate() {
    return dueDate;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public double getAmount() 
    { return amount; }
    
    public void setStatus(String status) { 
        this.status = status; 
    }
    public String getStatus() 
    { return status; }

    
    public boolean isOverdue() {
        return dueDate.isBefore(LocalDate.now()) && status.equals("Active");
    }
}

/**/