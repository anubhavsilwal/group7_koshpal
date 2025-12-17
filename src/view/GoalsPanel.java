package view;

import controller.GoalsController;
import model.Goal;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GoalsPanel extends JPanel {
    private GoalsController controller;
    private JPanel goalsCardsPanel;
    private JLabel totalGoalsLabel;
    private JLabel totalTargetLabel;
    private JLabel currentSavedLabel;
    private JLabel avgProgressLabel;
    
    public GoalsPanel() {
        initComponents();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Top panel with "Add goals" button
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Financial Goals");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        JButton addGoalButton = new JButton("Add Goal");
        addGoalButton.addActionListener(e -> showAddGoalDialog());
        
        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(addGoalButton, BorderLayout.EAST);
        
        // Goals cards panel (scrollable)
        goalsCardsPanel = new JPanel();
        goalsCardsPanel.setLayout(new GridLayout(0, 2, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(goalsCardsPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        
        // Summary panel
        JPanel summaryPanel = createSummaryPanel();
        
        // Add components
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(summaryPanel, BorderLayout.SOUTH);
    }
    
    private JPanel createSummaryPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 4, 10, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Summary"));
        
        // Labels
        panel.add(new JLabel("Total Goals:"));
        panel.add(new JLabel("Total target:"));
        panel.add(new JLabel("Current saved:"));
        panel.add(new JLabel("Avg Progress:"));
        
        // Value labels
        totalGoalsLabel = new JLabel("0");
        totalTargetLabel = new JLabel("$0.00");
        currentSavedLabel = new JLabel("$0.00");
        avgProgressLabel = new JLabel("0%");
        
        panel.add(totalGoalsLabel);
        panel.add(totalTargetLabel);
        panel.add(currentSavedLabel);
        panel.add(avgProgressLabel);
        
        return panel;
    }
    
    private void showAddGoalDialog() {
        if (controller != null) {
            controller.showAddGoalDialog();
        }
    }
    
    public void setController(GoalsController controller) {
        this.controller = controller;
    }
    
    public void updateGoalsDisplay(java.util.List<Goal> goals) {
        goalsCardsPanel.removeAll();
        
        for (int i = 0; i < goals.size(); i++) {
            Goal goal = goals.get(i);
            JPanel card = createGoalCard(goal, i);
            goalsCardsPanel.add(card);
        }
        
        goalsCardsPanel.revalidate();
        goalsCardsPanel.repaint();
    }
    
    private JPanel createGoalCard(Goal goal, int index) {
        JPanel card = new JPanel();
        card.setLayout(new BorderLayout(5, 5));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        card.setBackground(Color.WHITE);
        
        // Goal name
        JLabel nameLabel = new JLabel(goal.getName());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Progress bar
        JProgressBar progressBar = new JProgressBar();
        progressBar.setValue((int) goal.getProgress());
        progressBar.setStringPainted(true);
        progressBar.setString(String.format("%.0f%%", goal.getProgress()));
        
        // Progress text
        JLabel progressText = new JLabel(goal.getProgressText());
        progressText.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Due date
        JLabel dueDateLabel = new JLabel("Due: " + goal.getDueDateFormatted());
        
        // Location
        JLabel locationLabel = new JLabel(goal.getLocation());
        
        // Add money button
        JButton addMoneyButton = new JButton("Add Money");
        addMoneyButton.addActionListener(e -> showAddMoneyDialog(index));
        
        // Panel for text information
        JPanel infoPanel = new JPanel(new GridLayout(3, 1, 0, 2));
        infoPanel.add(progressText);
        infoPanel.add(dueDateLabel);
        infoPanel.add(locationLabel);
        
        // Add components to card
        card.add(nameLabel, BorderLayout.NORTH);
        card.add(progressBar, BorderLayout.CENTER);
        card.add(infoPanel, BorderLayout.SOUTH);
        card.add(addMoneyButton, BorderLayout.EAST);
        
        return card;
    }
    
    private void showAddMoneyDialog(int goalIndex) {
        if (controller != null) {
            controller.showAddMoneyDialog(goalIndex);
        }
    }
    
    public void updateSummary(int totalGoals, double totalTarget, 
                             double currentSaved, double avgProgress) {
        totalGoalsLabel.setText(String.valueOf(totalGoals));
        totalTargetLabel.setText(String.format("$%.2f", totalTarget));
        currentSavedLabel.setText(String.format("$%.2f", currentSaved));
        avgProgressLabel.setText(String.format("%.0f%%", avgProgress));
    }
}