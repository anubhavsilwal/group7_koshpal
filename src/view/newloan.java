/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import javax.swing.JOptionPane;


/**
 *
 * @author salmanansari.81
 */
public class newloan extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(newloan.class.getName());

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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        item = new javax.swing.JTextField();
        amount = new javax.swing.JTextField();
        username = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        due = new javax.swing.JTextField();
        confirm = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(254, 251, 238));
        jPanel1.setLayout(null);
        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 400, 90);

        jPanel2.setBackground(new java.awt.Color(205, 231, 217));
        jPanel2.setLayout(null);

        jLabel1.setText("Item:");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(40, 80, 80, 20);

        jLabel2.setText("Amount:");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(40, 180, 80, 20);

        jLabel3.setText("User Name:");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(40, 30, 80, 20);

        item.setText(" ");
        item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemActionPerformed(evt);
            }
        });
        jPanel2.add(item);
        item.setBounds(140, 80, 200, 23);

        amount.setText(" ");
        amount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountActionPerformed(evt);
            }
        });
        jPanel2.add(amount);
        amount.setBounds(140, 180, 200, 23);

        username.setText(" ");
        jPanel2.add(username);
        username.setBounds(140, 30, 200, 23);

        jLabel4.setText("Due Date:");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(40, 130, 80, 20);

        due.setText(" ");
        due.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dueActionPerformed(evt);
            }
        });
        jPanel2.add(due);
        due.setBounds(140, 130, 200, 23);

        confirm.setBackground(new java.awt.Color(0, 153, 0));
        confirm.setText("Confirm");
        confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmActionPerformed(evt);
            }
        });
        jPanel2.add(confirm);
        confirm.setBounds(160, 273, 90, 30);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 90, 400, 350);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void amountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountActionPerformed
        // TODO add your handling code here:
             validateAmount();
    }//GEN-LAST:event_amountActionPerformed

    private void dueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dueActionPerformed
        // TODO add your handling code here:
                validateDate();

    }//GEN-LAST:event_dueActionPerformed

    private void itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemActionPerformed
        // TODO add your handling code here:
                validateItemName();

    }//GEN-LAST:event_itemActionPerformed

    private void confirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmActionPerformed
        // TODO add your handling code here:
               createNewLoan();

    }//GEN-LAST:event_confirmActionPerformed

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {
        validateUsername();
    }
    
    // SIMPLE VALIDATION METHODS
    private void validateUsername() {
        if (username.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "User name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            username.requestFocus();
        }
    }
    
    private void validateItemName() {
        if (item.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Item name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            item.requestFocus();
        }
    }
    
    private void validateAmount() {
        try {
            String amountText = amount.getText().trim();
            if (amountText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Amount cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                amount.requestFocus();
                return;
            }
            
            double amt = Double.parseDouble(amountText);
            if (amt <= 0) {
                JOptionPane.showMessageDialog(this, "Amount must be greater than 0!", "Error", JOptionPane.ERROR_MESSAGE);
                amount.requestFocus();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount (numbers only)!", "Error", JOptionPane.ERROR_MESSAGE);
            amount.requestFocus();
        }
    }
    
    private void validateDate() {
        String dateText = due.getText().trim();
        if (dateText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Due date cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            due.requestFocus();
        } else if (!dateText.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(this, "Please enter date in YYYY-MM-DD format!", "Error", JOptionPane.ERROR_MESSAGE);
            due.requestFocus();
        }
    }
    
    private void createNewLoan() {
        // Validate all fields
        validateUsername();
        validateItemName();
        validateAmount();
        validateDate();
        
        // Check if all fields are valid
        if (!username.getText().trim().isEmpty() && 
            !item.getText().trim().isEmpty() && 
            !amount.getText().trim().isEmpty() && 
            !due.getText().trim().isEmpty()) {
            
            try {
                double amt = Double.parseDouble(amount.getText().trim());
                if (amt > 0) {
                    JOptionPane.showMessageDialog(this, 
                        "New Loan Created Successfully!\n" +
                        "Borrower: " + username.getText() + "\n" +
                        "Item: " + item.getText() + "\n" +
                        "Amount: $" + amount.getText() + "\n" +
                        "Due Date: " + due.getText(), 
                        "Success", 
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    // Clear fields for next entry
                    username.setText("");
                    item.setText("");
                    amount.setText("");
                    due.setText("");
                    
                    this.dispose(); // Close the form
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid amount format!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // Getter methods for form fields (FIXED - using correct field names)
    public String getBorrowerName() {
        return username.getText().trim();
    }
    
    public String getItemName() {
        return item.getText().trim();
    }
    
    public double getAmount() {
        try {
            return Double.parseDouble(amount.getText().trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
    
    public String getDueDate() {
        return due.getText().trim();
    }
    
    public boolean isFormValid() {
        return !username.getText().trim().isEmpty() && 
               !item.getText().trim().isEmpty() && 
               !amount.getText().trim().isEmpty() && 
               !due.getText().trim().isEmpty();
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            newloan form = new newloan();
            form.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amount;
    private javax.swing.JButton confirm;
    private javax.swing.JTextField due;
    private javax.swing.JTextField item;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
    
     
