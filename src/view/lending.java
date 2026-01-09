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
import java.time.LocalDate;


/**
 *
 * @author anubhavsilwal
 */

public class lending extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(lending.class.getName());
    private LendingController controller;   
    
    
    public lending() {
        initComponents();
        setTitle("Lending Management System");
        setSize(1300, 900);
        setLocationRelativeTo(null);
        setVisible(true);
         
        recordpanalreal.setLayout(null);
        
        System.out.println("✅ lending window created");
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        myInventerybtn = new javax.swing.JButton();
        lendingbtn = new javax.swing.JButton();
        goalbtn = new javax.swing.JButton();
        financialAnalyticsbtn = new javax.swing.JButton();
        documentsbtn = new javax.swing.JButton();
        dashboardbtn = new javax.swing.JButton();
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
        searchbar = new javax.swing.JTextField();
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

        myInventerybtn.setBackground(new java.awt.Color(255, 220, 169));
        myInventerybtn.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        myInventerybtn.setText("My Inventery");
        myInventerybtn.setSize(new java.awt.Dimension(200, 50));
        myInventerybtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myInventerybtnActionPerformed(evt);
            }
        });
        jPanel1.add(myInventerybtn);
        myInventerybtn.setBounds(0, 80, 200, 50);

        lendingbtn.setBackground(new java.awt.Color(93, 177, 132));
        lendingbtn.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        lendingbtn.setText("Lending");
        lendingbtn.setSize(new java.awt.Dimension(200, 50));
        lendingbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lendingbtnActionPerformed(evt);
            }
        });
        jPanel1.add(lendingbtn);
        lendingbtn.setBounds(0, 150, 200, 50);

        goalbtn.setBackground(new java.awt.Color(255, 220, 169));
        goalbtn.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        goalbtn.setText("Goal");
        goalbtn.setSize(new java.awt.Dimension(200, 50));
        goalbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goalbtnActionPerformed(evt);
            }
        });
        jPanel1.add(goalbtn);
        goalbtn.setBounds(0, 220, 200, 50);

        financialAnalyticsbtn.setBackground(new java.awt.Color(255, 220, 169));
        financialAnalyticsbtn.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        financialAnalyticsbtn.setText("Financial Analytics");
        financialAnalyticsbtn.setSize(new java.awt.Dimension(200, 50));
        financialAnalyticsbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                financialAnalyticsbtnActionPerformed(evt);
            }
        });
        jPanel1.add(financialAnalyticsbtn);
        financialAnalyticsbtn.setBounds(0, 290, 200, 50);

        documentsbtn.setBackground(new java.awt.Color(255, 220, 169));
        documentsbtn.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        documentsbtn.setText("Documents");
        documentsbtn.setSize(new java.awt.Dimension(200, 50));
        documentsbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                documentsbtnActionPerformed(evt);
            }
        });
        jPanel1.add(documentsbtn);
        documentsbtn.setBounds(0, 360, 200, 50);

        dashboardbtn.setBackground(new java.awt.Color(255, 220, 169));
        dashboardbtn.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        dashboardbtn.setText("Dashboard");
        dashboardbtn.setSize(new java.awt.Dimension(200, 50));
        dashboardbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardbtnActionPerformed(evt);
            }
        });
        jPanel1.add(dashboardbtn);
        dashboardbtn.setBounds(0, 10, 200, 50);

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

        newLoan.setBackground(new java.awt.Color(28, 163, 96));
        newLoan.setForeground(new java.awt.Color(51, 51, 0));
        newLoan.setText("  New Loan");
        newLoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newLoanActionPerformed(evt);
            }
        });
        jPanel2.add(newLoan);
        newLoan.setBounds(1090, 30, 96, 40);

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

        searchbar.setText("Search Record");
        searchbar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbarActionPerformed(evt);
            }
        });
        jPanel2.add(searchbar);
        searchbar.setBounds(840, 30, 200, 40);

        jScrollPane1.setViewportView(recordpanalreal);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(70, 250, 1100, 550);

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

    // Add this method to set controller
    public void setController(LendingController controller) {
        this.controller = controller;
        System.out.println("✅ Controller set in lending window");
        loadRecordCards();
    }
    
    // Add this method to get controller
    public LendingController getController() {
        return controller;
    }
    
    // Add this method to load record cards
    public void loadRecordCards() {
        System.out.println("🔄 Loading record cards...");
        
        if (recordpanalreal == null) {
            System.out.println("❌ recordpanalreal is null!");
            return;
        }        
         
        recordpanalreal.removeAll();
        recordpanalreal.setLayout(new javax.swing.BoxLayout(recordpanalreal, javax.swing.BoxLayout.Y_AXIS));
        
        if (controller != null) {
            List<Loan> loans = controller.getLoans();
            System.out.println("DEBUG: Number of loans to display: " + loans.size());
            
            // Update stats
            updateDashboardStats();
            
            if (loans.isEmpty()) {
                System.out.println("DEBUG: No loans found.");
                // Add a message when no loans
                javax.swing.JLabel noDataLabel = new javax.swing.JLabel("No loans found. Click 'New Loan' to add one.");
                noDataLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 16));
                noDataLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                noDataLabel.setForeground(new java.awt.Color(100, 100, 100));
                noDataLabel.setPreferredSize(new java.awt.Dimension(1100, 200));
                recordpanalreal.add(noDataLabel);
            } else {
                for (Loan loan : loans) {
                    System.out.println("DEBUG: Creating card for: " + loan.getBorrowerName() + " - " + loan.getItemName());
                    
                    RecordCard card = new RecordCard(loan, this);
                    card.setMaximumSize(new java.awt.Dimension(1100, 120));
                    card.setPreferredSize(new java.awt.Dimension(1100, 120));
                    card.setMinimumSize(new java.awt.Dimension(1100, 120));
                    recordpanalreal.add(card);
                    
                    // Add spacing between cards
                    recordpanalreal.add(javax.swing.Box.createRigidArea(new java.awt.Dimension(0, 10)));
                }
            }
        } else {
            System.out.println("DEBUG: Controller is null!");
            javax.swing.JLabel errorLabel = new javax.swing.JLabel("Controller not initialized. Please restart the application.");
            errorLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 16));
            errorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            errorLabel.setForeground(new java.awt.Color(255, 0, 0));
            recordpanalreal.add(errorLabel);
        }
        
        recordpanalreal.revalidate();
        recordpanalreal.repaint();
        System.out.println("✅ Record cards loaded");
    }

    private void updateDashboardStats() {
        if (controller != null) {
            try {
                List<Loan> loans = controller.getLoans();
                
                double totalAmount = 0;
                int activeLoans = 0;
                int overdueLoans = 0;
                LocalDate today = LocalDate.now();
                
                for (Loan loan : loans) {
                    totalAmount += loan.getAmount();
                    if ("Active".equals(loan.getStatus())) {
                        activeLoans++;
                        if (loan.getDueDate().isBefore(today)) {
                            overdueLoans++;
                        }
                    }
                }
                
                jLabel7.setText("$ " + String.format("%.2f", totalAmount));
                jLabel8.setText(" " + activeLoans);
                jLabel6.setText("" + overdueLoans);
                
                System.out.println("📈 Stats - Total: $" + totalAmount + 
                                 ", Active: " + activeLoans + 
                                 ", Overdue: " + overdueLoans);
            } catch (Exception e) {
                System.err.println("❌ Error updating stats: " + e.getMessage());
            }
        }
    }
    
    
    // Add this method to refresh the view
    public void refreshView() {
        System.out.println("🔄 Refreshing view...");
        loadRecordCards();
    }
    
    // Add this method to update statistics
    private void updateStats() {
        if (controller != null) {
            try {
                double totalAmount = controller.getLoanDAO().getTotalLoanedAmount(1); // user ID 1
                int activeLoans = controller.getLoanDAO().getActiveLoansCount(1);
                int overdueLoans = controller.getLoanDAO().getOverdueLoansCount(1);
                
                jLabel7.setText("$ " + String.format("%.2f", totalAmount));
                jLabel8.setText(" " + activeLoans);
                jLabel6.setText("" + overdueLoans);
                
                System.out.println("DEBUG: Stats updated - Total: $" + totalAmount + 
                                 ", Active: " + activeLoans + 
                                 ", Overdue: " + overdueLoans);
            } catch (Exception e) {
                System.err.println("Error updating stats: " + e.getMessage());
            }
        }
    }
    
    
    
    
    private void myInventerybtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myInventerybtnActionPerformed
         // Navigate to My Inventory
    System.out.println("My Inventory button clicked");
    if (controller != null) {
        controller.navigateToMyInventory();
    } else {
        JOptionPane.showMessageDialog(this, "My Inventory\n\nTrack your items and belongings", 
            "My Inventory", JOptionPane.INFORMATION_MESSAGE);
    }
    }//GEN-LAST:event_myInventerybtnActionPerformed

    private void lendingbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lendingbtnActionPerformed
         // Already on Lending page - just refresh
    System.out.println("Lending button clicked - refreshing");
    refreshView();
    }//GEN-LAST:event_lendingbtnActionPerformed

    private void goalbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goalbtnActionPerformed
         // Navigate to Goal
    System.out.println("Goal button clicked");
    if (controller != null) {
        controller.navigateToGoal();
    } else {
        JOptionPane.showMessageDialog(this, "Goals\n\nSet and track your financial goals", 
            "Goals", JOptionPane.INFORMATION_MESSAGE);
    }
    }//GEN-LAST:event_goalbtnActionPerformed

    private void financialAnalyticsbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financialAnalyticsbtnActionPerformed
         // Navigate to Financial Analytics
    System.out.println("Financial Analytics button clicked");
    if (controller != null) {
        controller.navigateToFinancialAnalytics();
    } else {
        JOptionPane.showMessageDialog(this, "Financial Analytics\n\nView charts and reports", 
            "Financial Analytics", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_financialAnalyticsbtnActionPerformed

    private void documentsbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_documentsbtnActionPerformed
        // Navigate to Documents
    System.out.println("Documents button clicked");
    if (controller != null) {
        controller.navigateToDocuments();
    } else {
        JOptionPane.showMessageDialog(this, "Documents\n\nManage your loan agreements and receipts", 
            "Documents", JOptionPane.INFORMATION_MESSAGE);
    }
    }//GEN-LAST:event_documentsbtnActionPerformed

    private void dashboardbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardbtnActionPerformed
         // Navigate to Dashboard
    System.out.println("Dashboard button clicked");
    if (controller != null) {
        controller.navigateToDashboard();
    } else {
        JOptionPane.showMessageDialog(this, "Dashboard\n\nOverview of your financial activities", 
            "Dashboard", JOptionPane.INFORMATION_MESSAGE);
    }
    }//GEN-LAST:event_dashboardbtnActionPerformed

    
    
    private void newLoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newLoanActionPerformed
        System.out.println("🆕 New Loan button clicked!");
        
        if (controller == null) {
            System.out.println("❌ Controller is null!");
            JOptionPane.showMessageDialog(this, "Application error. Please restart.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            newloan loanForm = new newloan(controller);
            loanForm.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
            
            loanForm.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    System.out.println("🔄 New loan form closed, refreshing...");
                    refreshView();
                }
            });
            
            loanForm.setVisible(true);
            loanForm.setLocationRelativeTo(null);
        });

           
    }//GEN-LAST:event_newLoanActionPerformed
   


    private void searchbarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchbarActionPerformed
        String searchText = searchbar.getText();
        System.out.println("🔍 Searching for: " + searchText);
        JOptionPane.showMessageDialog(this, "Search coming soon!", "Info", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_searchbarActionPerformed
     
     

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
            java.util.logging.Logger.getLogger(lending.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        // Create and show the form
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new lending().setVisible(true);
            }
        });
   }
   
   
         
   
    
    // Method to refresh the view (can be called from controller)
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dashboardbtn;
    private javax.swing.JButton documentsbtn;
    private javax.swing.JButton financialAnalyticsbtn;
    private javax.swing.JButton goalbtn;
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
    private javax.swing.JButton lendingbtn;
    private javax.swing.JButton myInventerybtn;
    private javax.swing.JButton newLoan;
    private javax.swing.JPanel recordpanalreal;
    private javax.swing.JTextField searchbar;
    // End of variables declaration//GEN-END:variables

}
 
