/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author salmanansari.81
 */
public class Record {
    private String userName;
    private String item;
    private String dueDate;
    private double amount;
    
    public Record(String userName, String item, String dueDate, double amount) {
        this.userName = userName;
        this.item = item;
        this.dueDate = dueDate;
        this.amount = amount;
    }
    
    // Getters
    public String getRecordUserName() { return userName; }
    public String getRecordItem() { return item; }
    public String getRecordDueDate() { return dueDate; }
    public double getRecordAmount() { return amount; }
    
    // Optional: Add setters if needed
    public void setUserName(String userName) { this.userName = userName; }
    public void setItem(String item) { this.item = item; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }
    public void setAmount(double amount) { this.amount = amount; }
    
    // Optional: Add toString() method for debugging
    @Override
    public String toString() {
        return "Record{" +
               "userName='" + userName + '\'' +
               ", item='" + item + '\'' +
               ", dueDate='" + dueDate + '\'' +
               ", amount=" + amount +
               '}';
    }
}
    

