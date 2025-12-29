package database;

import java.sql.*;

/**
 *
 * @author User
 */
public class Mysqlconnection implements Database {
    
    // Static block to load MySQL driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("MySQL Driver loaded successfully");
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load MySQL Driver: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Connection openConnection() {
        try {
            String username = "root";
            String password = "admin123";
            String database = "koshpal_data";
            
            // Connection URL with parameters for MySQL 8
            String url = "jdbc:mysql://localhost:3306/" + database + 
                        "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
            
            System.out.println("Attempting to connect to: " + url);
            System.out.println("Username: " + username);
            
            Connection connection = DriverManager.getConnection(url, username, password);
            
            if (connection == null || connection.isClosed()) {
                System.out.println("❌ Database connection failed");
            } else {
                System.out.println("✅ Database connection successful!");
                System.out.println("Connection valid: " + connection.isValid(2)); // 2 second timeout
            }
            return connection;
            
        } catch (Exception e) {
            System.err.println("❌ Database connection error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("✅ Connection closed");
            }
        } catch (Exception e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }

    @Override
    public ResultSet runQuery(Connection conn, String query) {
        try {
            if (conn == null || conn.isClosed()) {
                System.err.println("Cannot run query - connection is null or closed");
                return null;
            }
            
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);
            return result;
        } catch (Exception e) {
            System.err.println("Query execution error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int excecuteUpdate(Connection conn, String query) {
        try {
            if (conn == null || conn.isClosed()) {
                System.err.println("Cannot execute update - connection is null or closed");
                return -1;
            }
            
            Statement stmt = conn.createStatement();
            int result = stmt.executeUpdate(query);
            System.out.println("Update executed. Rows affected: " + result);
            return result;
        } catch (Exception e) {
            System.err.println("Update execution error: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }
    
    // Optional: Test method to verify connection
    public static void testConnection() {
        System.out.println("\n=== Testing MySQL Connection ===");
        Mysqlconnection db = new Mysqlconnection();
        Connection conn = db.openConnection();
        
        if (conn != null) {
            try {
                // Test with a simple query
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT 1 as test");
                if (rs.next()) {
                    System.out.println("✅ Test query successful: " + rs.getInt("test"));
                }
                
                // Get database info
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Database: " + meta.getDatabaseProductName());
                System.out.println("Version: " + meta.getDatabaseProductVersion());
                
            } catch (SQLException e) {
                System.err.println("Test query failed: " + e.getMessage());
            } finally {
                db.closeConnection(conn);
            }
        }
        System.out.println("=== Connection Test Complete ===\n");
    }
    
    // Main method for standalone testing
    public static void main(String[] args) {
        testConnection();
    }
}