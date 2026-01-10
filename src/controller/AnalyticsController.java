/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.FinanceDAO;
import model.FinancialData.MonthlySummary;
import model.FinancialData.CategoryBreakdown;
import view.analytics;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AnalyticsController {
    private analytics view;
    private FinanceDAO financeDao;
    
    public AnalyticsController(analytics view) {
        this.view = view;
        this.financeDao = new FinanceDAO();
        loadFinancialData();
    }
    
    public void loadFinancialData() {
        updateSummaryPanel();
        createAndDisplayCharts();
    }
    
    private void updateSummaryPanel() {
        List<MonthlySummary> monthlyData = financeDao.getMonthlySummary();
        
        double totalIncome = 0;
        double totalExpense = 0;
        
        for (MonthlySummary ms : monthlyData) {
            totalIncome += ms.getIncome();
            totalExpense += ms.getExpenses();
        }
        
        double netCashflow = totalIncome - totalExpense;
        
        view.getLblIncomeValue().setText("$" + String.format("%.2f", totalIncome));
        view.getLblExpenseValue().setText("$" + String.format("%.2f", totalExpense));
        view.getLblCashflow().setText("$" + String.format("%.2f", netCashflow));
    }
    
    private void createAndDisplayCharts() {
        // Create main panel to hold all charts
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        // Create bar chart
        JPanel barChartPanel = createBarChartPanel();
        barChartPanel.setBorder(BorderFactory.createTitledBorder("Income vs Expenses"));
        
        // Create expense pie chart
        JPanel expensePiePanel = createExpensePieChartPanel();
        expensePiePanel.setBorder(BorderFactory.createTitledBorder("Expense Distribution"));
        
        // Create income pie chart
        JPanel incomePiePanel = createIncomePieChartPanel();
        incomePiePanel.setBorder(BorderFactory.createTitledBorder("Income Sources"));
        
        // Add all charts to main panel
        mainPanel.add(barChartPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(expensePiePanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(incomePiePanel);
        
        // Add to scroll pane
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        // Clear and add to view
        view.getScrollPane().getViewport().removeAll();
        view.getScrollPane().setViewportView(mainPanel);
        view.revalidate();
        view.repaint();
        
        System.out.println("Charts created and displayed!");
    }
    
    private JPanel createBarChartPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        try {
            List<MonthlySummary> data = financeDao.getMonthlySummary();
            
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (MonthlySummary ms : data) {
                dataset.addValue(ms.getIncome(), "Income", ms.getMonth());
                dataset.addValue(ms.getExpenses(), "Expenses", ms.getMonth());
            }
            
            JFreeChart chart = ChartFactory.createBarChart(
                "Monthly Financial Summary",
                "Month",
                "Amount ($)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
            );
            
            CategoryPlot plot = chart.getCategoryPlot();
            plot.getRenderer().setSeriesPaint(0, new Color(46, 204, 113)); // Green for income
            plot.getRenderer().setSeriesPaint(1, new Color(231, 76, 60));   // Red for expenses
            
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(800, 400));
            panel.add(chartPanel, BorderLayout.CENTER);
            
        } catch (Exception e) {
            System.err.println("Error creating bar chart: " + e.getMessage());
            panel.add(new JLabel("Error loading bar chart data"), BorderLayout.CENTER);
        }
        
        return panel;
    }
    
    private JPanel createExpensePieChartPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        try {
            List<CategoryBreakdown> data = financeDao.getExpenseBreakdown("Jun", 2024);
            
            DefaultPieDataset dataset = new DefaultPieDataset();
            for (CategoryBreakdown cb : data) {
                dataset.setValue(cb.getName(), cb.getAmount());
            }
            
            JFreeChart chart = ChartFactory.createPieChart(
                "Expense Categories - June 2024",
                dataset,
                true,
                true,
                false
            );
            
            PiePlot plot = (PiePlot) chart.getPlot();
            
            // Set colors for common expense categories
            if (dataset.getKeys().contains("Housing")) 
                plot.setSectionPaint("Housing", new Color(52, 152, 219));
            if (dataset.getKeys().contains("Food")) 
                plot.setSectionPaint("Food", new Color(155, 89, 182));
            if (dataset.getKeys().contains("Transport")) 
                plot.setSectionPaint("Transport", new Color(241, 196, 15));
            if (dataset.getKeys().contains("Utilities")) 
                plot.setSectionPaint("Utilities", new Color(230, 126, 34));
            if (dataset.getKeys().contains("Entertainment")) 
                plot.setSectionPaint("Entertainment", new Color(46, 204, 113));
            if (dataset.getKeys().contains("Other")) 
                plot.setSectionPaint("Other", new Color(149, 165, 166));
            
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(800, 350));
            panel.add(chartPanel, BorderLayout.CENTER);
            
        } catch (Exception e) {
            System.err.println("Error creating expense pie chart: " + e.getMessage());
            panel.add(new JLabel("Error loading expense data"), BorderLayout.CENTER);
        }
        
        return panel;
    }
    
    private JPanel createIncomePieChartPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        try {
            List<CategoryBreakdown> data = financeDao.getIncomeSources("Jun", 2024);
            
            DefaultPieDataset dataset = new DefaultPieDataset();
            for (CategoryBreakdown cb : data) {
                dataset.setValue(cb.getName(), cb.getAmount());
            }
            
            JFreeChart chart = ChartFactory.createPieChart(
                "Income Sources - June 2024",
                dataset,
                true,
                true,
                false
            );
            
            PiePlot plot = (PiePlot) chart.getPlot();
            
            // Set colors for common income sources
            if (dataset.getKeys().contains("Salary")) 
                plot.setSectionPaint("Salary", new Color(46, 204, 113));
            if (dataset.getKeys().contains("Freelance")) 
                plot.setSectionPaint("Freelance", new Color(52, 152, 219));
            if (dataset.getKeys().contains("Investments")) 
                plot.setSectionPaint("Investments", new Color(155, 89, 182));
            if (dataset.getKeys().contains("Business")) 
                plot.setSectionPaint("Business", new Color(241, 196, 15));
            
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(800, 350));
            panel.add(chartPanel, BorderLayout.CENTER);
            
        } catch (Exception e) {
            System.err.println("Error creating income pie chart: " + e.getMessage());
            panel.add(new JLabel("Error loading income data"), BorderLayout.CENTER);
        }
        
        return panel;
    }
}