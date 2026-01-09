
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import java.io.File;

import controller.DocumentController;
import model.Document;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.logging.Logger;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author anubhavsilwal
 */
public class documents extends javax.swing.JFrame {
  
    private static final Logger logger = Logger.getLogger(documents.class.getName());
    private DocumentController controller = new DocumentController();
    private List<Document> documentList = new ArrayList<>();
    private JPanel documentPanelContainer;   
    
    private String imagePath = "";
    /**
     * Creates new form inventory
     */
    public documents() {
        initComponents();
        addActionListeners();
        setupIcons();
        initializeDocumentPanel();
       
            documentList = controller.getAllDocuments();
        refreshDocumentDisplay();
    }
    
    private void initializeDocumentPanel() {
        documentPanelContainer = new JPanel();
        documentPanelContainer.setLayout(new BoxLayout(documentPanelContainer, BoxLayout.Y_AXIS));
        documentPanelContainer.setBackground(Color.WHITE);
        jScrollPane1.setViewportView(documentPanelContainer);
   
    }
public void addNewDocument(String title, String category, String date, String amount, String imagePath) {
 controller.addDocument(title, category, date, amount, imagePath);
    documentList = controller.getAllDocuments();
    refreshDocumentDisplay();  
}
    
    private void refreshDocumentDisplay() {
        documentPanelContainer.removeAll();
        
        for (Document doc : documentList) {
            JPanel docPanel = createDocumentPanel(doc);
            documentPanelContainer.add(docPanel);
            documentPanelContainer.add(Box.createRigidArea(new Dimension(0, 10))); // Spacing
        }
        
        
        documentPanelContainer.revalidate();
        documentPanelContainer.repaint();
    }
    
  private JPanel createDocumentPanel(Document doc) {


    JPanel panel = new JPanel(new BorderLayout(10, 0));
    panel.setBackground(Color.WHITE);
    panel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
    panel.setPreferredSize(new Dimension(1200, 80));
    panel.setMaximumSize(new Dimension(1200, 80));

    // Icon
    JLabel pdfIcon = new JLabel();
    try {
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/images/pdf.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        pdfIcon.setIcon(new ImageIcon(scaledImage));
    } catch (Exception e) {
        pdfIcon.setText("📄");
        pdfIcon.setFont(new Font("Segoe UI", Font.PLAIN, 24));
    }

    // Text
    JLabel titleLabel = new JLabel(doc.getTitle());
    titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));

    JLabel categoryLabel = new JLabel(doc.getCategory());
    categoryLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    categoryLabel.setForeground(Color.GRAY);

    JLabel dateLabel = new JLabel(doc.getDate());
    dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
    dateLabel.setForeground(Color.LIGHT_GRAY);

    JPanel leftPanel = new JPanel();
    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
    leftPanel.setOpaque(false);
    leftPanel.add(titleLabel);
    leftPanel.add(categoryLabel);
    leftPanel.add(dateLabel);

    // ================= RIGHT SIDE =================
    JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 20));
    rightPanel.setOpaque(false);

    JLabel lblAmount = new JLabel("Rs " + doc.getAmount());
    lblAmount.setFont(new Font("Segoe UI", Font.BOLD, 20));
    lblAmount.setForeground(new Color(0, 128, 0));

    JButton btnView = new JButton("View");
    btnView.addActionListener(e -> showDocumentDetails(doc));

    JButton btnDelete = new JButton("Delete");
    btnDelete.setBackground(new Color(200, 50, 50));
    btnDelete.setForeground(Color.WHITE);

    btnDelete.addActionListener(e -> {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Delete this document?",
                "Confirm",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            controller.deleteDocument(doc.getId()); // DB delete
            documentList = controller.getAllDocuments();
            refreshDocumentDisplay();
        }
    });

    rightPanel.add(lblAmount);
    rightPanel.add(btnView);
    rightPanel.add(btnDelete);

    panel.add(pdfIcon, BorderLayout.WEST);
    panel.add(leftPanel, BorderLayout.CENTER);
    panel.add(rightPanel, BorderLayout.EAST);

    // 🔥 THIS LINE WAS MISSING
    return panel;

 }
