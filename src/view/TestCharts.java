package view;

import javax.swing.*;

public class TestCharts {
    public static void main(String[] args) {
        System.out.println("Testing chart frames...");
        
        // Test 1: NetWorthChartFrame
        try {
            NetWorthChartFrame chart1 = new NetWorthChartFrame();
            JPanel panel1 = chart1.getChartPanel();
            System.out.println("✓ NetWorthChartFrame: OK");
        } catch (Exception e) {
            System.out.println("✗ NetWorthChartFrame ERROR: " + e.getMessage());
        }
        
        // Test 2: IncomeExpenseChartFrame
        try {
            IncomeExpenseChartFrame chart2 = new IncomeExpenseChartFrame();
            JPanel panel2 = chart2.getChartPanel();
            System.out.println("✓ IncomeExpenseChartFrame: OK");
        } catch (Exception e) {
            System.out.println("✗ IncomeExpenseChartFrame ERROR: " + e.getMessage());
        }
        
        // Test 3: AssetDistributionChartFrame
        try {
            AssetDistributionChartFrame chart3 = new AssetDistributionChartFrame();
            JPanel panel3 = chart3.getChartPanel();
            System.out.println("✓ AssetDistributionChartFrame: OK");
        } catch (Exception e) {
            System.out.println("✗ AssetDistributionChartFrame ERROR: " + e.getMessage());
        }
        
        System.out.println("Test complete!");
    }
}