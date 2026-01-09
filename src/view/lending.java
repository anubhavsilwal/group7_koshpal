/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

// Add this import with your other imports

import view.newloan;
import controller.LendingController;
import model.Loan;
import javax.swing.*;
import java.sql.Connection;
import java.util.List;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 *
 * @author anubhavsilwal
 */

public class lending extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(lending.class.getName());
    private LendingController controller;   
    
    
    public lending() {
        initComponents();
        // Set layout for record panel
        recordpanalreal.setLayout(null);
    }
   
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        myInventery = new javax.swing.JButton();
        lending = new javax.swing.JButton();
        goal = new javax.swing.JButton();
        financialAnalytics = new javax.swing.JButton();
        documents = new javax.swing.JButton();
        dashboard = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        newLoan = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        recordpanalreal = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(254, 251, 238));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(224, 236, 229));
        jPanel1.setSize(new java.awt.Dimension(200, 800));
        jPanel1.setLayout(null);

        myInventery.setBackground(new java.awt.Color(255, 220, 169));
        myInventery.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        myInventery.setText("My Inventery");
        myInventery.setSize(new java.awt.Dimension(200, 50));
        myInventery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myInventeryActionPerformed(evt);
            }
        });
        jPanel1.add(myInventery);
        myInventery.setBounds(0, 80, 200, 50);

        lending.setBackground(new java.awt.Color(93, 177, 132));
        lending.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        lending.setText("Lending");
        lending.setSize(new java.awt.Dimension(200, 50));
        lending.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lendingActionPerformed(evt);
            }
        });
        jPanel1.add(lending);
        lending.setBounds(0, 150, 200, 50);

        goal.setBackground(new java.awt.Color(255, 220, 169));
        goal.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        goal.setText("Goal");
        goal.setSize(new java.awt.Dimension(200, 50));
        goal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goalActionPerformed(evt);
            }
        });
        jPanel1.add(goal);
        goal.setBounds(0, 220, 200, 50);

        financialAnalytics.setBackground(new java.awt.Color(255, 220, 169));
        financialAnalytics.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        financialAnalytics.setText("Financial Analytics");
        financialAnalytics.setSize(new java.awt.Dimension(200, 50));
        financialAnalytics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                financialAnalyticsActionPerformed(evt);
            }
        });
        jPanel1.add(financialAnalytics);
        financialAnalytics.setBounds(0, 290, 200, 50);

        documents.setBackground(new java.awt.Color(255, 220, 169));
        documents.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        documents.setText("Documents");
        documents.setSize(new java.awt.Dimension(200, 50));
        documents.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                documentsActionPerformed(evt);
            }
        });
        jPanel1.add(documents);
        documents.setBounds(0, 360, 200, 50);

        dashboard.setBackground(new java.awt.Color(255, 220, 169));
        dashboard.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        dashboard.setText("Dashboard");
        dashboard.setSize(new java.awt.Dimension(200, 50));
        dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardActionPerformed(evt);
            }
        });
        jPanel1.add(dashboard);
        dashboard.setBounds(0, 10, 200, 50);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 120, 200, 800);

        jPanel2.setBackground(new java.awt.Color(205, 231, 217));
        jPanel2.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Georgia", 0, 36)); // NOI18N
        jLabel1.setText("Lending & Borrowing");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(34, 22, 344, 41);

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        jLabel2.setText("Track loans you’ve given and items borrowed  ");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(51, 68, 387, 20);

        newLoan.setBackground(new java.awt.Color(0, 102, 51));
        newLoan.setForeground(new java.awt.Color(242, 242, 242));
        newLoan.setText("  New Loan");
        newLoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newLoanActionPerformed(evt);
            }
        });
        jPanel2.add(newLoan);
        newLoan.setBounds(1085, 30, 96, 40);

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel4.setText("Total Loaned out");

        jLabel7.setFont(new java.awt.Font("Hiragino Sans TC", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 204, 51));
        jLabel7.setText("$ 23,000");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel4)
                .addGap(12, 12, 12)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.add(jPanel4);
        jPanel4.setBounds(110, 110, 240, 89);

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel3.setText("Active loan");

        jLabel8.setFont(new java.awt.Font("Hiragino Sans TC", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 204, 51));
        jLabel8.setText(" 3");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(12, 12, 12)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel5);
        jPanel5.setBounds(500, 110, 228, 89);

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel5.setText("OverDue");

        jLabel6.setFont(new java.awt.Font("Hiragino Sans TC", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("1");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(12, 12, 12)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel6);
        jPanel6.setBounds(930, 110, 217, 95);

        search.setText("Search Record");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        jPanel2.add(search);
        search.setBounds(790, 30, 200, 40);

        jScrollPane1.setViewportView(recordpanalreal);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(70, 250, 1100, 520);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(198, 121, 1250, 800);

        jPanel12.setBackground(new java.awt.Color(254, 251, 238));
        jPanel12.setLayout(null);

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        jLabel25.setText("jLabel25");
        jPanel12.add(jLabel25);
        jLabel25.setBounds(10, 10, 100, 84);

        getContentPane().add(jPanel12);
        jPanel12.setBounds(0, 0, 1448, 120);

        setBounds(0, 0, 1436, 947);
    }// </editor-fold>//GEN-END:initComponents

    private void myInventeryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myInventeryActionPerformed
        if (controller != null) {
            controller.navigateToMyInventory();
        } else {
            JOptionPane.showMessageDialog(this, "My Inventory - Coming Soon");
        }
    }//GEN-LAST:event_myInventeryActionPerformed

    private void lendingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lendingActionPerformed
        if (controller != null) {
            controller.navigateToLending();
        }
    }//GEN-LAST:event_lendingActionPerformed

    private void goalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goalActionPerformed
        if (controller != null) {
            controller.navigateToGoal();
        } else {
            JOptionPane.showMessageDialog(this, "Goal - Coming Soon");
        }
    }//GEN-LAST:event_goalActionPerformed

    private void financialAnalyticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financialAnalyticsActionPerformed
         if (controller != null) {
            controller.navigateToFinancialAnalytics();
        } else {
            JOptionPane.showMessageDialog(this, "Financial Analytics - Coming Soon");
        }
    }//GEN-LAST:event_financialAnalyticsActionPerformed

    private void documentsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_documentsActionPerformed
       if (controller != null) {
            controller.navigateToDocuments();
        } else {
            JOptionPane.showMessageDialog(this, "Documents - Coming Soon");
        }
    }//GEN-LAST:event_documentsActionPerformed

    private void dashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardActionPerformed
        if (controller != null) {
            controller.navigateToDashboard();
        } else {
            JOptionPane.showMessageDialog(this, "Dashboard - Coming Soon");
        }
    }//GEN-LAST:event_dashboardActionPerformed

    
    
    private void newLoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newLoanActionPerformed
        System.out.println("New Loan button clicked!");
    
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            newloan loanForm = new newloan();
            
            if (controller != null) {
                loanForm.setController(controller);
            }
            
            // Set the form to dispose on close (not exit)
            loanForm.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            
            loanForm.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    System.out.println("New loan form closed, refreshing data...");
                    refreshView();
                }
            });
            
            loanForm.setVisible(true);
            loanForm.setLocationRelativeTo(null);
        }
    }); 

           
    }//GEN-LAST:event_newLoanActionPerformed
   
    
    // Method to refresh the view

    /**
     *
     */
