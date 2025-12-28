 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/*
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

    // FULL CONSTRUCTOR
    public Loan(int loanId, String borrowerName, String itemName,
                LocalDate dueDate, double amount, String status,
                LocalDate loanDate, int itemId) {

        this.loanId = loanId;
        this.borrowerName = borrowerName;
        this.itemName = itemName;
        this.dueDate = dueDate;
        this.amount = amount;
        this.status = status;
        this.loanDate = loanDate;
        this.itemId = itemId;
    }

    // MINIMUM CONSTRUCTOR
    public Loan(String borrowerName, String itemName,
                LocalDate dueDate, double amount) {

        this.borrowerName = borrowerName;
        this.itemName = itemName;
        this.dueDate = dueDate;
        this.amount = amount;
        this.status = "Active";
        this.loanDate = LocalDate.now();
    }

    public int getLoanId() { return loanId; }
    public String getBorrowerName() { return borrowerName; }
    public String getItemName() { return itemName; }
    public LocalDate getDueDate() { return dueDate; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }
    public LocalDate getLoanDate() { return loanDate; }
    public int getItemId() { return itemId; }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isOverdue() {
        return dueDate.isBefore(LocalDate.now()) && status.equals("Active");
    }
}

*/

package model;

import java.util.Date;

public class Loan {
    private String borrowerName;
    private String itemName;
    private double amount;
    private Date dueDate;
    private String status; // "Active", "Overdue", "Returned"
    
    public Loan(String borrowerName, String itemName, double amount, Date dueDate, String status) {
        this.borrowerName = borrowerName;
        this.itemName = itemName;
        this.amount = amount;
        this.dueDate = dueDate;
        this.status = status;
    }
    
    // Getters and setters
    public String getBorrowerName() { return borrowerName; }
    public void setBorrowerName(String borrowerName) { this.borrowerName = borrowerName; }
    
    public String getItemName() { return itemName; }
    public void setItemName(String itemName) { this.itemName = itemName; }
    
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    
    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}