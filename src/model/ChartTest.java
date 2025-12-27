/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author samee
 */



//error but still works

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.awt.*;

public class ChartTest {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create simple frame
            JFrame frame = new JFrame("Chart Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 700);
            
            // Create simple dataset
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            dataset.addValue(1200, "Income", "Jan");
            dataset.addValue(900, "Expenses", "Jan");
            dataset.addValue(1500, "Income", "Feb");
            dataset.addValue(1100, "Expenses", "Feb");
            dataset.addValue(1300, "Income", "Mar");
            dataset.addValue(1000, "Expenses", "Mar");
            
            // Create chart
            JFreeChart chart = ChartFactory.createBarChart(
                "Test Chart - Should Display",
                "Month",
                "Amount ($)",
                dataset
            );
            
            // Add to frame
            ChartPanel chartPanel = new ChartPanel(chart);
            frame.add(chartPanel);
            
            frame.setVisible(true);
            System.out.println("Chart window should be visible!");
        });
    }
}