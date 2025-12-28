/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author anubhavsilwal
 */
public class JarTest {
    public static void main(String[] args) {
        System.out.println("Testing current JAR setup...\n");
        
        // Test 1: JCommon core
        try {
            Class.forName("org.jfree.util.StringUtils");
            System.out.println("✓ jcommon-1.0.23.jar - OK");
        } catch (ClassNotFoundException e) {
            System.out.println("✗ jcommon-1.0.23.jar - MISSING");
        }
        
        // Test 2: JCommon XML
        try {
            Class.forName("org.jfree.xml.XmlWriteSupport");
            System.out.println("✓ jcommon-xml-1.0.23.jar - OK");
        } catch (ClassNotFoundException e) {
            System.out.println("✗ jcommon-xml-1.0.23.jar - MISSING");
        }
        
        // Test 3: JFreeChart main library
        try {
            Class.forName("org.jfree.chart.JFreeChart");
            System.out.println("✓ jfreechart-1.0.19.jar - OK");
        } catch (ClassNotFoundException e) {
            System.out.println("✗ jfreechart-1.0.19.jar - MISSING (This is your problem!)");
        }
        
        System.out.println("\n=== SOLUTION ===");
        System.out.println("You need the MAIN JFreeChart library, not the demo.");
        System.out.println("Download: jfreechart-1.0.19.jar (not -demo.jar)");
    }
}