private void showDocumentDetails(Document doc) {
    // Main panel
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    
    // Document info
    panel.add(new JLabel("Title: " + doc.getTitle()));
    panel.add(new JLabel("Category: " + doc.getCategory()));
    panel.add(new JLabel("Date: " + doc.getDate()));
    panel.add(new JLabel("Amount: Rs " + doc.getAmount()));

    panel.add(Box.createRigidArea(new Dimension(0, 10))); // spacing
    
    // Image
    if (doc.getImagePath() != null && !doc.getImagePath().isEmpty() 
        && new File(doc.getImagePath()).exists()) {
        ImageIcon icon = new ImageIcon(doc.getImagePath());
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(300, 300, Image.SCALE_SMOOTH); // simple scaling
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImg));
        panel.add(imageLabel);
    } else {
        panel.add(new JLabel("No image available"));
    }

    // Show dialog
    JOptionPane.showMessageDialog(this, panel, "Document Details", JOptionPane.PLAIN_MESSAGE);
}

    private void addActionListeners() {
        // Category filter buttons
        jButton1.addActionListener(e -> showAllDocuments());
        jButton7.addActionListener(e -> filterByCategory("Life Style"));
        jButton6.addActionListener(e -> filterByCategory("Health"));
        jButton5.addActionListener(e -> filterByCategory("Financial"));
        jButton8.addActionListener(e -> filterByCategory("Utilities"));
        jButton2.addActionListener(e -> filterByCategory("Others"));
        
        // Add Expense button
        btnAddExpense.addActionListener(e -> {
            AddExpenseForm addExpense = new AddExpenseForm(this);
            addExpense.setVisible(true);
        });
        
        // Search functionality
        jTextField1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jTextField1.getText().equals("Search Receipts....")) {
                    jTextField1.setText("");
                    jTextField1.setForeground(Color.BLACK);
                }
            }
            
            @Override
            public void focusLost(FocusEvent e) {
                if (jTextField1.getText().isEmpty()) {
                    jTextField1.setText("Search Receipts....");
                    jTextField1.setForeground(Color.GRAY);
                }
            }
        });
        
        jTextField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                searchDocuments(jTextField1.getText());
            }
        });
        
        // Navigation buttons
        jButton14.addActionListener(e -> showMessage("My Inventory clicked"));
        jButton10.addActionListener(e -> showMessage("Lending clicked"));
        jButton9.addActionListener(e -> showMessage("Goal clicked"));
        jButton12.addActionListener(e -> showMessage("Financial Analytics clicked"));
        jButton11.addActionListener(e -> showMessage("Dashboard clicked"));
        jButton13.addActionListener(e -> showMessage("Dashboard clicked"));
    }
    
    private void showAllDocuments() {
    documentList = controller.getAllDocuments(); // reload DB
    refreshDocumentDisplay();

    resetCategoryButtons();
    jButton1.setBackground(new Color(0, 127, 76));
    jButton1.setForeground(Color.WHITE);
    }
    
    private void filterByCategory(String category) {
        
    // 1️⃣ Get fresh data from DB
    List<Document> filtered =
            category.equals("All")
            ? controller.getAllDocuments()
            : controller.getDocumentsByCategory(category);

    // 2️⃣ Render
    documentPanelContainer.removeAll();

    for (Document doc : filtered) {
        documentPanelContainer.add(createDocumentPanel(doc));
        documentPanelContainer.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    documentPanelContainer.revalidate();
    documentPanelContainer.repaint();

    // 3️⃣ Button highlight
    resetCategoryButtons();
    Color activeBg = new Color(0, 127, 76);

    switch (category) {
        case "Life Style" -> jButton7.setBackground(activeBg);
        case "Health" -> jButton6.setBackground(activeBg);
        case "Financial" -> jButton5.setBackground(activeBg);
        case "Utilities" -> jButton8.setBackground(activeBg);
        case "Other" -> jButton2.setBackground(activeBg);
        default -> jButton1.setBackground(activeBg);
    }
    }
    
    private void resetCategoryButtons() {
        Color defaultBg = new Color(169, 221, 200);
        Color defaultFg = Color.BLACK;
        
        jButton1.setBackground(defaultBg);
        jButton1.setForeground(defaultFg);
        jButton7.setBackground(defaultBg);
        jButton7.setForeground(defaultFg);
        jButton6.setBackground(defaultBg);
        jButton6.setForeground(defaultFg);
        jButton5.setBackground(defaultBg);
        jButton5.setForeground(defaultFg);
        jButton8.setBackground(defaultBg);
        jButton8.setForeground(defaultFg);
        jButton2.setBackground(defaultBg);
        jButton2.setForeground(defaultFg);
    }
    
    private void searchDocuments(String query) {
        
    if (query.equals("Search Receipts....") || query.trim().isEmpty()) {
        showAllDocuments();
        return;
    }

    List<Document> results = controller.searchDocuments(query);

    documentPanelContainer.removeAll();

    for (Document doc : results) {
        documentPanelContainer.add(createDocumentPanel(doc));
        documentPanelContainer.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    documentPanelContainer.revalidate();
    documentPanelContainer.repaint();
    }
    
    private void setupIcons() {
        // Initial search field styling
        jTextField1.setForeground(Color.GRAY);
    }
    
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel10 = new javax.swing.JPanel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jButton21 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jButton24 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        btnAddExpense = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();

        jPanel10.setLayout(null);

        jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel51.setText("Rent Paid");
        jPanel10.add(jLabel51);
        jLabel51.setBounds(80, 10, 90, 30);

        jLabel52.setText("May 20 2025");
        jPanel10.add(jLabel52);
        jLabel52.setBounds(140, 40, 90, 16);

        jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel53.setText("Utilities");
        jPanel10.add(jLabel53);
        jLabel53.setBounds(80, 40, 50, 20);

        jLabel54.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pdf.png"))); // NOI18N
        jLabel54.setText("jLabel10");
        jPanel10.add(jLabel54);
        jLabel54.setBounds(10, 10, 50, 50);

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/de.png"))); // NOI18N
        jPanel10.add(jButton19);
        jButton19.setBounds(1170, 20, 29, 30);

        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Screenshot 2025-12-12 220657.png"))); // NOI18N
        jPanel10.add(jButton20);
        jButton20.setBounds(1130, 20, 27, 30);

        jLabel55.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel55.setText("Rent Paid");
        jPanel10.add(jLabel55);
        jLabel55.setBounds(80, 10, 90, 30);

        jLabel56.setText("May 20 2025");
        jPanel10.add(jLabel56);
        jLabel56.setBounds(140, 40, 90, 16);

        jLabel57.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel57.setText("Utilities");
        jPanel10.add(jLabel57);
        jLabel57.setBounds(80, 40, 50, 20);

        jLabel58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pdf.png"))); // NOI18N
        jLabel58.setText("jLabel10");
        jPanel10.add(jLabel58);
        jLabel58.setBounds(10, 10, 50, 50);

        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Screenshot 2025-12-12 220657.png"))); // NOI18N
        jPanel10.add(jButton21);
        jButton21.setBounds(1130, 20, 27, 30);

        jPanel11.setLayout(null);

        jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel59.setText("Rent Paid");
        jPanel11.add(jLabel59);
        jLabel59.setBounds(80, 10, 90, 30);

        jLabel60.setText("May 20 2025");
        jPanel11.add(jLabel60);
        jLabel60.setBounds(140, 40, 90, 16);

        jLabel61.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel61.setText("Utilities");
        jPanel11.add(jLabel61);
        jLabel61.setBounds(80, 40, 50, 20);

        jLabel62.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pdf.png"))); // NOI18N
        jLabel62.setText("jLabel10");
        jPanel11.add(jLabel62);
        jLabel62.setBounds(10, 10, 50, 50);

        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/de.png"))); // NOI18N
        jPanel11.add(jButton22);
        jButton22.setBounds(1170, 20, 29, 30);

        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Screenshot 2025-12-12 220657.png"))); // NOI18N
        jPanel11.add(jButton23);
        jButton23.setBounds(1130, 20, 27, 30);

        jLabel63.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel63.setText("Rent Paid");
        jPanel11.add(jLabel63);
        jLabel63.setBounds(80, 10, 90, 30);

        jLabel64.setText("May 20 2025");
        jPanel11.add(jLabel64);
        jLabel64.setBounds(140, 40, 90, 16);

        jLabel65.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel65.setText("Utilities");
        jPanel11.add(jLabel65);
        jLabel65.setBounds(80, 40, 50, 20);

        jLabel66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pdf.png"))); // NOI18N
        jLabel66.setText("jLabel10");
        jPanel11.add(jLabel66);
        jLabel66.setBounds(10, 10, 50, 50);

        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Screenshot 2025-12-12 220657.png"))); // NOI18N
        jPanel11.add(jButton24);
        jButton24.setBounds(1130, 20, 27, 30);

        jPanel10.add(jPanel11);
        jPanel11.setBounds(10, 220, 1220, 80);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(254, 251, 238));
        jPanel1.setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(1340, 10, 80, 70);

        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/kosh2.png"))); // NOI18N
        jLabel46.setText("jLabel46");
        jPanel1.add(jLabel46);
        jLabel46.setBounds(30, -10, 120, 125);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1440, 100);

        jPanel3.setBackground(new java.awt.Color(205, 231, 217));
        jPanel3.setLayout(null);

        jButton1.setBackground(new java.awt.Color(0, 127, 76));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("All");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);
        jButton1.setBounds(0, 160, 80, 30);

        btnAddExpense.setBackground(new java.awt.Color(0, 127, 76));
        btnAddExpense.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnAddExpense.setForeground(new java.awt.Color(255, 255, 255));
        btnAddExpense.setText("Add Expenses");
        btnAddExpense.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                btnAddExpenseInputMethodTextChanged(evt);
            }
        });
        btnAddExpense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddExpenseActionPerformed(evt);
            }
        });
        jPanel3.add(btnAddExpense);
        btnAddExpense.setBounds(950, 20, 280, 60);

        jTextField1.setBackground(new java.awt.Color(220, 220, 220));
        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextField1.setText("Search Receipts....");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField1);
        jTextField1.setBounds(610, 40, 230, 40);

        jTextField2.setBackground(new java.awt.Color(205, 231, 217));
        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jTextField2.setText("Documents ");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField2);
        jTextField2.setBounds(0, 10, 370, 60);

        jTextField3.setBackground(new java.awt.Color(205, 231, 217));
        jTextField3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextField3.setText(" Store and manage important document ");
        jPanel3.add(jTextField3);
        jTextField3.setBounds(0, 60, 370, 60);

        jButton2.setBackground(new java.awt.Color(169, 221, 200));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setText("Other");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2);
        jButton2.setBounds(550, 160, 90, 30);

        jButton5.setBackground(new java.awt.Color(169, 221, 200));
        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton5.setText("Financial");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5);
        jButton5.setBounds(330, 160, 90, 30);

        jButton6.setBackground(new java.awt.Color(169, 221, 200));
        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton6.setText("Health");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton6);
        jButton6.setBounds(220, 160, 90, 30);

        jButton7.setBackground(new java.awt.Color(169, 221, 200));
        jButton7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton7.setText("Life Style");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton7);
        jButton7.setBounds(110, 160, 90, 30);

        jButton8.setBackground(new java.awt.Color(169, 221, 200));
        jButton8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton8.setText("Utilities");
        jPanel3.add(jButton8);
        jButton8.setBounds(440, 160, 90, 30);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jPanel3.add(jScrollPane1);
        jScrollPane1.setBounds(0, 210, 1240, 590);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(200, 100, 1240, 820);

        jPanel2.setBackground(new java.awt.Color(224, 236, 229));
        jPanel2.setLayout(null);

        jButton9.setBackground(new java.awt.Color(255, 220, 168));
        jButton9.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton9.setText("Goal");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton9);
        jButton9.setBounds(0, 230, 190, 60);

        jButton10.setBackground(new java.awt.Color(255, 220, 168));
        jButton10.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton10.setText("Lending");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton10);
        jButton10.setBounds(0, 160, 190, 60);

        jButton11.setBackground(new java.awt.Color(255, 220, 168));
        jButton11.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton11.setText("Dashbord");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton11);
        jButton11.setBounds(0, 20, 190, 60);

        jButton12.setBackground(new java.awt.Color(255, 220, 168));
        jButton12.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton12.setText("Financial Analytics");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton12);
        jButton12.setBounds(0, 300, 190, 60);

        jButton13.setBackground(new java.awt.Color(0, 127, 76));
        jButton13.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton13.setText("Document");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton13);
        jButton13.setBounds(0, 370, 190, 60);

        jButton14.setBackground(new java.awt.Color(255, 220, 168));
        jButton14.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jButton14.setText("My Inventory");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton14);
        jButton14.setBounds(0, 90, 190, 60);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 100, 200, 800);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
                showMessage("Goal feature coming soon!");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        showMessage("Lending feature coming soon!");
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        showMessage("Dashboard feature coming soon!");
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        showMessage("Financial Analytics feature coming soon!");
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        showMessage("Dashboard feature coming soon!");
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        showMessage("My Inventory feature coming soon!");
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        filterByCategory("Life Style");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        filterByCategory("Health");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        filterByCategory("Financial");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void btnAddExpenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddExpenseActionPerformed
    }//GEN-LAST:event_btnAddExpenseActionPerformed

    private void btnAddExpenseInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_btnAddExpenseInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddExpenseInputMethodTextChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        showAllDocuments();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
            filterByCategory("Others");
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
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
        java.awt.EventQueue.invokeLater(new Runnable() {
    @Override
    public void run() {
        new documents().setVisible(true);
    }
});

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddExpense;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
