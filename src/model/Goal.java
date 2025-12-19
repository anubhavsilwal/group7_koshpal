package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Goal {
    private String name;
    private String category;
    private double targetAmount;
    private double savedAmount;
    private LocalDate dueDate;
    private String location;
    
    public Goal(String name, String category, double targetAmount, 
                double savedAmount, LocalDate dueDate, String location) {
        this.name = name;
        this.category = category;
        this.targetAmount = targetAmount;
        this.savedAmount = savedAmount;
        this.dueDate = dueDate;
        this.location = location != null ? location :"";
    }
    
    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public double getTargetAmount() { return targetAmount; }
    public void setTargetAmount(double targetAmount) { this.targetAmount = targetAmount; }
    
    public double getSavedAmount() { return savedAmount; }
    public void setSavedAmount(double savedAmount) { this.savedAmount = savedAmount; }
    
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    // Helper methods
    public double getProgress() {
        if (targetAmount <= 0) return 0;
        return (savedAmount / targetAmount) * 100;
    }
    
    public boolean isCompleted() {
        return savedAmount >= targetAmount;
    }
    
    public String getDueDateFormatted() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dueDate.format(formatter);
    }
    
    public void addMoney(double amount) {
        this.savedAmount += amount;
    }
    
    public String getProgressText() {
        return String.format("$%.2f / $%.2f", savedAmount, targetAmount);
    }
}