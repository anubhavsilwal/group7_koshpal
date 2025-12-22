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