public void refreshView() {
    if (controller != null) {
        controller.refreshView();
    } else {
        loadRecordCards(); // Fallback
    }
    System.out.println("View refreshed");
}
    
 
    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
         
    }//GEN-LAST:event_searchActionPerformed
     
 
/**
 * @param args the command line arguments
 */
  
  
   public static void main(String args[]) {
   /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(lending.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new lending().setVisible(true);
            }
        });
}
   
 
   // ========== ADDED METHODS FOR RECORD CARDS ==========
    
    public javax.swing.JPanel getRecordPanel() {
        return recordpanalreal;
    }
    
    public void addRecordCard(String borrower, String item, String dueDate, double amount) {
        try {
            // Create a new RecordCard
            RecordCard card = new RecordCard();
        
        card.setRecordData(borrower, item, dueDate, amount);
        
        // Rest of your code remains the same...
        int cardHeight = 120;
        int spacing = 10;
        int cardCount = recordpanalreal.getComponentCount();
        int yPos = cardCount * (cardHeight + spacing);
        
        card.setBounds(0, yPos, 1100, cardHeight);
        recordpanalreal.add(card);
        recordpanalreal.setPreferredSize(new java.awt.Dimension(1100, yPos + cardHeight + spacing));
        recordpanalreal.revalidate();
        recordpanalreal.repaint();
        
        System.out.println("Added RecordCard for: " + borrower + " - " + item);
        
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error creating record card: " + e.getMessage());
    }
}
    
    // Add this method to load existing loans
    public void loadRecordCards() {
        try {
            // Clear existing cards
            recordpanalreal.removeAll();
            
            // Check if controller exists
            if (controller == null) {
                System.out.println("Controller is null in loadRecordCards");
                
                // Add sample data for testing
                addRecordCard("Salman", "Macbook Air", "2026-11-17", 5000.00);
                addRecordCard("Sandesh", "Photography Equipment", "2025-09-22", 7000.00);
                addRecordCard("Sameep", "Designing Material", "2025-12-27", 11000.00);
                return;
            }
            
            // Get loans from controller
            List<Loan> loans = controller.getLoans();
            
            if (loans == null || loans.isEmpty()) {
                System.out.println("No loans found");
                return;
            }
            
            // Add cards for each loan
            for (Loan loan : loans) {
                addRecordCard(
                    loan.getBorrowerName(),
                    loan.getItemName(),
                    loan.getDueDate().toString(),
                    loan.getAmount()
                );
            }
              
            // Refresh the panel
            recordpanalreal.revalidate();
            recordpanalreal.repaint();
            
            System.out.println("Loaded " + loans.size() + " record cards");
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading record cards: " + e.getMessage());
        }
    }
    
    // Add a setter for the controller
    public void setController(LendingController controller) {
        this.controller = controller;
        System.out.println("Controller set in lending view");
        
        // Load record cards after controller is set
        loadRecordCards();
    }
    
    // Method to refresh the view (can be called from controller)
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dashboard;
    private javax.swing.JButton documents;
    private javax.swing.JButton financialAnalytics;
    private javax.swing.JButton goal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton lending;
    private javax.swing.JButton myInventery;
    private javax.swing.JButton newLoan;
    private javax.swing.JPanel recordpanalreal;
    private javax.swing.JTextField search;
    // End of variables declaration//GEN-END:variables

}
 
