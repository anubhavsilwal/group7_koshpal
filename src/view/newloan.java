/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.LendingController;
import javax.swing.JOptionPane;


/**
 *
 * @author salmanansari.81
 */
public class newloan extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(newloan.class.getName());
    private LendingController controller;

    /**
     * Creates new form newloan
     */
     
    public newloan() {
        initComponents();
        
    }

    /**
     * This method is called from within the   rm Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        username = new javax.swing.JLabel();
        item = new javax.swing.JLabel();
        DueDate = new javax.swing.JLabel();
        amount = new javax.swing.JLabel();
        confirmButton = new javax.swing.JButton();
        username1 = new javax.swing.JTextField();
        item1 = new javax.swing.JTextField();
        duedate1 = new javax.swing.JTextField();
        amount1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(254, 251, 238));
        jPanel1.setLayout(null);
        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 370, 90);

        jPanel3.setBackground(new java.awt.Color(205, 231, 217));
        jPanel3.setLayout(null);

        username.setText("UserName:");
        jPanel3.add(username);
        username.setBounds(20, 40, 90, 20);

        item.setText("Item:");
        jPanel3.add(item);
        item.setBounds(20, 80, 29, 17);

        DueDate.setText("DueDate:");
        jPanel3.add(DueDate);
        DueDate.setBounds(20, 110, 70, 17);

        amount.setText("Amount:");
        jPanel3.add(amount);
        amount.setBounds(20, 150, 70, 17);

        confirmButton.setBackground(new java.awt.Color(0, 153, 0));
        confirmButton.setText("Confirm");
        jPanel3.add(confirmButton);
        confirmButton.setBounds(140, 250, 75, 23);

        username1.setText(" ");
        username1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                username1ActionPerformed(evt);
            }
        });
        jPanel3.add(username1);
        username1.setBounds(100, 40, 170, 23);

        item1.setText(" ");
        jPanel3.add(item1);
        item1.setBounds(100, 80, 170, 23);

        duedate1.setText(" ");
        jPanel3.add(duedate1);
        duedate1.setBounds(100, 110, 170, 23);

        amount1.setText(" ");
        jPanel3.add(amount1);
        amount1.setBounds(100, 150, 170, 23);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(0, 90, 380, 350);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void username1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_username1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_username1ActionPerformed

    private void duedate1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void amount1ActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    }   
 
    
    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // Get values from the form
        String borrower = username1.getText().trim();
        String itemName = item1.getText().trim();
        String dueDateStr = duedate1.getText().trim();
        String amountStr = amount1.getText().trim();
        
        // Validate all fields
        if (borrower.isEmpty() || itemName.isEmpty() || dueDateStr.isEmpty() || amountStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Validate date format (YYYY-MM-DD)
        if (!dueDateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Please enter date in YYYY-MM-DD format!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            // Parse amount
            double amount = Double.parseDouble(amountStr);
            
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Amount must be greater than 0!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Use the controller to create the loan
            if (controller != null) {
                controller.createLoanFromForm(borrower, itemName, amount, dueDateStr);
                JOptionPane.showMessageDialog(this, "Loan created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                
                // Clear form
                username1.setText("");
                item1.setText("");
                duedate1.setText("");
                amount1.setText("");
                
                // Close the form
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Controller not available!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount (numbers only)!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }                                            

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            newloan form = new newloan();
            form.setVisible(true);
        });
    }

    public void setController(LendingController controller) {
        this.controller = controller;
        System.out.println("Controller set for newloan form");
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DueDate;
    private javax.swing.JLabel amount;
    private javax.swing.JTextField amount1;
    private javax.swing.JButton confirmButton;
    private javax.swing.JTextField duedate1;
    private javax.swing.JLabel item;
    private javax.swing.JTextField item1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel username;
    private javax.swing.JTextField username1;
    // End of variables declaration//GEN-END:variables

 
}
    
     
