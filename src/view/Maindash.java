/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import javax.swing.JPanel;

/**
 *
 * @author anubhavsilwal
 */
public class Maindash extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Maindash.class.getName());

    private int userId = -1;  // -1 means no user logged in
    private String username = "Guest";
    
    /**
     * Creates new form Maindash
     */
    public Maindash() {
        initComponents();
        fixButtonColors();
        addChartToDashboard();
        addButtonListeners();
        
    }
    
    
    private void addButtonListeners() {
    // Dashboard button - refresh charts
    dashboard.addActionListener(e -> refreshAllCharts());
    
    // My Inventory button - open inventory
    myinventory.addActionListener(e -> openInventory());
    
    // Financial Analytics button - open analytics
    jButton1.addActionListener(e -> openAnalytics());
    
    // Documents button - open documents
    jButton2.addActionListener(e -> openDocuments());
    
    // Lending button - open lending
    lending.addActionListener(e -> openLending());
}
    
   private void openInventory() {
    try {
        // Close dashboard
        this.dispose();
        
        // Open inventory
        view.inventory inventoryView = new view.inventory();
        controller.InventoryController inventoryController = new controller.InventoryController(inventoryView);
        inventoryController.setCurrentUserId(this.userId);  // Pass user ID
        inventoryController.open();
        
    } catch (Exception ex) {
        System.out.println("Error opening inventory: " + ex.getMessage());
        javax.swing.JOptionPane.showMessageDialog(this, "Error loading inventory");
    }
}

private void openAnalytics() {
    try {
        // Close dashboard
        this.dispose();
        
        // Open analytics
        view.analytics analyticsView = new view.analytics();
        controller.AnalyticsController analyticsController = new controller.AnalyticsController(analyticsView);
        analyticsView.setVisible(true);
        
    } catch (Exception ex) {
        System.out.println("Error opening analytics: " + ex.getMessage());
        javax.swing.JOptionPane.showMessageDialog(this, "Error loading analytics");
    }
}

private void openDocuments() {
    try {
        // Close dashboard
        this.dispose();
        
        // Open documents
        view.documents documentsView = new view.documents();
        documentsView.setVisible(true);
        
    } catch (Exception ex) {
        System.out.println("Error opening documents: " + ex.getMessage());
        javax.swing.JOptionPane.showMessageDialog(this, "Error loading documents");
    }
}

