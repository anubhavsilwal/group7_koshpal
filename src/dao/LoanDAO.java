/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 package dao;

import model.Loan;
import database.Mysqlconnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author salmanansari.81
 */
public class LoanDAO {
    private Mysqlconnection mysql = new Mysqlconnection();
    
    // Inner class for statistics
    public class LoanStatistics {
        private double totalAmount;
        private int activeCount;
        private int overdueCount;
        
        public LoanStatistics(double totalAmount, int activeCount, int overdueCount) {
            this.totalAmount = totalAmount;
            this.activeCount = activeCount;
            this.overdueCount = overdueCount;
        }
        
        public double getTotalAmount() { return totalAmount; }
        public int getActiveCount() { return activeCount; }
        public int getOverdueCount() { return overdueCount; }
    }
    
    // 1. Create Loan (Insert new loan)
    public int createLoan(Loan loan, int userId) throws SQLException {
        Connection conn = mysql.openConnection();
        String sql = "INSERT INTO loans (borrower_name, item_name, due_date, "
                + "loan_amount, status, loan_date, item_id, user_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql, 
                Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, loan.getBorrowerName());
            pstmt.setString(2, loan.getItemName());
            pstmt.setDate(3, Date.valueOf(loan.getDueDate()));
            pstmt.setBigDecimal(4, java.math.BigDecimal.valueOf(loan.getLoanAmount()));
            pstmt.setString(5, loan.getStatus());
            pstmt.setDate(6, Date.valueOf(loan.getLoanDate()));
            pstmt.setInt(7, loan.getItemId());
            pstmt.setInt(8, userId);
            
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // Return generated loan_id
            }
            return -1;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
    // 2. Get All Loans for a User
    public List<Loan> getLoansByUserId(int userId) throws SQLException {
        List<Loan> loans = new ArrayList<>();
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM loans WHERE user_id = ? ORDER BY due_date ASC";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Loan loan = extractLoanFromResultSet(rs);
                loans.add(loan);
            }
        } finally {
            mysql.closeConnection(conn);
        }
        return loans;
    }
    
    // 3. Get Active Loans
    public List<Loan> getActiveLoans(int userId) throws SQLException {
        List<Loan> loans = new ArrayList<>();
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM loans WHERE user_id = ? AND status = 'Active' "
                + "ORDER BY due_date ASC";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                loans.add(extractLoanFromResultSet(rs));
            }
        } finally {
            mysql.closeConnection(conn);
        }
        return loans;
    }
    
    // 4. Get Overdue Loans
    public List<Loan> getOverdueLoans(int userId) throws SQLException {
        List<Loan> loans = new ArrayList<>();
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM loans WHERE user_id = ? AND "
                + "(status = 'Overdue' OR (status = 'Active' AND due_date < CURDATE()))";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                loans.add(extractLoanFromResultSet(rs));
            }
        } finally {
            mysql.closeConnection(conn);
        }
        return loans;
    }
    
    // 5. Update Loan Status
    public boolean updateLoanStatus(int loanId, String newStatus, int userId) 
            throws SQLException {
        Connection conn = mysql.openConnection();
        String sql = "UPDATE loans SET status = ? WHERE loan_id = ? AND user_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newStatus);
            pstmt.setInt(2, loanId);
            pstmt.setInt(3, userId);
            
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
    // 6. Update Loan (all fields)
    public boolean updateLoan(Loan loan, int userId) throws SQLException {
        Connection conn = mysql.openConnection();
        String sql = "UPDATE loans SET borrower_name = ?, item_name = ?, "
                + "due_date = ?, loan_amount = ?, status = ?, item_id = ? "
                + "WHERE loan_id = ? AND user_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, loan.getBorrowerName());
            pstmt.setString(2, loan.getItemName());
            pstmt.setDate(3, Date.valueOf(loan.getDueDate()));
            pstmt.setBigDecimal(4, java.math.BigDecimal.valueOf(loan.getLoanAmount()));
            pstmt.setString(5, loan.getStatus());
            pstmt.setInt(6, loan.getItemId());
            pstmt.setInt(7, loan.getLoanId());
            pstmt.setInt(8, userId);
            
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
    // 7. Delete Loan
    public boolean deleteLoan(int loanId, int userId) throws SQLException {
        Connection conn = mysql.openConnection();
        String sql = "DELETE FROM loans WHERE loan_id = ? AND user_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, loanId);
            pstmt.setInt(2, userId);
            
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
    // 8. Get Loan by ID
    public Loan getLoanById(int loanId, int userId) throws SQLException {
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM loans WHERE loan_id = ? AND user_id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, loanId);
            pstmt.setInt(2, userId);
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractLoanFromResultSet(rs);
            }
        } finally {
            mysql.closeConnection(conn);
        }
        return null;
    }
    
    // 9. Search Loans
    public List<Loan> searchLoans(int userId, String searchTerm) throws SQLException {
        List<Loan> loans = new ArrayList<>();
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM loans WHERE user_id = ? AND "
                + "(borrower_name LIKE ? OR item_name LIKE ? OR status LIKE ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, "%" + searchTerm + "%");
            pstmt.setString(3, "%" + searchTerm + "%");
            pstmt.setString(4, "%" + searchTerm + "%");
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                loans.add(extractLoanFromResultSet(rs));
            }
        } finally {
            mysql.closeConnection(conn);
        }
        return loans;
    }
    
    // 10. Get Loan Statistics (for dashboard)
    public LoanStatistics getLoanStatistics(int userId) throws SQLException {
        Connection conn = mysql.openConnection();
        
        // Query for total loaned amount
        String totalSql = "SELECT SUM(loan_amount) as total FROM loans "
                + "WHERE user_id = ? AND (status = 'Active' OR status = 'Overdue')";
        
        // Query for active loans count
        String activeSql = "SELECT COUNT(*) as count FROM loans "
                + "WHERE user_id = ? AND status = 'Active'";
        
        // Query for overdue loans count
        String overdueSql = "SELECT COUNT(*) as count FROM loans "
                + "WHERE user_id = ? AND (status = 'Overdue' OR "
                + "(status = 'Active' AND due_date < CURDATE()))";
        
        double totalAmount = 0;
        int activeCount = 0;
        int overdueCount = 0;
        
        try {
            // Get total amount
            try (PreparedStatement pstmt = conn.prepareStatement(totalSql)) {
                pstmt.setInt(1, userId);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    totalAmount = rs.getBigDecimal("total") != null ? 
                                  rs.getBigDecimal("total").doubleValue() : 0;
                }
            }
            
            // Get active count
            try (PreparedStatement pstmt = conn.prepareStatement(activeSql)) {
                pstmt.setInt(1, userId);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    activeCount = rs.getInt("count");
                }
            }
            
            // Get overdue count
            try (PreparedStatement pstmt = conn.prepareStatement(overdueSql)) {
                pstmt.setInt(1, userId);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    overdueCount = rs.getInt("count");
                }
            }
            
        } finally {
            mysql.closeConnection(conn);
        }
        
        return new LoanStatistics(totalAmount, activeCount, overdueCount);
    }
    
    // 11. Get Loans by Item ID
    public List<Loan> getLoansByItemId(int itemId, int userId) throws SQLException {
        List<Loan> loans = new ArrayList<>();
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM loans WHERE item_id = ? AND user_id = ? "
                + "ORDER BY loan_date DESC";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, itemId);
            pstmt.setInt(2, userId);
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                loans.add(extractLoanFromResultSet(rs));
            }
        } finally {
            mysql.closeConnection(conn);
        }
        return loans;
    }
    
    // 12. Update overdue statuses (cron job or periodic update)
    public int updateOverdueStatuses(int userId) throws SQLException {
        Connection conn = mysql.openConnection();
        String sql = "UPDATE loans SET status = 'Overdue' "
                + "WHERE user_id = ? AND status = 'Active' AND due_date < CURDATE()";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            return pstmt.executeUpdate();
        } finally {
            mysql.closeConnection(conn);
        }
    }
    
    // 13. Get loans due soon (within 7 days)
    public List<Loan> getLoansDueSoon(int userId) throws SQLException {
        List<Loan> loans = new ArrayList<>();
        Connection conn = mysql.openConnection();
        String sql = "SELECT * FROM loans WHERE user_id = ? AND status = 'Active' "
                + "AND due_date BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 7 DAY) "
                + "ORDER BY due_date ASC";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                loans.add(extractLoanFromResultSet(rs));
            }
        } finally {
            mysql.closeConnection(conn);
        }
        return loans;
    }
    
    // Helper method to extract Loan from ResultSet
    private Loan extractLoanFromResultSet(ResultSet rs) throws SQLException {
        return new Loan(
            rs.getInt("loan_id"),
            rs.getString("borrower_name"),
            rs.getString("item_name"),
            rs.getDate("due_date").toLocalDate(),
            rs.getBigDecimal("loan_amount").doubleValue(),
            rs.getString("status"),
            rs.getDate("loan_date").toLocalDate(),
            rs.getInt("item_id")
        );
    }
}