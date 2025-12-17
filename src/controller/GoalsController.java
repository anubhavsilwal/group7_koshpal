package controller;

import model.Goal;
import model.GoalsModel;
import javax.swing.*;
import java.time.LocalDate;

public class GoalsController {
    private GoalsModel model;
    
    // Remove the view parameter since we don't use GoalsPanel
    public GoalsController(GoalsModel model) {
        this.model = model;
        // Don't set view or add listener - we handle it in goals.java
    }
    
    // Alternative constructor for compatibility
    public GoalsController(GoalsModel model, Object view) {
        this(model); // Just call the main constructor
    }
    
    public void showAddGoalDialog() {
        JTextField nameField = new JTextField();
        JTextField categoryField = new JTextField();
        JTextField targetField = new JTextField();
        JTextField savedField = new JTextField("0");
        JTextField dateField = new JTextField(LocalDate.now().plusMonths(1).toString());
        JTextField locationField = new JTextField();
        
        Object[] message = {
            "Name:", nameField,
            "Category:", categoryField,
            "Target Amount:", targetField,
            "Saved Amount:", savedField,
            "Due Date (YYYY-MM-DD):", dateField,
            "Location:", locationField
        };
        
        int option = JOptionPane.showConfirmDialog(
            null,
            message,
            "Add New Goal",
            JOptionPane.OK_CANCEL_OPTION
        );
        
        if (option == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                String category = categoryField.getText();
                double targetAmount = Double.parseDouble(targetField.getText());
                double savedAmount = Double.parseDouble(savedField.getText());
                LocalDate dueDate = LocalDate.parse(dateField.getText());
                String location = locationField.getText();
                
                Goal goal = new Goal(name, category, targetAmount, savedAmount, dueDate, location);
                model.addGoal(goal);
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                    "Invalid input. Please check all fields.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}