private void openLending() {
    try {
        // Close dashboard
        this.dispose();
        
        // Open lending
        view.lending lendingView = new view.lending();
        lendingView.setVisible(true);
        
    } catch (Exception ex) {
        System.out.println("Error opening lending: " + ex.getMessage());
        javax.swing.JOptionPane.showMessageDialog(this, "Error loading lending");
    }
}
    
    
    private void fixButtonColors() {
    System.out.println("Fixing button colors...");
    
    // 1. Fix Dashboard button (green)
    dashboard.setContentAreaFilled(true);  // THIS IS IMPORTANT
    dashboard.setOpaque(true);
    dashboard.setBorderPainted(false);
    dashboard.setBackground(new java.awt.Color(93, 177, 132));
    dashboard.setForeground(java.awt.Color.WHITE);
    
    // 2. Fix orange buttons
    java.awt.Color orange = new java.awt.Color(255, 220, 169);
    
    myinventory.setContentAreaFilled(true);
    myinventory.setOpaque(true);
    myinventory.setBorderPainted(false);
    myinventory.setBackground(orange);
    myinventory.setForeground(java.awt.Color.BLACK);
    
    lending.setContentAreaFilled(true);
    lending.setOpaque(true);
    lending.setBorderPainted(false);
    lending.setBackground(orange);
    lending.setForeground(java.awt.Color.BLACK);
    
    jButton1.setContentAreaFilled(true);
    jButton1.setOpaque(true);
    jButton1.setBorderPainted(false);
    jButton1.setBackground(orange);
    jButton1.setForeground(java.awt.Color.BLACK);
    
    jButton2.setContentAreaFilled(true);
    jButton2.setOpaque(true);
    jButton2.setBorderPainted(false);
    jButton2.setBackground(orange);
    jButton2.setForeground(java.awt.Color.BLACK);
    
    // 3. Fix + Income button (green)
    jButton3.setContentAreaFilled(true);
    jButton3.setOpaque(true);
    jButton3.setBorderPainted(false);
    jButton3.setBackground(new java.awt.Color(0, 153, 0));
    jButton3.setForeground(java.awt.Color.WHITE);
    
    // 4. Fix + Expense button (red)
    jButton4.setContentAreaFilled(true);
    jButton4.setOpaque(true);
    jButton4.setBorderPainted(false);
    jButton4.setBackground(new java.awt.Color(204, 0, 0));
    jButton4.setForeground(java.awt.Color.WHITE);
    
    // Force repaint
    dashboard.repaint();
    myinventory.repaint();
    lending.repaint();
    jButton1.repaint();
    jButton2.repaint();
    jButton3.repaint();
    jButton4.repaint();
    
    System.out.println("Button colors should be fixed now");
}
    
    public void setUserId(int userId) {
        this.userId = userId;
        System.out.println("User ID set to: " + userId);
    }
    public void setUsername(String username) {
        this.username = username;
        // Update welcome label if it exists
        if (jLabel2 != null) {
            jLabel2.setText("Welcome back, " + username + "!");
        }
        System.out.println("Username set to: " + username);
    }

    // Optional: Add a method to check if user is logged in
    public boolean isUserLoggedIn() {
        return userId > 0;
    }
    
    private void addChartToDashboard() {
    try {
        // 1. Add Net Worth Chart to jPanel4
        NetWorthChartFrame netWorthChart = new NetWorthChartFrame();
        JPanel netWorthPanel = netWorthChart.getChartPanel();
        
        jPanel4.removeAll();
        jPanel4.setLayout(new java.awt.BorderLayout());
        jPanel4.add(netWorthPanel, java.awt.BorderLayout.CENTER);
        jPanel4.revalidate();
        jPanel4.repaint();
        
        // 2. Add Income vs Expense Chart to jPanel6
        IncomeExpenseChartFrame incomeExpenseChart = new IncomeExpenseChartFrame();
        JPanel incomeExpensePanel = incomeExpenseChart.getChartPanel();
        
        jPanel6.removeAll();
        jLabel4.setVisible(false);
        jPanel6.setLayout(new java.awt.BorderLayout());
        jPanel6.add(incomeExpensePanel, java.awt.BorderLayout.CENTER);
        jPanel6.revalidate();
        jPanel6.repaint();
        
        // 3. Add Asset Distribution Chart to jPanel5
        AssetDistributionChartFrame assetChart = new AssetDistributionChartFrame();
        JPanel assetPanel = assetChart.getChartPanel();
        
        jPanel5.removeAll();
        jLabel3.setVisible(false);
        jPanel5.setLayout(new java.awt.BorderLayout());
        jPanel5.add(assetPanel, java.awt.BorderLayout.CENTER);
        jPanel5.revalidate();
        jPanel5.repaint();
        
        System.out.println("All three charts added successfully!");
        
    } catch (Exception e) {
        System.out.println("Error adding charts: " + e.getMessage());
        showChartErrors();
    }
}
    
    // ============ ERROR HANDLING METHOD (OUTSIDE addChartToDashboard) ============
    private void showChartErrors() {
        // Error message for jPanel4 (Net Worth)
        javax.swing.JLabel errorLabel1 = new javax.swing.JLabel(
            "Net Worth Chart - Data will appear here",
            javax.swing.SwingConstants.CENTER
        );
        errorLabel1.setFont(new java.awt.Font("Helvetica Neue", java.awt.Font.PLAIN, 14));
        errorLabel1.setForeground(new java.awt.Color(102, 102, 102));
        
        jPanel4.removeAll();
        jPanel4.setLayout(new java.awt.BorderLayout());
        jPanel4.add(errorLabel1, java.awt.BorderLayout.CENTER);
        jPanel4.revalidate();
        jPanel4.repaint();
        
        // Error message for jPanel6 (Income vs Expense)
        javax.swing.JLabel errorLabel2 = new javax.swing.JLabel(
            "Income vs Expense Chart - Data will appear here",
            javax.swing.SwingConstants.CENTER
        );
        errorLabel2.setFont(new java.awt.Font("Helvetica Neue", java.awt.Font.PLAIN, 14));
        errorLabel2.setForeground(new java.awt.Color(102, 102, 102));
        
        jPanel6.removeAll();
        jLabel4.setVisible(false);
        jPanel6.setLayout(new java.awt.BorderLayout());
        jPanel6.add(errorLabel2, java.awt.BorderLayout.CENTER);
        jPanel6.revalidate();
        jPanel6.repaint();
    }
    
    // ============ METHOD TO REFRESH CHARTS ============
    public void refreshAllCharts() {
        addChartToDashboard();  // Simple refresh by recreating charts
        System.out.println("Charts refreshed!");
    }
    
    // ============ ADD DASHBOARD BUTTON ACTION ============
    // Add this action listener to your dashboard button
    // You need to modify the dashboard button in GUI Designer
    // OR add this code manually:
    
    /*
    private void dashboardActionPerformed(java.awt.event.ActionEvent evt) {                                         
        refreshAllCharts();  // Refresh charts when dashboard button is clicked
    }
    */
    
    // ============ REST OF YOUR CODE STAYS THE SAME ============
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        dashboard = new javax.swing.JButton();
        myinventory = new javax.swing.JButton();
        lending = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1440, 900));
        setSize(new java.awt.Dimension(1440, 900));

        jPanel1.setBackground(new java.awt.Color(205, 231, 217));
        jPanel1.setMaximumSize(new java.awt.Dimension(1440, 100));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(254, 251, 238));
        jPanel2.setLayout(null);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        jPanel2.add(jLabel7);
        jLabel7.setBounds(30, 0, 100, 100);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/usericon.png"))); // NOI18N
        jLabel8.setText("jLabel8");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(1340, 20, 70, 70);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 1440, 100);

        jPanel3.setBackground(new java.awt.Color(224, 236, 229));
        jPanel3.setLayout(null);

        dashboard.setBackground(new java.awt.Color(93, 177, 132));
        dashboard.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        dashboard.setText("Dashboard");
        dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardActionPerformed(evt);
            }
        });
        jPanel3.add(dashboard);
        dashboard.setBounds(10, 10, 180, 50);

        myinventory.setBackground(new java.awt.Color(255, 220, 169));
        myinventory.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        myinventory.setText("My Inventory");
        jPanel3.add(myinventory);
        myinventory.setBounds(10, 70, 180, 50);

        lending.setBackground(new java.awt.Color(255, 220, 169));
        lending.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        lending.setText("Lending");
        jPanel3.add(lending);
        lending.setBounds(10, 130, 180, 50);

        jButton1.setBackground(new java.awt.Color(255, 220, 169));
        jButton1.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jButton1.setText("Financial Analytics");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);
        jButton1.setBounds(10, 190, 180, 50);

        jButton2.setBackground(new java.awt.Color(255, 220, 169));
        jButton2.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jButton2.setText("Documents");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2);
        jButton2.setBounds(10, 250, 180, 50);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(0, 100, 200, 800);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 2, 18)); // NOI18N
        jLabel1.setText("Networth");
        jPanel4.add(jLabel1);
        jLabel1.setBounds(10, 10, 80, 23);

        jPanel1.add(jPanel4);
        jPanel4.setBounds(230, 150, 700, 410);

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel2.setText("Welcome back, Salman!");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(230, 110, 380, 30);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        jLabel3.setText("Asset Distribution");
        jPanel5.add(jLabel3);
        jLabel3.setBounds(10, 10, 140, 20);

        jPanel1.add(jPanel5);
        jPanel5.setBounds(230, 580, 440, 310);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        jLabel4.setText("Income vs Expense");
        jPanel6.add(jLabel4);
        jLabel4.setBounds(14, 6, 150, 20);

        jPanel1.add(jPanel6);
        jPanel6.setBounds(950, 150, 470, 410);

        jButton3.setBackground(new java.awt.Color(0, 153, 0));
        jButton3.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("+ Income");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(690, 580, 110, 50);

        jButton4.setBackground(new java.awt.Color(204, 0, 0));
        jButton4.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("+ Expense");
        jPanel1.add(jButton4);
        jButton4.setBounds(820, 580, 110, 50);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(null);

        jPanel8.setBackground(new java.awt.Color(0, 153, 0));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel7.add(jPanel8);
        jPanel8.setBounds(0, 0, 20, 60);

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel5.setText("Highest Value Asset");
        jPanel7.add(jLabel5);
        jLabel5.setBounds(40, 20, 200, 23);

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel10.setText("Real Estate");
        jPanel7.add(jLabel10);
        jLabel10.setBounds(320, 0, 130, 30);

        jLabel11.setFont(new java.awt.Font("Helvetica Neue", 1, 20)); // NOI18N
        jLabel11.setText("$43,000");
        jPanel7.add(jLabel11);
        jLabel11.setBounds(330, 30, 100, 20);

        jPanel1.add(jPanel7);
        jPanel7.setBounds(950, 580, 470, 60);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(null);

        jPanel10.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel9.add(jPanel10);
        jPanel10.setBounds(0, 0, 20, 60);

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel6.setText("Total Asset Value");
        jPanel9.add(jLabel6);
        jLabel6.setBounds(40, 20, 170, 20);

        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 1, 20)); // NOI18N
        jLabel12.setText("$230,400");
        jPanel9.add(jLabel12);
        jLabel12.setBounds(320, 20, 110, 20);

        jPanel1.add(jPanel9);
        jPanel9.setBounds(950, 660, 470, 60);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(null);

        jPanel12.setBackground(new java.awt.Color(255, 102, 0));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanel11.add(jPanel12);
        jPanel12.setBounds(0, 0, 20, 60);

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel9.setText("Average Asset Value");
        jPanel11.add(jLabel9);
        jLabel9.setBounds(40, 20, 210, 20);

        jLabel13.setFont(new java.awt.Font("Helvetica Neue", 1, 20)); // NOI18N
        jLabel13.setText("$23,730");
        jPanel11.add(jLabel13);
        jLabel13.setBounds(330, 20, 110, 30);

        jPanel1.add(jPanel11);
        jPanel11.setBounds(950, 740, 470, 60);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setLayout(null);

        jLabel14.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel14.setText("Assets");
        jPanel13.add(jLabel14);
        jLabel14.setBounds(20, 10, 70, 20);

        jLabel17.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel17.setText("12");
        jPanel13.add(jLabel17);
        jLabel17.setBounds(170, 10, 30, 20);

        jPanel16.setBackground(new java.awt.Color(51, 153, 0));
        jPanel16.setForeground(new java.awt.Color(51, 153, 0));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel13.add(jPanel16);
        jPanel16.setBounds(120, 0, 10, 40);

        jPanel1.add(jPanel13);
        jPanel13.setBounds(710, 660, 210, 40);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setLayout(null);

        jLabel15.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel15.setText("Goals");
        jPanel14.add(jLabel15);
        jLabel15.setBounds(20, 10, 80, 17);

        jLabel18.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel18.setText("4");
        jPanel14.add(jLabel18);
        jLabel18.setBounds(180, 10, 30, 23);

        jPanel17.setBackground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel14.add(jPanel17);
        jPanel17.setBounds(120, 0, 10, 40);

        jPanel1.add(jPanel14);
        jPanel14.setBounds(710, 720, 210, 40);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setLayout(null);

        jLabel16.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel16.setText("Loan Outs");
        jPanel15.add(jLabel16);
        jLabel16.setBounds(10, 10, 100, 23);

        jLabel19.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel19.setText("6");
        jPanel15.add(jLabel19);
        jLabel19.setBounds(180, 10, 30, 23);

        jPanel19.setBackground(new java.awt.Color(255, 102, 0));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel15.add(jPanel19);
        jPanel19.setBounds(120, 0, 10, 40);

        jPanel1.add(jPanel15);
        jPanel15.setBounds(710, 780, 210, 40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1440, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void dashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dashboardActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new Maindash().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dashboard;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JButton lending;
    private javax.swing.JButton myinventory;
    // End of variables declaration//GEN-END:variables
}
