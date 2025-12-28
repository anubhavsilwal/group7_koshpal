/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.GoalsController;
import model.GoalsModel;
import model.Goal;
import javax.swing.*;
import java.awt.Component;
import java.awt.Font;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author anubhavsilwal
 */
public class goals extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(goals.class.getName());
     
    private GoalsModel goalsModel;
    private GoalsController goalsController;
    private List<Goal> currentGoals;

    /**
     * Creates new form inventory
     */
    public goals() {
        initComponents();
         initializeGoalsMVC();
    }
    private void initializeGoalsMVC() {
    try {
        // 1. Initialize the model (this loads sample data)
        goalsModel = new GoalsModel();
        
        // 2. Setup listener for automatic updates
        setupModelListener();
        
        // 3. Initialize controller
        goalsController = new GoalsController(goalsModel);
        
        // 4. Load and display goals
        refreshGoalsDisplay();
        
        logger.info("Goals MVC system initialized successfully!");
        
    } catch (Exception e) {
        logger.severe("Error initializing goals system: " + e.getMessage());
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, 
            "Error initializing goals system. Please check console.",
            "Initialization Error", 
            JOptionPane.ERROR_MESSAGE);
    }
}
    
    /**
     * Listener for goal updates
     */
    private void setupModelListener() {
        if (goalsModel != null) {
            // Create a listener that refreshes the display when model changes
            goalsModel.addListener(() -> {
                SwingUtilities.invokeLater(() -> {
                    refreshGoalsDisplay();
                });
            });
        }
    }
    
    /**
     * Refresh all goals display
     */
    private void refreshGoalsDisplay() {
        try {
            // Get current goals from model
            currentGoals = goalsModel.getGoals();
            // ADD NULL CHECK:
            if (currentGoals == null){
                System.out.println("No goals to display");
                return;
            }
            
            // Update goal cards based on actual data
            updateGoalCard(jPanel4, 0); // First card
            updateGoalCard(jPanel5, 1); // Second card  
            updateGoalCard(jPanel6, 2); // Third card
            updateGoalCard(jPanel7, 3); // Fourth card
            updateGoalCard(jPanel9, 4); // Fifth card - NEW
            updateGoalCard(jPanel10, 5);
            
            // Update summary
            updateSummary();
            
        } catch (Exception e) {
            logger.warning("Error refreshing goals display: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Update a specific goal card
     */
        /**
     * Update a specific goal card
     */
    private void updateGoalCard(JPanel cardPanel, int goalIndex) {
        if (currentGoals == null) {
            cardPanel.setVisible(false);
            return;
        }
        
        if (goalIndex < 0 || goalIndex >= currentGoals.size()) {
            // Hide card if no goal exists at this index
            cardPanel.setVisible(false);
            return;
        }
            
        Goal goal = currentGoals.get(goalIndex);
        cardPanel.setVisible(true);
        
        try {
            // Better approach: Update labels based on font properties
            for (Component comp : cardPanel.getComponents()) {
                if (comp instanceof JLabel) {
                    JLabel label = (JLabel) comp;
                    
                    // Update goal name (font size 18 and bold)
                    if (label.getFont().getSize() >= 16 && label.getFont().isBold() && 
                        !label.getText().contains("Due:")) {
                        label.setText(goal.getName());
                    }
                    
                    // Update category (italic font)
                    else if (label.getFont().getStyle() == Font.ITALIC) {
                        label.setText(goal.getCategory());
                    }
                    
                    // Update amount (contains $ or "Target")
                    else if (label.getText() != null && 
                            (label.getText().contains("$") || label.getText().contains("Target"))) {
                        label.setText(String.format("$%.2f / $%.2f", 
                            goal.getSavedAmount(), goal.getTargetAmount()));
                    }
                    
                    // Update due date
                    else if (label.getText() != null && label.getText().startsWith("Due:")) {
                        label.setText("Due: " + goal.getDueDateFormatted());
                    }
                }
                else if (comp instanceof JProgressBar) {
                    JProgressBar progressBar = (JProgressBar) comp;
                    progressBar.setValue((int) goal.getProgress());
                    progressBar.setString(String.format("%.0f%%", goal.getProgress()));
                }
            }
            
        } catch (Exception e) {
            logger.warning("Error updating goal card " + goalIndex + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
    /**
     * Update the summary panel
     */
    private void updateSummary() {
        try {
            jLabel25.setText(String.valueOf(goalsModel.getTotalGoals())); // Total Goals
            jLabel27.setText(String.format("$%.2f", goalsModel.getTotalTarget())); // Total target
            jLabel31.setText(String.format("$%.2f", goalsModel.getTotalSaved())); // Current saved
            jLabel32.setText(String.format("%.0f%%", goalsModel.getAverageProgress())); // Avg Progress
            
        } catch (Exception e) {
            logger.warning("Error updating summary: " + e.getMessage());
        }
    }
     
    /**
     * Show dialog to update goal progress
     */
       private void updateGoalProgress(int goalIndex) {
        if (currentGoals == null) {
            JOptionPane.showMessageDialog(this, 
                "No goal at this position. Add a goal first.", 
                "No Goal", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (goalIndex >= currentGoals.size()){
            JOptionPane.showMessageDialog(this,
                    "Goals not loaded yet. Please wait.",
                    "No Goal",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Goal goal = currentGoals.get(goalIndex);
        
        Object[] options = {"Add Money", "Remove Money", "Cancel"};
    int choice = JOptionPane.showOptionDialog(
        this,
        "Goal: " + goal.getName() + "\n" +
        "Current: $" + goal.getSavedAmount() + " / $" + goal.getTargetAmount() + "\n" +
        "Progress: " + String.format("%.0f%%", goal.getProgress()),
        "Update Goal - " + goal.getName(),
        JOptionPane.DEFAULT_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[0]
    );
    
    if (choice == 0) { // Add Money
        String input = JOptionPane.showInputDialog(
            this,
            "Add money to: " + goal.getName() + "\n" +
            "Current: $" + goal.getSavedAmount() + " / $" + goal.getTargetAmount() + "\n" +
            "Enter amount to add:",
            "Add Money to Goal",
            JOptionPane.QUESTION_MESSAGE
        );
        
        processAmountChange(goalIndex, input, true);
        
    } else if (choice == 1) { // Remove Money
        String input = JOptionPane.showInputDialog(
            this,
            "Remove money from: " + goal.getName() + "\n" +
            "Current: $" + goal.getSavedAmount() + " / $" + goal.getTargetAmount() + "\n" +
            "Enter amount to remove:",
            "Remove Money from Goal",
            JOptionPane.QUESTION_MESSAGE
        );
        
        processAmountChange(goalIndex, input, false);
    }
}

private void processAmountChange(int goalIndex, String input, boolean isAdding) {
    if (input != null && !input.trim().isEmpty()) {
        try {
            double amount = Double.parseDouble(input);
            
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this,
                    "Please enter a positive amount.",
                    "Invalid Amount",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            Goal goal = currentGoals.get(goalIndex);
            
            // Check if removing more than available
            if (!isAdding && amount > goal.getSavedAmount()) {
                JOptionPane.showMessageDialog(this,
                    "Cannot remove more than the current saved amount ($" + 
                    goal.getSavedAmount() + ").",
                    "Insufficient Funds",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Apply the amount (add or subtract)
            double adjustedAmount = isAdding ? amount : -amount;
            goalsModel.addMoneyToGoal(goalIndex, adjustedAmount);
            
            // Show success message
            String action = isAdding ? "added to" : "removed from";
            String amountMsg = String.format("$%.2f", amount);
            
            JOptionPane.showMessageDialog(this,
                String.format("Successfully %s %s %s!\nNew total: $%.2f / $%.2f", 
                    action, amountMsg, goal.getName(), 
                    goal.getSavedAmount(), goal.getTargetAmount()),
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                "Please enter a valid number.",
                "Invalid Input",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
        
    
    /**
     * Show summary details
     */
    private void showSummaryDetails() {
        if (goalsModel == null) return;
        
        String summary = String.format(
            "<html><div style='font-size:12pt; padding:10px;'>" +
            "<h3>Financial Goals Summary</h3>" +
            "<b>Total Goals:</b> %d<br>" +
            "<b>Total Target Amount:</b> $%.2f<br>" +
            "<b>Total Saved:</b> $%.2f<br>" +
            "<b>Remaining Amount:</b> $%.2f<br>" +
            "<b>Average Progress:</b> %.1f%%<br><br>" +
            "<i>Keep up the good work!</i>" +
            "</div></html>",
            goalsModel.getTotalGoals(),
            goalsModel.getTotalTarget(),
            goalsModel.getTotalSaved(),
            goalsModel.getTotalTarget() - goalsModel.getTotalSaved(),
            goalsModel.getAverageProgress()
        );
        
        JOptionPane.showMessageDialog(this, summary, "Goals Summary", JOptionPane.INFORMATION_MESSAGE);
    }
        private void removeGoal(int goalIndex) {
        if (goalsModel == null) return;
        if (currentGoals == null || goalIndex >= currentGoals.size()) {
            JOptionPane.showMessageDialog(this, "No goal to remove.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Goal goal = currentGoals.get(goalIndex);
        int confirm = JOptionPane.showConfirmDialog(this,
            "Remove goal: " + goal.getName() + "?", 
            "Confirm", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            goalsModel.removeGoal(goalIndex);
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        button1 = new java.awt.Button();
        Lending = new java.awt.Button();
        Goals = new java.awt.Button();
        button4 = new java.awt.Button();
        Documents = new java.awt.Button();
        Dashboard = new java.awt.Button();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jButton8 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jProgressBar2 = new javax.swing.JProgressBar();
        jLabel10 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jButton6 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jProgressBar3 = new javax.swing.JProgressBar();
        jLabel15 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jButton7 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jProgressBar4 = new javax.swing.JProgressBar();
        jLabel20 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        jButton9 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jProgressBar5 = new javax.swing.JProgressBar();
        jLabel33 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        jButton11 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jProgressBar6 = new javax.swing.JProgressBar();
        jLabel43 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();
        jButton13 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(254, 251, 238));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setIcon(new javax.swing.ImageIcon("C:\\Users\\Prajanya\\OneDrive\\Pictures\\Screenshot 2025-12-12 170217.png")); // NOI18N
        jPanel1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 20, -1, -1));

        jLabel39.setIcon(new javax.swing.ImageIcon("C:\\Users\\Prajanya\\OneDrive\\Pictures\\profile.png")); // NOI18N
        jPanel1.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 0, -1, -1));

        jLabel38.setIcon(new javax.swing.ImageIcon("C:\\Users\\Prajanya\\OneDrive\\Pictures\\kosh.png")); // NOI18N
        jPanel1.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1440, 100);

        jPanel2.setBackground(new java.awt.Color(224, 236, 229));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        button1.setBackground(new java.awt.Color(254, 251, 238));
        button1.setLabel("My Inventory");
        jPanel2.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 200, 50));

        Lending.setBackground(new java.awt.Color(254, 251, 238));
        Lending.setLabel("Lending");
        jPanel2.add(Lending, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 200, 50));

        Goals.setBackground(new java.awt.Color(254, 251, 238));
        Goals.setLabel("Goals");
        jPanel2.add(Goals, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 200, 50));

        button4.setBackground(new java.awt.Color(254, 251, 238));
        button4.setLabel("Financial Analytics");
        jPanel2.add(button4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 200, 50));

        Documents.setBackground(new java.awt.Color(254, 251, 238));
        Documents.setLabel("Documents");
        jPanel2.add(Documents, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 200, 50));

        Dashboard.setBackground(new java.awt.Color(254, 251, 238));
        Dashboard.setLabel("Dashboard");
        jPanel2.add(Dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 200, 50));

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 100, 200, 800);

        jPanel3.setBackground(new java.awt.Color(205, 231, 217));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel1.setText("Financial Goals");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 170, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(150, 180, 220), 2));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(30, 58, 138));
        jLabel2.setText("Vacation Fund");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(30, 58, 138));
        jLabel3.setText("Travel");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("$7,500 / $10,000");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jProgressBar1.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jProgressBar1.setForeground(new java.awt.Color(59, 130, 246));
        jProgressBar1.setValue(75);
        jProgressBar1.setStringPainted(true);
        jPanel4.add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 310, 20));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Due: 2024-12-31");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jButton1.setBackground(new java.awt.Color(204, 255, 204));
        jButton1.setForeground(new java.awt.Color(0, 204, 0));
        jButton1.setText("Update");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, -1, -1));

        jSeparator4.setForeground(new java.awt.Color(102, 102, 102));
        jPanel4.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 300, -1));

        jButton8.setForeground(new java.awt.Color(255, 0, 0));
        jButton8.setText("Remove");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, -1, -1));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 350, 210));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(150, 180, 220), 2));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(30, 58, 138));
        jLabel7.setText("Vacation Fund");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(30, 58, 138));
        jLabel8.setText("Travel");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("$7,500 / $10,000");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jProgressBar2.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jProgressBar2.setForeground(new java.awt.Color(59, 130, 246));
        jProgressBar2.setValue(75);
        jProgressBar2.setStringPainted(true);
        jPanel5.add(jProgressBar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 310, 20));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Due: 2024-12-31");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jButton2.setBackground(new java.awt.Color(204, 255, 204));
        jButton2.setForeground(new java.awt.Color(0, 204, 0));
        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, -1, -1));

        jSeparator2.setForeground(new java.awt.Color(102, 102, 102));
        jPanel5.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 300, -1));

        jButton6.setForeground(new java.awt.Color(255, 0, 0));
        jButton6.setText("Remove");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, -1, -1));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 350, 210));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(150, 180, 220), 2));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(30, 58, 138));
        jLabel12.setText("Vacation Fund");
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(30, 58, 138));
        jLabel13.setText("Travel");
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("$7,500 / $10,000");
        jPanel6.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jProgressBar3.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jProgressBar3.setForeground(new java.awt.Color(59, 130, 246));
        jProgressBar3.setValue(75);
        jProgressBar3.setStringPainted(true);
        jPanel6.add(jProgressBar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 310, 20));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setText("Due: 2024-12-31");
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jButton3.setBackground(new java.awt.Color(204, 255, 204));
        jButton3.setForeground(new java.awt.Color(0, 204, 0));
        jButton3.setText("Update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, -1, -1));

        jSeparator3.setForeground(new java.awt.Color(102, 102, 102));
        jPanel6.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 300, -1));

        jButton7.setForeground(new java.awt.Color(255, 0, 0));
        jButton7.setText("Remove");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, -1, -1));

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 120, 350, 210));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(150, 180, 220), 2));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(30, 58, 138));
        jLabel17.setText("Vacation Fund");
        jPanel7.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(30, 58, 138));
        jLabel18.setText("Travel");
        jPanel7.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setText("$7,500 / $10,000");
        jPanel7.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jProgressBar4.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jProgressBar4.setForeground(new java.awt.Color(59, 130, 246));
        jProgressBar4.setValue(75);
        jProgressBar4.setStringPainted(true);
        jPanel7.add(jProgressBar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 310, 20));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel20.setText("Due: 2024-12-31");
        jPanel7.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jButton4.setBackground(new java.awt.Color(204, 255, 204));
        jButton4.setForeground(new java.awt.Color(0, 204, 0));
        jButton4.setText("Update");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, -1, -1));

        jSeparator5.setForeground(new java.awt.Color(102, 102, 102));
        jPanel7.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 300, -1));

        jButton9.setForeground(new java.awt.Color(255, 0, 0));
        jButton9.setText("Remove");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, -1, -1));

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 360, 350, 210));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(153, 153, 153));
        jLabel22.setText("Set and track your financial objectives");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel23.setText("Summary");
        jPanel8.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setText("Total Goals");
        jPanel8.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, -1, -1));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setText("4");
        jPanel8.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, 20, -1));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel26.setText("Total target");
        jPanel8.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, -1, -1));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel27.setText("$40,000");
        jPanel8.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, -1, -1));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel28.setText("Current saved");
        jPanel8.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, -1, -1));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel29.setText("Avg Progress");
        jPanel8.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, -1, -1));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel31.setText("$23,000");
        jPanel8.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, -1, -1));

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel32.setText("75%");
        jPanel8.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, -1, -1));

        jLabel35.setIcon(new javax.swing.ImageIcon("C:\\Users\\Prajanya\\OneDrive\\Pictures\\market.png")); // NOI18N
        jPanel8.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 50, -1, -1));

        jLabel36.setIcon(new javax.swing.ImageIcon("C:\\Users\\Prajanya\\OneDrive\\Pictures\\market.png")); // NOI18N
        jPanel8.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, -1, -1));

        jLabel37.setIcon(new javax.swing.ImageIcon("C:\\Users\\Prajanya\\OneDrive\\Pictures\\market.png")); // NOI18N
        jPanel8.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, -1, -1));

        jPanel3.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 630, 800, 160));

        jButton5.setBackground(new java.awt.Color(0, 153, 51));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("+ Add goals");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        jPanel3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 30, -1, -1));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(150, 180, 220), 2));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(30, 58, 138));
        jLabel16.setText("Vacation Fund");
        jPanel9.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(30, 58, 138));
        jLabel21.setText("Travel");
        jPanel9.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel30.setText("$7,500 / $10,000");
        jPanel9.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jProgressBar5.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jProgressBar5.setForeground(new java.awt.Color(59, 130, 246));
        jProgressBar5.setValue(75);
        jProgressBar5.setStringPainted(true);
        jPanel9.add(jProgressBar5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 310, 20));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel33.setText("Due: 2024-12-31");
        jPanel9.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jButton10.setBackground(new java.awt.Color(204, 255, 204));
        jButton10.setForeground(new java.awt.Color(0, 204, 0));
        jButton10.setText("Update");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, -1, -1));

        jSeparator6.setForeground(new java.awt.Color(102, 102, 102));
        jPanel9.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 300, -1));

        jButton11.setForeground(new java.awt.Color(255, 0, 0));
        jButton11.setText("Remove");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, -1, -1));

        jPanel3.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 120, 350, 210));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(150, 180, 220), 2));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(30, 58, 138));
        jLabel40.setText("Vacation Fund");
        jPanel10.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel41.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(30, 58, 138));
        jLabel41.setText("Travel");
        jPanel10.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel42.setText("$7,500 / $10,000");
        jPanel10.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jProgressBar6.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        jProgressBar6.setForeground(new java.awt.Color(59, 130, 246));
        jProgressBar6.setValue(75);
        jProgressBar6.setStringPainted(true);
        jPanel10.add(jProgressBar6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 310, 20));

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel43.setText("Due: 2024-12-31");
        jPanel10.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jButton12.setBackground(new java.awt.Color(204, 255, 204));
        jButton12.setForeground(new java.awt.Color(0, 204, 0));
        jButton12.setText("Update");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, -1, -1));

        jSeparator7.setForeground(new java.awt.Color(102, 102, 102));
        jPanel10.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 300, -1));

        jButton13.setForeground(new java.awt.Color(255, 0, 0));
        jButton13.setText("Remove");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, -1, -1));

        jPanel3.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 360, 350, 210));

        getContentPane().add(jPanel3);
        jPanel3.setBounds(200, 100, 1240, 800);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        updateGoalProgress(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        updateGoalProgress(1);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       updateGoalProgress(2);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        updateGoalProgress(3);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked
        if (goalsController != null) {
            goalsController.showAddGoalDialog();
            SwingUtilities.invokeLater(() -> {
                try {
                    Thread.sleep(500);
                    refreshGoalsDisplay();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
           removeGoal(0); // Remove first goal
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
         removeGoal(1); // Remove second goal
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
         removeGoal(2); // Remove third goal
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
         removeGoal(3); // Remove fourth goal
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        updateGoalProgress(4);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
removeGoal(4);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        updateGoalProgress(5);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        removeGoal(5);
    }//GEN-LAST:event_jButton13ActionPerformed

    // ========================================
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new goals().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button Dashboard;
    private java.awt.Button Documents;
    private java.awt.Button Goals;
    private java.awt.Button Lending;
    private java.awt.Button button1;
    private java.awt.Button button4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar jProgressBar3;
    private javax.swing.JProgressBar jProgressBar4;
    private javax.swing.JProgressBar jProgressBar5;
    private javax.swing.JProgressBar jProgressBar6;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    // End of variables declaration//GEN-END:variables
}
